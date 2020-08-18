package com.example.myapplication.model

import com.example.myapplication.di.Injection
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TermRepoImp: TermRepo{
   override fun getSearchTerm(term:String): Single<QueryResponse> {
        return Injection().provideRetrofit().getRetrofit().getTerm(term)
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
   }
}
