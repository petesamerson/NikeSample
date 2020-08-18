package com.example.myapplication.di

import com.example.myapplication.model.TermApiInterface

interface TermRetrofit {
    fun getRetrofit(): TermApiInterface
}
