package com.example.s3vior

 import com.example.SettingData
 import com.example.s3vior.model.State
 import com.example.s3vior.utils.MoreFragmentRecyclerData
 import kotlinx.coroutines.flow.Flow
 import kotlinx.coroutines.flow.flow


class SettingDataRepository {


    fun getData():Flow<State<List<SettingData>>>{
        return wrapData()
    }
    private fun wrapData():Flow<State<List<SettingData>>>{
        val data = MoreFragmentRecyclerData.list

        return flow {
            emit(State.Loading)
            emit(State.Success(data))
        }
    }

}