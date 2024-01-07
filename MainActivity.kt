package com.stecavalli.flashlight                               // This line of code must be modified with the Package Name chosen when creating the project.
                                                                
import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
                                                                                                                                
import com.stecavalli.flashlight.ui.theme.FlashlightTheme       // This line of code must be modified with the Package Name chosen when creating the project.

class MainActivity : ComponentActivity() {
    private var isFlashOn by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashlightTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        TorchButton(isFlashOn) {
                            isFlashOn = !isFlashOn
                                toggleFlashlight(isFlashOn)
                        }
                    }
                }
            }
        }
    }

    private fun toggleFlashlight(isOn: Boolean) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try {
            val cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, isOn)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
            // Gestire l'eccezione appropriata, ad esempio, mostrando un messaggio all'utente
        }
    }

}

@Composable
fun TorchButton(isOn: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        Text(if (isOn) "Turn Off Torch" else "Turn On Torch")
    }
}
