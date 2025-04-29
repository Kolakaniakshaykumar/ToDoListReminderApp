package tessides3399241.todolistapp.Kolakaniakshaykumar

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen()
        }
    }
}

@Composable
fun SignUpScreen() {
    var name by remember { mutableStateOf("") }
    var useremail by remember { mutableStateOf("") }
    var userpassword by remember { mutableStateOf("") }
    var confirmuserpassword by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.shade1))
            .padding(WindowInsets.systemBars.asPaddingValues())

    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.todolist_icon),
            contentDescription = "Back"
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, SignInActivity::class.java))
                context.finish()
            },
            modifier = Modifier
                .width(120.dp)
                .height(50.dp)
                .align(Alignment.End),
            shape = RoundedCornerShape(
                topStart = 16.dp, // Adjust radius as needed
                bottomStart = 16.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB0BEC5), // Adjust color as needed
                contentColor = Color.Black
            )
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.weight(0.5f))

        // Login title
        Text(
            text = "Register",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(0.2f))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            // Column with Text Fields
            Column(
                modifier = Modifier
                    .wrapContentHeight()

            )
            {

                // User Name TextField
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Enter FullName") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle, // Replace with desired icon
                            contentDescription = "Email Icon"
                        )
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .padding(vertical = 0.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = useremail,
                    onValueChange = { useremail = it },
                    label = { Text("Enter Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email, // Replace with desired icon
                            contentDescription = "Email Icon"
                        )
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .padding(vertical = 0.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = userpassword,
                    onValueChange = { userpassword = it },
                    label = { Text("Enter Password") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock, // Replace with desired icon
                            contentDescription = "Email Icon"
                        )
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .padding(vertical = 0.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))


                OutlinedTextField(
                    value = confirmuserpassword,
                    onValueChange = { confirmuserpassword = it },
                    label = { Text("Confirm Password") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock, // Replace with desired icon
                            contentDescription = "Email Icon"
                        )
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .padding(vertical = 0.dp)
                )


            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_circle_right_36),
                contentDescription = "Akshay Kumar Kolakani",
                modifier = Modifier
                    .clickable {
                        when {
                            useremail.isEmpty() -> {
                                Toast.makeText(context, "Email missing. Kindly provide it to proceed", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            name.isEmpty() -> {
                                Toast.makeText(context, "Name missing. Kindly provide it to proceed", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            userpassword.isEmpty() -> {
                                Toast.makeText(
                                    context,
                                    "Password missing. Kindly provide it to proceed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            else -> {
                                val appUserDetails = AppUserDetails(
                                    name,
                                    useremail,
                                    "",
                                    userpassword
                                )
                                signUpAppUser(appUserDetails, context)
                            }

                        }
                    }
            )

            Spacer(modifier = Modifier.weight(1f))

        }


        Spacer(modifier = Modifier.weight(1f))

    }
}

fun signUpAppUser(appUserDetails: AppUserDetails, context: Context) {

    val firebaseDatabase = FirebaseDatabase.getInstance()
    val databaseReference = firebaseDatabase.getReference("AppUserDetails")

    databaseReference.child(appUserDetails.emailid.replace(".", ","))
        .setValue(appUserDetails)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "You Registered Successfully", Toast.LENGTH_SHORT)
                    .show()

                context.startActivity(Intent(context, SignInActivity::class.java))
                (context as Activity).finish()

            } else {
                Toast.makeText(
                    context,
                    "Registration Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { _ ->
            Toast.makeText(
                context,
                "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()
        }
}

data class AppUserDetails(
    var name: String = "",
    var emailid: String = "",
    var area: String = "",
    var password: String = ""
)