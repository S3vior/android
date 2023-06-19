package com.example.s3vior.utils

object Constants {
    object SpinnerData {
        val statuts = arrayOf("Male", "Female")
        var age = arrayOf("10-20", "20-30")

        var month = arrayOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "October",
            "November",
            "December"
        )

        var gender= arrayOf("Male", "Female")
    }
    object StateFilter{
        var state = arrayOf(
            "All persons",
            "Missed",
            "Founded"
        )
    }

    object UploadImage {
        const val REQUEST_CODE_GALLERY = 100
        const val REQUEST_CODE_CAMERA = 200
    }
    const val PREF_NAME = "user_data"
    const val NAME_KEY = "name"
    const val MOBILE_KEY = "mobile"
    const val TOKEN_KEY = "token"
    const val EMAIL_KEY = "email"
}