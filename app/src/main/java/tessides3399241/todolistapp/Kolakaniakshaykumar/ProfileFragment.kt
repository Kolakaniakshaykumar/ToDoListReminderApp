package tessides3399241.todolistapp.Kolakaniakshaykumar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tessides3399241.todolistapp.Kolakaniakshaykumar.taskdata.TaskViewModel
import tessides3399241.todolistapp.Kolakaniakshaykumar.taskdata.TaskViewModelFactory
import tessides3399241.todolistapp.Kolakaniakshaykumar.ui.theme.TodoListTheme

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireActivity().application

        taskViewModel = ViewModelProvider(
            this,
            TaskViewModelFactory(application)
        )[TaskViewModel::class.java]

        view.findViewById<ComposeView>(R.id.profileData).setContent {
            TodoListTheme {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.soft_peach))
                .padding(horizontal = 12.dp, vertical = 4.dp),
        ) {
            Text(
                modifier = Modifier,
                text = "My Profile",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        context.startActivity(
                            Intent(
                                context,
                                tessides3399241.todolistapp.Kolakaniakshaykumar.AboutApplicationOwnerActivity::class.java
                            )
                        )

                    },
                painter = painterResource(id = R.drawable.app_info),
                contentDescription = "Info"
            )


        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = painterResource(id = R.drawable.iv_profile),
                contentDescription = "Profile"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Name : ${TaskManagerPrefs.fetchDisplayName(context)}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "EmailId : ${TaskManagerPrefs.getEmail(context)}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .clickable {
                        TaskManagerPrefs.setAuthStatus(context, false)

                        context.startActivity(Intent(context, SignInActivity::class.java))
                        (context as Activity).finish()
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(
                            10.dp
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.black),
                        shape = RoundedCornerShape(
                            10.dp
                        )
                    )
                    .padding(vertical = 12.dp, horizontal = 12.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Logout",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = colorResource(id = R.color.white),
                )
            )

        }
    }
}