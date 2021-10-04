package com.example.testmulti.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.model.ArticleModel
import com.example.domain.model.Day
import com.example.domain.model.Result
import com.example.domain.usecase.ArticleUseCase
import com.example.testmulti.MainCoroutineRule
import com.example.testmulti.articlelist.ArticleListViewModel
import com.example.testmulti.articlelist.ArticleListViewState
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

//TODO: create mocks, run tests and assert for below cases
//TODO: Should have test cases for
// LOADING:
// SUCCESS: article list is EMPTY and data in livedata is null -> ViewState is EMPTY
// SUCCESS: data in livedata is not null -> ViewState is LOADED
// ERROR: Observable string is not empty -> ViewState is ERROR

@RunWith(JUnit4::class)
class ArticleListViewModelTest {

    companion object {
        const val ONE_DAY = 1
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineMainRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var useCase: ArticleUseCase
    lateinit var articleViewModel: ArticleListViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        articleViewModel = ArticleListViewModel(useCase)
    }

    @Test
    fun `fetch data and check success view state`() = runBlocking {
        every { useCase(Day(ONE_DAY)) } returns flowOf(
            Result.Success(
                listOf(
                    ArticleModel(id = 1),
                    ArticleModel(id = 2)
                )
            )
        )
        articleViewModel.fetchArticles()


        assertEquals(articleViewModel.articleListState.value, ArticleListViewState.Loaded)
    }

    @Test
    fun `fetch data with empty list and check data state`() = runBlocking {
        every { useCase(Day(ONE_DAY)) } returns flowOf(Result.Success(emptyList()))
        articleViewModel.fetchArticles()
        val actualData = articleViewModel.articleListData.value
        val expectedData = emptyList<ArticleModel>()

        assertEquals(actualData, expectedData)
    }

    @Test
    fun `fetch data with empty list and check view state`() = runBlocking {
        every { useCase(Day(ONE_DAY)) } returns flowOf(Result.Success(emptyList()))
        articleViewModel.fetchArticles()

        assertEquals(articleViewModel.articleListState.value, ArticleListViewState.Empty)
    }

    @Test
    fun `throw exception while fetching data and check data state is null`() = runBlocking {
        every { useCase(Day(ONE_DAY)) } returns flowOf(Result.Error(Exception("Exception")))
        articleViewModel.fetchArticles()
        val actualData = articleViewModel.articleListData.value
        val expectedData = null

        assertEquals(actualData, expectedData)
    }

    @Test
    fun `throw exception while fetching data and check view state is error state`() =
        runBlocking {
            every { useCase(Day(ONE_DAY)) } returns flowOf(Result.Error(Exception("Exception")))
            articleViewModel.fetchArticles()

            assertEquals(articleViewModel.articleListState.value, ArticleListViewState.Error)
        }

    @Test
    fun `throw exception while fetching data and check error string is not null`() =
        runBlocking {
            every { useCase(Day(ONE_DAY)) } returns flowOf(Result.Error(IOException("IO Exception")))
            articleViewModel.fetchArticles()

            assert(articleViewModel.articleError.get().isNullOrEmpty().not())
        }

    @Test
    fun `check test first emitted loading`() = runBlocking {

        every { useCase(Day(ONE_DAY)) } returns merge(
            flowOf(Result.Loading), flowOf(
                Result.Success(
                    listOf(ArticleModel(id = 1), ArticleModel(id = 2))
                )
            )
        )
        val observer = mockk<Observer<ArticleListViewState>>()
        val slot = slot<ArticleListViewState>()
        val list = arrayListOf<ArticleListViewState>()
        articleViewModel.articleListState.observeForever(observer)
        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        articleViewModel.fetchArticles()
        assert(list.first().isLoading())
    }

    @Test
    fun `check if final state is loaded`() = runBlocking {

        every { useCase(Day(ONE_DAY)) } returns merge(
            flowOf(Result.Loading), flowOf(
                Result.Success(
                    listOf(ArticleModel(id = 1), ArticleModel(id = 2))
                )
            )
        )
        val observer = mockk<Observer<ArticleListViewState>>()
        val slot = slot<ArticleListViewState>()
        val list = arrayListOf<ArticleListViewState>()
        articleViewModel.articleListState.observeForever(observer)
        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        articleViewModel.fetchArticles()
        assertEquals(ArticleListViewState.Loaded, articleViewModel.articleListState.value)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun <T> merge(vararg flows: Flow<T>): Flow<T> = flowOf(*flows).flattenMerge()


}