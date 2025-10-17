package com.example.todoapp.HomeScreen.Data_layer.local.dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.HomeScreen.Data_layer.local.dao.model.TodoItem
import com.example.todoapp.HomeScreen.Data_layer.local.dao.todoDao

@Database(
    entities = [TodoItem::class],
    version = 1,
    exportSchema = true
)
abstract class Todo_db : RoomDatabase(){
    abstract fun todoDao(): todoDao

    companion object{
        @Volatile
        private var INSTANCES : Todo_db? = null
        fun getDatabase(context: Context) : Todo_db{
            return INSTANCES?: synchronized(this){
                val instances = Room.databaseBuilder(
                    context = context.applicationContext ,
                    klass = Todo_db::class.java,
                    name = "todo_database"
                ).build()
                INSTANCES = instances
                instances
            }
        }
    }
}