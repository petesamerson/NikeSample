package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.model.TermApiInterface
import com.example.myapplication.model.TermRepo
import com.example.myapplication.model.TermRepoImp

class Injection {
    fun provideTermRepo(): TermRepo {
        return TermRepoImp()
    }
    fun provideRetrofit():TermRetrofit{
        return TermRetrofitImp()
    }
}