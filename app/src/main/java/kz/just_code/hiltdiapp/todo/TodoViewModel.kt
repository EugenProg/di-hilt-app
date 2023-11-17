package kz.just_code.hiltdiapp.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kz.just_code.hiltdiapp.data.BaseViewModel
import kz.just_code.hiltdiapp.data.db.TodoEntity
import kz.just_code.hiltdiapp.data.repositories.TodoRepository
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repo: TodoRepository
) : BaseViewModel() {

    val todoListLiveData: LiveData<TodoEntity> = repo.todoFlow.asLiveData()

    fun saveTodo(todo: String) {
        launch(
            request = {
                repo.saveTodo(todo)
            }
        )
    }

    fun deleteById(id: Int) {
        launch(
            request = {
                repo.deleteById(id)
            }
        )
    }
}