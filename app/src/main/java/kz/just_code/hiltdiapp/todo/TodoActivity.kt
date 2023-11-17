package kz.just_code.hiltdiapp.todo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kz.just_code.hiltdiapp.databinding.ActivityTodoBinding

@AndroidEntryPoint
class TodoActivity: AppCompatActivity() {
    private val viewModel: TodoViewModel by viewModels()
    private val binding: ActivityTodoBinding by lazy { ActivityTodoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            viewModel.saveTodo(binding.todoInput.text.toString())
        }

        viewModel.todoListLiveData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }
    }
}