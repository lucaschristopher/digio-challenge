package br.com.digio.androidtest.presentation.navigation.route

private const val HOME_SCREEN = "home"
internal sealed class Routes(val route: String) {
    data object Home : Routes(HOME_SCREEN)
}
