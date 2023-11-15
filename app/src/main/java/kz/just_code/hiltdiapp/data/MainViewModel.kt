package kz.just_code.hiltdiapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.just_code.hiltdiapp.data.useCases.GetCurrentWeatherUseCase
import kz.just_code.hiltdiapp.data.useCases.SearchWeatherUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeather: GetCurrentWeatherUseCase,
    private val searchWeather: SearchWeatherUseCase
): BaseViewModel() {

    fun getCurrentWeather(city: String) {
        launch(
            request = {
                getCurrentWeather.execute(city)
            },
            onSuccess = {

            }
        )
    }
}

abstract class BaseViewModel: ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private var _exceptionLiveData = MutableLiveData<String?>()
    val exceptionLiveData: LiveData<String?> = _exceptionLiveData

    fun <T> launch(
        request: suspend () -> T,
        onSuccess: (T) -> Unit = { }
    ) {
        coroutineScope.launch {
            try {
                _loadingLiveData.postValue(true)
                val response = request.invoke()
                onSuccess.invoke(response)
            } catch (e: Exception) {
                _exceptionLiveData.postValue(e.message)
                Log.e(">>>", e.message.orEmpty())
            } finally {
                _loadingLiveData.postValue(false)
            }
        }
    }
}