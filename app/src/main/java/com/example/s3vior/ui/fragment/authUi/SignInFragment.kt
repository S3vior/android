package com.example.s3vior.ui.fragment.authUi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.s3vior.R
import com.example.s3vior.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val Req_Code: Int = 123


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.emailEditText.addTextChangedListener(this)
        binding.passwordEditText.addTextChangedListener(this)
        FirebaseApp.initializeApp(requireContext())
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener { v ->
            auth.signInWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            ).addOnSuccessListener {
                navigationToMainFragment(v)
            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.googleLogin.setOnClickListener {
            Toast.makeText(requireContext(), "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }
        binding.tvSignUp.setOnClickListener { navigationToSignupFragment(it) }
        return binding.root
    }
    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    // onActivityResult() function : this is where
    // we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    // this is where we update the UI after Google signin takes place
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                SavedPreference.setEmail(this, account.email.toString())
                SavedPreference.setUsername(this, account.displayName.toString())
                val intent = Intent(requireContext(), DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleSignIn.getLastSignedInAccount(requireContext()) != null) {
            startActivity(
                Intent(
                    this, DashboardActivity
                    ::class.java
                )
            )
            finish()
        }
    }
    private fun navigationToMainFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_homeFragment)
    }


    private fun navigationToSignupFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_signInFragment_to_singUpFragment)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        //edited
        binding.loginButton.isEnabled =
            binding.passwordEditText.text.isNotEmpty() && binding.emailEditText.text.isNotEmpty()
    }

    override fun afterTextChanged(s: Editable?) {
    }

}