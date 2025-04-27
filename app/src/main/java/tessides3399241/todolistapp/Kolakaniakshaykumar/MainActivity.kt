package tessides3399241.todolistapp.Kolakaniakshaykumar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrandDisplay(this)
        }
    }
}

@Composable
fun BrandDisplay(fragmentActivity: FragmentActivity) {
    val context = LocalContext.current as Activity

    DisposableEffect(Unit) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            delay(3000)

//            if(TaskManagerPrefs.isUserAuthenticated(context))
//            {
//                context.startActivity(Intent(context, tessides3399241.todolistapp.Kolakaniakshaykumar.ConatinerActivity::class.java))
//                context.finish()
//            }else{
//                context.startActivity(Intent(context, SignInActivity::class.java))
//                context.finish()
//            }

            if (TaskManagerPrefs.isUserAuthenticated(context)) {
                val biometricManager = BiometricManager.from(fragmentActivity)
                if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS) {
                    val executor = ContextCompat.getMainExecutor(fragmentActivity)
                    val biometricPrompt =
                        BiometricPrompt(
                            fragmentActivity,
                            executor,
                            object : BiometricPrompt.AuthenticationCallback() {
                                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                                    super.onAuthenticationSucceeded(result)
                                    context.startActivity(
                                        Intent(
                                            context,
                                            ConatinerActivity::class.java
                                        )
                                    )
                                    context.finish()
                                }

                                override fun onAuthenticationError(
                                    errorCode: Int,
                                    errString: CharSequence
                                ) {
                                    super.onAuthenticationError(errorCode, errString)
                                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG)
                                        .show()
                                }

                                override fun onAuthenticationFailed() {
                                    super.onAuthenticationFailed()
                                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG)
                                        .show()
                                }
                            })

                    val promptInfo = BiometricPrompt.PromptInfo.Builder()
                        .setTitle("FingerPrint Verification")
                        .setSubtitle("Place your finger to continue")
                        .setNegativeButtonText("Close")
                        .build()

                    biometricPrompt.authenticate(promptInfo)
                } else {
                    Toast.makeText(
                        fragmentActivity,
                        "This device doesn't support fingerprint",
                        Toast.LENGTH_LONG
                    ).show()
                    context.startActivity(Intent(context, ConatinerActivity::class.java))
                    context.finish()

                }
            } else {
                context.startActivity(Intent(context, SignInActivity::class.java))
                context.finish()
            }
        }
        onDispose { job.cancel() }
    }

    BrandDisplayScreen()
}

@Composable
fun BrandDisplayScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.shade1)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Welcome to",
                color = colorResource(id = R.color.soft_peach),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Akshay Kumar Kolakani",
                color = colorResource(id = R.color.soft_peach),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 18.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Image(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.todolist_icon),
                contentDescription = "Back"
            )

            Spacer(modifier = Modifier.weight(1f))

        }
    }

}
