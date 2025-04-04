package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.todolist.ui.theme.TodoListTheme


class TodoListFragment : Fragment(R.layout.fragment_todo_list) {

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.todoListFragment).setContent {
            TodoListTheme {

            }
        }
    }

}