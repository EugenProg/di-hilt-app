package kz.just_code.hiltdiapp.data.useCases

import kz.just_code.hiltdiapp.data.network.WeatherResponse
import kz.just_code.hiltdiapp.data.repositories.WeatherRepository

interface GetCurrentWeatherUseCase {
    suspend fun execute(city: String): WeatherResponse?
}

class GetCurrentWeatherInteraction(
    private val repo: WeatherRepository
) : GetCurrentWeatherUseCase {
    override suspend fun execute(city: String): WeatherResponse? {
        return repo.getCurrentWeather(city)
    }
}

interface SearchWeatherUseCase {
    suspend fun execute(text: String): WeatherResponse?
}

class SearchWeatherInteraction(
    private val repo: WeatherRepository
) : SearchWeatherUseCase {
    override suspend fun execute(text: String): WeatherResponse? {
        return repo.searchWeather(text)
    }
}

