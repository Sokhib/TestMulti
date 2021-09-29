package com.example.testmulti

import com.example.testmulti.articlelist.ArticleListViewState
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var state: ArticleListViewState

    @Test
    fun setStateAsEmpty_ShouldBeSettled() {
        state = ArticleListViewState.Empty

        Assert.assertTrue(state.isEmpty())
        Assert.assertFalse(state.isLoaded())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isError())
    }

    @Test
    fun setStateAsLoaded_ShouldBeSettled() {
        state = ArticleListViewState.Loaded

        Assert.assertTrue(state.isLoaded())
        Assert.assertFalse(state.isEmpty())
        Assert.assertFalse(state.isLoading())
        Assert.assertFalse(state.isError())
    }
}