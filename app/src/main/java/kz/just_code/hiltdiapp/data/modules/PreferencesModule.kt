package kz.just_code.hiltdiapp.data.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.just_code.hiltdiapp.data.preferences.SharedPreferencesUtils
import kz.just_code.hiltdiapp.data.preferences.SharedPreferencesUtilsImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("My Shared preferences", AppCompatActivity.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesUtils(sharedPreferences: SharedPreferences): SharedPreferencesUtils =
        SharedPreferencesUtilsImpl(sharedPreferences)
}