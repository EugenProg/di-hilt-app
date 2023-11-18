package kz.just_code.hiltdiapp.todo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.just_code.hiltdiapp.data.db.TodoEntity
import kz.just_code.hiltdiapp.databinding.ItemToDoBinding
import java.text.SimpleDateFormat
import java.util.Date

class ToDoAdapter : ListAdapter<TodoEntity, ToDoAdapter.TodoItemViewHolder>(TodoDiffUtils) {

    var click: ((TodoEntity) -> Unit)? = null

    object TodoDiffUtils : DiffUtil.ItemCallback<TodoEntity>() {
        override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem == newItem
        }
    }

    inner class TodoItemViewHolder(private val binding: ItemToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TodoEntity) {
            binding.message.text = item.message
            binding.date.text = item.saveTime.toDateFormat()

            itemView.setOnClickListener {
                click?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(
            ItemToDoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

@SuppressLint("SimpleDateFormat")
fun String.toDateFormat(): String? {
    val fromFormatter = SimpleDateFormat("yyyy-MM-dd")
    val toFormatter = SimpleDateFormat("dd MMMM yyyy")

    return fromFormatter.parse(this)?.let { toFormatter.format(it) }
}

@SuppressLint("SimpleDateFormat")
fun Date.toSaveFormat(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(this)
}