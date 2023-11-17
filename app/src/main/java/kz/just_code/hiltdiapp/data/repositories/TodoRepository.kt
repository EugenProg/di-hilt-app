package kz.just_code.hiltdiapp.data.repositories

import kotlinx.coroutines.flow.Flow
import kz.just_code.hiltdiapp.data.db.ToDoDao
import kz.just_code.hiltdiapp.data.db.TodoEntity
import java.util.Date
import javax.inject.Inject

interface TodoRepository {
    suspend fun saveTodo(todo: String)
    suspend fun deleteAll()
    suspend fun deleteById(id: Int)
    suspend fun getAll(): List<TodoEntity>
    suspend fun getAllByDate(date: String): List<TodoEntity>
    suspend fun getById(id: Int): TodoEntity
    var todoFlow: Flow<TodoEntity>
}

class TodoRepositoryImpl @Inject constructor(
    private val dao: ToDoDao
): TodoRepository {
    override suspend fun saveTodo(todo: String) {
        dao.save(
            TodoEntity(
                0,
                todo,
                Date().toString()
            )
        )
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun deleteById(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun getAll(): List<TodoEntity> {
        return dao.getAll()
    }

    override suspend fun getAllByDate(date: String): List<TodoEntity> {
        return dao.getAllByDate(date)
    }

    override suspend fun getById(id: Int): TodoEntity {
        return dao.getById(id)
    }

    override var todoFlow: Flow<TodoEntity> = dao.getAllFlow()
}