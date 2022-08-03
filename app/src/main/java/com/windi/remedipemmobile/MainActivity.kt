package com.windi.remedipemmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.windi.remedipemmobile.adapter.todo_adapter
import com.windi.remedipemmobile.models.Todo
import java.util.*

class MainActivity : AppCompatActivity() {
    var todoList = arrayListOf<Todo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoRecycler = findViewById<RecyclerView>(R.id.todo_recycler)
        todoList = arrayListOf(
            Todo(
                title="Apple Event",
                description = "Speaker Tim Cook",
                date = Date(2017-1900,11-1,25,4,0)
            ),
            Todo(
                title="Twitter Event",
                description = "Speaker Jack Dorsey",
                date = Date(2017-1900,11-1,1,7,0)
            ),
            Todo(
                title="Google Event",
                description = "Speaker Larry Page",
                date = Date(2017-1900,10-1,18,2,0)
            )
        )
        todoRecycler.adapter = todo_adapter(todoList)
        todoRecycler.layoutManager = LinearLayoutManager(this)

        val addNewEventButton = findViewById<Button>(R.id.add_button)
        val addNewEventLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                try{
                    val value = data?.getParcelableExtra<Todo>("Event")
                    todoList.add(
                        Todo(
                            title = value?.title!!,
                            description = value.description,
                            date = value.date
                        )
                    )
                    todoRecycler.adapter = todo_adapter(todoList)
                } catch (e : Exception){
                    Toast.makeText(this, "error ${e.message}", Toast.LENGTH_SHORT).show()
                }
                
            }

        }
        addNewEventButton.setOnClickListener {
            val intent = Intent(this, AddNewEvent :: class.java)
            addNewEventLauncher.launch(intent)
        }
    }


}