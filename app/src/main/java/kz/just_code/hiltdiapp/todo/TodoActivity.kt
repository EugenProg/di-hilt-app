package kz.just_code.hiltdiapp.todo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kz.just_code.hiltdiapp.databinding.ActivityTodoBinding

@AndroidEntryPoint
class TodoActivity: AppCompatActivity() {
    private val viewModel: TodoViewModel by viewModels()
    private val binding: ActivityTodoBinding by lazy { ActivityTodoBinding.inflate(layoutInflater) }
    private val adapter: ToDoAdapter = ToDoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            viewModel.saveTodo(binding.todoInput.text.toString())
        }

        binding.delete.setOnClickListener {
           viewModel.clear()
        }

        binding.toDoListView.layoutManager = LinearLayoutManager(this)
        binding.toDoListView.adapter = adapter

        viewModel.todoListLiveData.observe(this) {
            adapter.submitList(it)
        }

        viewModel.getAllLieData.observe(this) {
            adapter.submitList(it)
        }

        viewModel.saveSuccessLieData.observe(this) {
            binding.todoInput.setText("")
        }

        adapter.click = {
            val alert = AlertDialog.Builder(this)
                .setMessage("You really wan to delete item: \"${it.message}\"")
                .setTitle("Warning")
                .setPositiveButton("Ok") { _, _ ->
                    viewModel.deleteById(it.id)
                }
                .setNegativeButton("No") { _, _ ->

                }
                .create()

            alert.show()
        }
    }
}