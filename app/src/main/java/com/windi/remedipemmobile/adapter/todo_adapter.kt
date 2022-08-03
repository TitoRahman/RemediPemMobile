package com.windi.remedipemmobile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.windi.remedipemmobile.R
import com.windi.remedipemmobile.models.Todo
import java.text.SimpleDateFormat

class todo_adapter (
    private val todoList : List<Todo>
        ) : RecyclerView.Adapter<todo_adapter.RecyclerViewVH> (){
    inner class RecyclerViewVH(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title_box_value)
        val date : TextView = itemView.findViewById(R.id.date_box_value)
        val description : TextView = itemView.findViewById(R.id.description_box_value)
        val dateDate : TextView = itemView.findViewById(R.id.date_box_date_value)
        val dateMonth : TextView = itemView.findViewById(R.id.date_box_month_value)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewVH {
        return RecyclerViewVH(LayoutInflater.from(parent.context).inflate(R.layout.todo_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: RecyclerViewVH, position: Int) {
        val currentTodo = todoList[position]
        val monthName = listOf("JAN","FEB","MAR", "APR", "MAY", "JUN", "JUL","AUG", "SEP", "OCT", "NOV","DEC")
        holder.apply {
            title.text = currentTodo.title
            //date.text = LocalDate.parse(currentTodo.date.toString(), DateTimeFormatter.ofPattern("dd-mm-yyyy : h:mm a")).toString()
            date.text = SimpleDateFormat("dd-MM-yyyy : hh:mm a").format(currentTodo.date)
            description.text = currentTodo.description
            dateDate.text = currentTodo.date.date.toString()
            dateMonth.text = monthName[currentTodo.date.month]
        }
    }
}