package kz.just_code.hiltdiapp.data.modules

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kz.just_code.hiltdiapp.data.db.ToDoDao
import kz.just_code.hiltdiapp.data.db.ToDoDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room
            .databaseBuilder(context, ToDoDatabase::class.java, "To do database")
            .build()
    }

    @Provides
    fun getTodoDao(db: ToDoDatabase): ToDoDao = db.toDoDao()
}