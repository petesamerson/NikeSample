package com.example.myapplication.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TermRepo{
   fun getSearchTerm(term:String): LiveData<TermResource> {
      val apiInterface =  getTermApiInterface()
      val termListLiveData = MutableLiveData<TermResource>()
      apiInterface.getTerm(term)
         .enqueue(object : Callback<QueryResponse>{
            override fun onFailure(call: Call<QueryResponse>, t: Throwable) {
                termListLiveData.value = TermResource(emptyList(),ApiStatus.NETWORK_FAIL)
            }
            override fun onResponse(call: Call<QueryResponse>, response: Response<QueryResponse>) {
               response.body()?.list?.let {
                  if(it.isNotEmpty())
                     termListLiveData.value = TermResource(it,ApiStatus.SUCCESS)
                  else
                     termListLiveData.value = TermResource(it,ApiStatus.NO_RESULTS)
               }
            }
         })
      return termListLiveData as LiveData<TermResource>
   }
   private fun getTermApiInterface(): TermApiInterface{
      val retrofit = Retrofit.Builder().baseUrl(
        "https://mashape-community-urban-dictionary.p.rapidapi.com/"
      ).addConverterFactory(GsonConverterFactory.create()).build()
      return retrofit.create(TermApiInterface::class.java)
   }
   companion object{
      const val TAG = "TermRepo"
   }
}
