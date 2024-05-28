package br.com.digio.androidtest.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.digio.androidtest.presentation.navigation.route.Routes
import br.com.digio.androidtest.presentation.ui.home.DigioHomeScreen

@Composable
internal fun NavigationGraph(navController: NavHostController) {

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {

        composable(route = Routes.Home.route) {
            DigioHomeScreen(viewModel = hiltViewModel(viewModelStoreOwner))
        }
    }
}