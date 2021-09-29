package com.example.domain.mapper

interface Mapper<F, T> {
    suspend fun map(from: F): T
}