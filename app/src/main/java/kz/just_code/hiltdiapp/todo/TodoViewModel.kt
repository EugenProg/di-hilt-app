package kz.just_code.hiltdiapp.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    val todoListLiveData: LiveData<List<TodoEntity>> = repo.todoFlow.asLiveData()

    private val _getAllLieData = MutableLiveData<List<TodoEntity>>()
    val getAllLieData: LiveData<List<TodoEntity>> = _getAllLieData

    private val _saveSuccessLieData = MutableLiveData<Unit>()
    val saveSuccessLieData: LiveData<Unit> = _saveSuccessLieData

    fun saveTodo(todo: String) {
        launch(
            request = {
                repo.saveTodo(todo)
            },
            onSuccess = {
                _saveSuccessLieData.postValue(Unit)
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

    fun clear() {
        launch(
            request = {
                repo.deleteAll()
            }
        )
    }

    fun getAll(date: String) {
        launch(
            request = {
                repo.getAllByDate(date)
            },
            onSuccess = {
                _getAllLieData.postValue(it)
            }
        )
    }
}