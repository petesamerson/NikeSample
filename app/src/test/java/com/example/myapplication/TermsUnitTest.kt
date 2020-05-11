package com.example.myapplication

import com.example.myapplication.model.Term
import com.example.myapplication.model.Thumb
import com.example.myapplication.viewmodel.TermViewModel
import org.junit.Test

import kotlin.random.Random.Default.nextInt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TermsUnitTest {
    @Test
    fun sortIsCorrect() {
        val viewModel= TermViewModel()
        var termList = emptyList<Term>()
        for(i in 0..10)
            termList.plus(Term(
                "","",
                nextInt(0,100),
                nextInt(0,100)
            ))

        termList = viewModel.sortTermList(termList,
            Thumb.UP)
        var lastThumbs:Int = -1
        termList.forEach{
            if(lastThumbs != -1)
                assert(lastThumbs >= it.thumbs_up)
            lastThumbs = it.thumbs_up
        }
        termList = viewModel.sortTermList(termList,
            Thumb.DOWN)
        lastThumbs = -1
        termList.forEach{
            if(lastThumbs != -1)
                assert(lastThumbs >= it.thumbs_down)
            lastThumbs = it.thumbs_down
        }

    }
}
