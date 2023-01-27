package com.example.s3vior.ui.sheetFragment

import android.app.Activity.RESULT_OK
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.s3vior.R
import com.example.s3vior.databinding.BottomSheetFragmentBinding
import com.example.s3vior.databinding.FragmentPersonDetailsBinding
import com.example.s3vior.networking.API
import com.example.s3vior.utils.Constants
import com.example.s3vior.utils.FileUtils
import com.example.s3vior.utils.FileUtils.*
import com.example.s3vior.utils.RealPathUtil
import com.example.s3vior.viewModels.PersonDetailsViewModel
import com.example.s3vior.viewModels.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI

class BottomSheetFragment(private val _binding: FragmentPersonDetailsBinding) :
    BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetFragmentBinding
    var Images: ArrayList<Uri> = ArrayList()
    private lateinit var viewModel: PersonDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetFragmentBinding.inflate(inflater)
        initButtons()


        val viewModelFactory = ViewModelFactory(requireActivity())

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PersonDetailsViewModel::class.java)

//        lifecycleScope.launch {
//            viewModel.upload("Ahmed", "0", "lose", Uri.parse("/storage/emulated/0/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Images/IMG-20230125-WA0094.jpeg")   )
//        }
        return binding.root
    }

    private fun initButtons() {
        binding.linearGallery.setOnClickListener { galleryIntent() }
        binding.linearCamera.setOnClickListener { cameraIntent() }
    }

    private fun galleryIntent() {
        openGalleryForImage()
    }

    private fun cameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_CAMERA)
    }

    private fun openGalleryForImage() {
        // For latest versions API LEVEL 19+
//        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//        intent.addCategory(Intent.CATEGORY_OPENABLE)
//        intent.type = "image/*"
//        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY);
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Constants.UploadImage.REQUEST_CODE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_GALLERY && data != null) {
                 if (data.data != null) {


                val imageUri: Uri = data.data!!
                _binding.imageView.setImageURI(imageUri)

                val fileRealPath = getRealPathFromURI(requireContext(), imageUri) ?: "no"

                Log.i("zcacaksbcjvasv", "onActivityResult:  ${Uri.parse(fileRealPath)} ")

                lifecycleScope.launch {
              //  viewModel.upload("Ahmed", "0", "lose", Uri.parse(fileRealPath)   )
                }

            }

        }
        if (resultCode == RESULT_OK && requestCode == Constants.UploadImage.REQUEST_CODE_CAMERA && data != null) {
            val bitMab = data.extras?.get("data") as Bitmap
            _binding.imageView.setImageBitmap(bitMab)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun getRealPathFromURI(context: Context, uri: Uri): String? {
        when {
            // DocumentProvider
            DocumentsContract.isDocumentUri(context, uri) -> {
                when {
                    // ExternalStorageProvider
                    isExternalStorageDocument(uri) -> {
                        val docId = DocumentsContract.getDocumentId(uri)
                        val split = docId.split(":").toTypedArray()
                        val type = split[0]
                        // This is for checking Main Memory
                        return if ("primary".equals(type, ignoreCase = true)) {
                            if (split.size > 1) {
                                Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                            } else {
                                Environment.getExternalStorageDirectory().toString() + "/"
                            }
                            // This is for checking SD Card
                        } else {
                            "storage" + "/" + docId.replace(":", "/")
                        }
                    }
                    isDownloadsDocument(uri) -> {
                        val fileName = getFilePath(context, uri)
                        if (fileName != null) {
                            return Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName
                        }
                        var id = DocumentsContract.getDocumentId(uri)
                        if (id.startsWith("raw:")) {
                            id = id.replaceFirst("raw:".toRegex(), "")
                            val file = File(id)
                            if (file.exists()) return id
                        }
                        val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                        return getDataColumn(context, contentUri, null, null)
                    }
                    isMediaDocument(uri) -> {
                        val docId = DocumentsContract.getDocumentId(uri)
                        val split = docId.split(":").toTypedArray()
                        val type = split[0]
                        var contentUri: Uri? = null
                        when (type) {
                            "image" -> {
                                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            }
                            "video" -> {
                                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                            }
                            "audio" -> {
                                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                            }
                        }
                        val selection = "_id=?"
                        val selectionArgs = arrayOf(split[1])
                        return getDataColumn(context, contentUri, selection, selectionArgs)
                    }
                }
            }
            "content".equals(uri.scheme, ignoreCase = true) -> {
                // Return the remote address
                return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(context, uri, null, null)
            }
            "file".equals(uri.scheme, ignoreCase = true) -> {
                return uri.path
            }
        }
        return null
    }

    fun getDataColumn(context: Context, uri: Uri?, selection: String?,
                      selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(
            column
        )
        try {
            if (uri == null) return null
            cursor = context.contentResolver.query(uri, projection, selection, selectionArgs,
                null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }


    fun getFilePath(context: Context, uri: Uri?): String? {
        var cursor: Cursor? = null
        val projection = arrayOf(
            MediaStore.MediaColumns.DISPLAY_NAME
        )
        try {
            if (uri == null) return null
            cursor = context.contentResolver.query(uri, projection, null, null,
                null)
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }



}