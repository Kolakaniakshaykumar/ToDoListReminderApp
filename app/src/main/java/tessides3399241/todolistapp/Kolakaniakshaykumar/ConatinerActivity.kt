package tessides3399241.todolistapp.Kolakaniakshaykumar

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
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
            openFragment(tessides3399241.todolistapp.Kolakaniakshaykumar.TodoListFragment())
        }

        btUpComingTask.setOnClickListener {
            openFragment(tessides3399241.todolistapp.Kolakaniakshaykumar.UpComingTaskFragment())
        }

        btMyProfile.setOnClickListener {
            openFragment(tessides3399241.todolistapp.Kolakaniakshaykumar.ProfileFragment())
        }
    }

    private fun openFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }
}