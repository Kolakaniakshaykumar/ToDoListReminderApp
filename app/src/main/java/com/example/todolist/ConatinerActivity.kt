package com.example.todolist

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class ConatinerActivity : AppCompatActivity() {

    private lateinit var btManageTask : LinearLayout
    private lateinit var btUpComingTask : LinearLayout
    private lateinit var btMyProfile : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conatiner)

        btManageTask=findViewById(R.id.btManageTasks)
        btUpComingTask=findViewById(R.id.btUpcomingTasks)
        btMyProfile=findViewById(R.id.btMyProfile)

        openFragment(TodoListFragment())

        onClickListener()
    }

    private fun onClickListener()
    {
        btManageTask.setOnClickListener {
            openFragment(TodoListFragment())
        }

        btUpComingTask.setOnClickListener {
            openFragment(UpComingTaskFragment())
        }

        btMyProfile.setOnClickListener {

        }
    }

    private fun openFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }
}