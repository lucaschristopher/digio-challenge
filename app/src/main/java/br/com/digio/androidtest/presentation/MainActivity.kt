package br.com.digio.androidtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.digio.androidtest.presentation.navigation.graph.NavigationGraph
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTestTheme {
                navHostController = rememberNavController()
                NavigationGraph(navController = navHostController)
            }
        }
    }
}
