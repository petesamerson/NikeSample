package com.example.myapplication.di

import com.example.myapplication.model.TermApiInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TermRetrofitImp : TermRetrofit{
    override fun getRetrofit(): TermApiInterface {
        val retrofit = Retrofit.Builder().baseUrl(
            "https://mashape-community-urban-dictionary.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(TermApiInterface::class.java)
    }

}
