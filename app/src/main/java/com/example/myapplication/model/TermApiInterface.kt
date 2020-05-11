package com.example.myapplication.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TermApiInterface {
    @Headers("x-rapidapi-key:dbafd545d9msh86a3e9c13ed9b27p1e4603jsn6f2723f71fd7")
    @GET("define")
    fun getTerm(
        @Query("term") term:String
    ): Call<QueryResponse>
}