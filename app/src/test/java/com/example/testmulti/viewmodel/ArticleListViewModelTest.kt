package com.example.testmulti.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //TODO: create mocks, run tests and assert for below cases


    //TODO: Should have test cases for
    // LOADING:
    // SUCCESS: article list is EMPTY and data in livedata is null -> ViewState is EMPTY
    // SUCCESS: data in livedata is not null -> ViewState is LOADED
    // ERROR: Observable string is not empty -> ViewState is ERROR


}