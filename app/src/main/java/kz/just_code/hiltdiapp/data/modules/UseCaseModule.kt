package kz.just_code.hiltdiapp.data.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.just_code.hiltdiapp.data.repositories.WeatherRepository
import kz.just_code.hiltdiapp.data.useCases.GetCurrentWeatherInteraction
import kz.just_code.hiltdiapp.data.useCases.GetCurrentWeatherUseCase
import kz.just_code.hiltdiapp.data.useCases.SearchWeatherInteraction
import kz.just_code.hiltdiapp.data.useCases.SearchWeatherUseCase

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideSearchWeatherUseCase(repo: WeatherRepository): SearchWeatherUseCase =
        SearchWeatherInteraction(repo)

    @Provides
    fun provideGetCurrentWeatherUseCase(repo: WeatherRepository): GetCurrentWeatherUseCase =
        GetCurrentWeatherInteraction(repo)
}