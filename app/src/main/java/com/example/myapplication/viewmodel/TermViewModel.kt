package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Thumb
import com.example.myapplication.model.Term
import com.example.myapplication.model.TermRepo
import com.example.myapplication.model.TermResource

class TermViewModel : ViewModel(){
    private var repo = TermRepo()
    private var termsLiveData = MutableLiveData<TermResource>()

    fun getSearchTerm(term: String): LiveData<TermResource> {
        termsLiveData = repo.getSearchTerm(term) as MutableLiveData<TermResource>
        return termsLiveData as LiveData<TermResource>
    }

    fun sortLiveData(thumb: Thumb):LiveData<TermResource>{
        var tempList = termsLiveData.value?.terms
        if (tempList != null) {
            tempList = sortTermList(tempList,thumb)
        }
        if (tempList != null) {
            termsLiveData.value?.terms = tempList
        }
        return termsLiveData
    }

    fun sortTermList(termList:List<Term>,thumb: Thumb):List<Term>{
        return if(thumb == Thumb.UP){
            termList.sortedBy { -it.thumbs_up }
        } else{
            termList.sortedBy { -it.thumbs_down }
        }
    }


}