package com.example.s3vior

import com.example.SettingData

class SettingDataRepository {

     val settingDataList = listOf<SettingData>(
        SettingData(
            R.drawable.home_icon,
            "home",
            R.drawable.baseline_arrow_back_ios_24
        ),
        SettingData(R.drawable.home_icon, "home", R.drawable.baseline_arrow_back_ios_24),
        SettingData(R.drawable.home_icon, "home", R.drawable.baseline_arrow_back_ios_24)
    )

    fun getSettingData() = settingDataList
}