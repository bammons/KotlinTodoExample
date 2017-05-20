package com.example.bammons.todo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView


class MainActivity : AppCompatActivity() {
    val TAG : String = "MainActivity"
    lateinit var todoListView : ListView
    lateinit var listAdapter : ArrayAdapter<String>
    lateinit var taskList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoListView = findViewById(R.id.list_todo) as ListView
        taskList = ArrayList()
        listAdapter = ArrayAdapter(this, R.layout.item_todo, R.id.task_title, taskList)
        todoListView.adapter = listAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add_task -> {
                val taskEditText = EditText(this)
                val dialog = AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add") { dialog, which ->
                            val task = taskEditText.text.toString()
                            Log.d(TAG, "Task to add: $task")
                            taskList.add(task)
                            listAdapter.notifyDataSetChanged()
                        }
                        .setNegativeButton("Cancel", null)
                        .create()
                dialog.show()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
