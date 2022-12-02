package com.example.s3vior.networking

 import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    // https://woolapi.herokuapp.com/wallpapers/category?category=Gaming

    // https://woolapi.herokuapp.com/wallpapers/search?keyword=try

 @GET("")
 fun getHomeFromApi(page:Int)
}


