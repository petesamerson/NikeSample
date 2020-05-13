package com.example.myapplication

import android.content.Context
import com.example.myapplication.model.TermRepo
import com.example.myapplication.model.TermRepoImp

class Injection {
    fun provideTermRepo(context: Context): TermRepo {
        return TermRepoImp()
    }
}