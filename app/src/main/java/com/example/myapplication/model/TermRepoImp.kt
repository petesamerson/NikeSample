package com.example.myapplication.model

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TermRepoImp: TermRepo{

   override fun getSearchTerm(term:String): Single<QueryResponse> {
      return getTermApiInterface().getTerm(term)
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
   }

   private fun getTermApiInterface(): TermApiInterface{
      val retrofit = Retrofit.Builder().baseUrl(
        "https://mashape-community-urban-dictionary.p.rapidapi.com/")
         .addConverterFactory(GsonConverterFactory.create())
         .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
         .build()
      return retrofit.create(TermApiInterface::class.java)
   }
}
