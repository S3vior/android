package com.example.s3vior.utils

object Constants {
    object SpinnerData {
        val statuts = arrayOf("Male", "Female")
        var age = arrayOf("10-20", "20-30")

        var month = arrayOf(
            "يناير",
            "فبراير",
            "مارس",
            "ابريل",
            "مايو",
            "يونيو",
            "يوليو",
            "اكتوبر",
            "نوفمبر",
            "ديسمبر"
        )

        var gender= arrayOf("ذكر","انثي")
    }

    object UploadImage {
        const val REQUEST_CODE_GALLERY = 100
        const val REQUEST_CODE_CAMERA = 200
    }
}