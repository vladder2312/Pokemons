package com.vladder2312.pokemons.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vladder2312.pokemons.data.PokemonsService
import com.vladder2312.pokemons.data.ServiceConstants
import com.vladder2312.pokemons.data.responses.PokemonsRepository
import com.vladder2312.pokemons.data.responses.PokemonsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object PokemonsModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .client(provideOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonsService(): PokemonsService {
        return provideRetrofit().create(PokemonsService::class.java)
    }

    @Provides
    fun providePokemonsRepository(): PokemonsRepository {
        return PokemonsRepositoryImpl(providePokemonsService())
    }
}