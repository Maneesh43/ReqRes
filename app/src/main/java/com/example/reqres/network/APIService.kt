package com.example.reqres.network

import com.example.reqres.network.models.BaseClass
import com.example.reqres.network.models.UserBaseClass
import com.example.reqres.network.models.UserClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/api/users")
   fun getAllUsers(): Call<BaseClass>

    @GET("/api/users/{id}")
   fun getUser(@Path(value="id") id:String): Call<UserBaseClass>

    companion object{
        val BASE_URL="https://reqres.in"
    }
}