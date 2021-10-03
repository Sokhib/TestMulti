package com.example.testmulti.model

import com.example.data.mapper.ArticleListMapper
import com.example.data.model.PopularArticles
import com.example.data.model.Result
import com.example.domain.model.ArticleModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
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

    @Test
    fun nullList_Articles_ShouldReturnEmptyList() {
        runBlocking {
            val response = PopularArticles(null, "NO_DATA")
            val result = mapper.map(response)
            Assert.assertEquals(result, emptyList<ArticleModel>())
        }
    }

}
