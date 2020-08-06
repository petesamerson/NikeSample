package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.*
import io.reactivex.disposables.CompositeDisposable

class TermViewModel(private var repo:TermRepo) : ViewModel(){

    private val termsMutableLiveData = MutableLiveData<List<Term>>()
    val termsLiveData:LiveData<List<Term>>
        get() = termsMutableLiveData

    private val statusMutableLiveData = MutableLiveData<ApiStatus>()
    val statusLiveData:LiveData<ApiStatus>
        get() = statusMutableLiveData

    private val disposable = CompositeDisposable()

    fun getSearchTerm(term: String,isRetry: Boolean) {
        disposable.add(
            repo.getSearchTerm(term).subscribe({response ->
                val terms = response.list
                if(terms.isNotEmpty()) {
                    statusMutableLiveData.value = ApiStatus.SUCCESS
                    termsMutableLiveData.value = response.list
                }
                else{
                    statusMutableLiveData.value = ApiStatus.NO_RESULTS
                }
            }, {
                if(!isRetry)
                    statusMutableLiveData.value = ApiStatus.NETWORK_FAIL
                else
                    statusMutableLiveData.value = ApiStatus.NETWORK_RETRY_FAIL
            })
        )
    }

    fun sortLiveData(thumb: Thumb){
        termsMutableLiveData.value = termsMutableLiveData.value?.let {
            sortTermList(it,thumb)
        }
    }

    fun sortTermList(termList:List<Term>,thumb: Thumb):List<Term>{
        return if(thumb == Thumb.UP){
            termList.sortedBy { -it.thumbs_up }
        } else{
            termList.sortedBy { -it.thumbs_down }
        }
    }


}