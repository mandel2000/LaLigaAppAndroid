package es.upsa.mimo.android.laligaapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.upsa.mimo.android.laligaapp.network.ApiClient
import es.upsa.mimo.android.laligaapp.network.ApiClient.retrofit
import es.upsa.mimo.android.laligaapp.network.ApiService
import retrofit2.Retrofit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}