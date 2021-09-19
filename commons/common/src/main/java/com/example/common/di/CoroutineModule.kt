package com.example.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @Provides
    @DefaultDispatcher
    internal fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    internal fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    internal fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @MainImmediateDispatcher
    internal fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher
