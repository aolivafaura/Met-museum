package com.aoliva.metmuseum.di

import com.aoliva.metmuseum.common.dispatcher.DefaultDispatcherProvider
import com.aoliva.metmuseum.common.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideDispatchers(): DispatcherProvider = DefaultDispatcherProvider()
}