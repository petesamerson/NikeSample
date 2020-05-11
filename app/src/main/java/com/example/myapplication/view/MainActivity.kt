package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Thumb
import com.example.myapplication.model.ApiStatus
import com.example.myapplication.viewmodel.TermViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: TermViewModel = ViewModelProvider(this).get(TermViewModel::class.java)
        val termAdapter = TermAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.rcycl_terms).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = termAdapter
        }

        iv_sort_icon.setOnClickListener {
            lv_sort_icon.apply {
                visibility = if (visibility == View.INVISIBLE) {
                    View.VISIBLE
                }
                else
                    View.INVISIBLE
            }
        }
        val listThumbsSort: ListView = findViewById(R.id.lv_sort_icon)
        ArrayAdapter(
            this,
            R.layout.listview_menu,
            R.id.sort_menu_item,
            resources.getStringArray(R.array.thumb_sort_array)
        ).also { adapter ->
            listThumbsSort.adapter = adapter
        }
        listThumbsSort.setOnItemClickListener {
                _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            if (position == Thumb.UP.value) {
                viewModel.sortLiveData(Thumb.UP).observeForever {
                    termAdapter.terms = it.terms
                }
            } else{
                viewModel.sortLiveData(Thumb.UP).observeForever {
                    termAdapter.terms = it.terms
                }
            }
            listThumbsSort.visibility = View.INVISIBLE
        }

        iv_search_icon.setOnClickListener {
           viewModel.getSearchTerm(et_search_bar.editableText.toString()).observeForever {
               when (it.status) {
                   ApiStatus.SUCCESS -> termAdapter.terms = it.terms
                   ApiStatus.NO_RESULTS -> AlertDialog.Builder(this)
                       .setTitle(getString(R.string.no_results))
                       .setPositiveButton(getString(R.string.close)) { dialog, _ ->
                           dialog.cancel()
                           dialog.dismiss()
                       }.create().show()
                   else -> {
                       AlertDialog.Builder(this)
                           .setTitle(getString(R.string.no_network_detected))
                           .setPositiveButton(getString(R.string.retry)) { _, _ ->
                               viewModel.getSearchTerm(et_search_bar.editableText.toString())
                                   .observeForever { secondCall ->
                                       if(secondCall.status == ApiStatus.SUCCESS)
                                           termAdapter.terms = secondCall.terms
                                       else{
                                           AlertDialog.Builder(this)
                                               .setTitle(getString(R.string.retry_failed))
                                               .setPositiveButton(getString(R.string.close)) {
                                                       dialog, _ ->
                                                   dialog.cancel()
                                                   dialog.dismiss()
                                               }.create().show()
                                       }
                                   }
                           }
                           .create().show()
                   }
               }
           }
        }
    }
}
