package basic.training.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import basic.training.jetpack.ui.navigation.NavigationGraph
import basic.training.jetpack.ui.theme.Hi3rdJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hi3rdJetpackTheme {
                NavigationGraph()
            }
        }
    }
}
