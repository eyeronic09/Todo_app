package com.example.todoapp.HomeScreen

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [TodoItem::class],
    version = 1
)
abstract class Todo_db : RoomDatabase(){
    abstract fun todoDao(): todoDao

    companion object{
        @Volatile
        private var INSTANCE : Todo_db? = null
        fun getDatabase (context : Context): Todo_db{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = Todo_db::class.java,
                    name = "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }

}