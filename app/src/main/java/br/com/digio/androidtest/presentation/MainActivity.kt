package br.com.digio.androidtest.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.digio.androidtest.R
import br.com.digio.androidtest.commons.data.provider.network.ConnectivityObserver
import br.com.digio.androidtest.commons.data.provider.network.NetworkConnectivityObserver
import br.com.digio.androidtest.presentation.navigation.graph.NavigationGraph
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initConnectivityObserver()
        setContent {
            AndroidTestTheme {
                CheckConnection()

                navHostController = rememberNavController()
                NavigationGraph(navController = navHostController)
            }
        }
    }

    private fun initConnectivityObserver() {
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
    }

    @Composable
    private fun CheckConnection() {
        val status by connectivityObserver.observe()
            .collectAsState(initial = ConnectivityObserver.Status.Unavailable)

        if (status == ConnectivityObserver.Status.Lost) {
            Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
    }
}
