package com.example.testmulti.model

import com.example.data.mapper.ArticleListMapper
import com.example.data.model.PopularArticles
import com.example.data.model.Result
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterDetailMapperTest {

    private val mapper = ArticleListMapper()

    @Test(expected = NullPointerException::class)
    fun articleMapper_WithNullId_ShouldThrowException() {
        runBlocking {
            val response = PopularArticles(
                status = "Failure",
                results = listOf(Result(null, null, null, null, null))
            )
            mapper.map(response)
        }
    }

}
