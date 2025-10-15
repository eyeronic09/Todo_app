package com.example.todoapp.HomeScreen

import android.app.Application
import android.content.Context
import com.example.todoapp.HomeScreen.Presentation_layer.TodoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.scope.get
import org.koin.dsl.module

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}

val appModule = module {
    single { Todo_db.getDatabase(get()) }
    single { get<Todo_db>().todoDao() }
    single<TodoRepository> { TodoRepositoryImp(get()) }

    viewModelOf(::TodoViewModel)
}