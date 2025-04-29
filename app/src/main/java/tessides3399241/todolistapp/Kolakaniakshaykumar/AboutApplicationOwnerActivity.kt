package tessides3399241.todolistapp.Kolakaniakshaykumar

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AboutApplicationOwnerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AboutApplicationOwnerScreen()
        }
    }
}

@Composable
fun AboutApplicationOwnerScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues())
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.soft_peach))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clickable { (context as Activity).finish() },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "Back"
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "App Info",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }


        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFE1F5FE))
                    .padding(16.dp)
            ) {
                Column {
                    Text("Contact Us", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Akshay Kumar Kolakani")
                    Text("Email: kolakaniakshaykumar22@gmail.com")
                    Text("Student ID: S3399241")
                }
            }



            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFFFF9C4))
                    .padding(16.dp)
            ) {
                Column {
                    Text("About Us", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Welcome to the To-Do List with Reminders App – your personal productivity companion!\n" +
                                "Created by Akshay Kumar Kolakani, this app is designed to help you stay organized, focused, and on top of your daily tasks. Whether you're managing work deadlines, personal goals, or simple everyday chores, our app makes it easy to plan, prioritize, and complete your to-do list.\n" +
                                "At To-Do List with Reminders, we believe productivity should be simple and accessible to everyone. \n" +
                                "Thanks for choosing us to help you stay organized – one task at a time!\n"
                    )
                }
            }
        }
    }
}
