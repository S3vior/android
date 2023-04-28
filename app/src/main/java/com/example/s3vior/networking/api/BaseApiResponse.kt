package com.example.s3vior.networking.api
import kotlinx.serialization.Serializable

@Serializable
class BaseApiResponse<T>(var status: Boolean, var message: String, var data: T?)
