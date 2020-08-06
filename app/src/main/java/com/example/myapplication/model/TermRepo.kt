package com.example.myapplication.model

import io.reactivex.Single

interface TermRepo {
    fun getSearchTerm(term:String): Single<QueryResponse>
}