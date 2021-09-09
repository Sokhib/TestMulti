package com.example.data.mapper

interface Mapper<F, T> {
    suspend fun map(from: F): T
}