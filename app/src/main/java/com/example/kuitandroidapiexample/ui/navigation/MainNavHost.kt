package com.example.kuitandroidapiexample.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kuitandroidapiexample.App
import com.example.kuitandroidapiexample.KuitApplication
import com.example.kuitandroidapiexample.ui.detail.screen.DetailScreen
import com.example.kuitandroidapiexample.ui.detail.viewmodel.AnimalDetailViewModel
import com.example.kuitandroidapiexample.ui.home.screen.HomeScreen
import com.example.kuitandroidapiexample.ui.home.screen.PreferencesScreen
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.ui.register.screen.RegisterScreen
import com.example.kuitandroidapiexample.ui.register.viewmodel.RegisterViewModel
import com.example.kuitandroidapiexample.ui.register.viewmodel.RegisterViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun MainNavHost(
    padding: PaddingValues,
) {
    val navController = rememberNavController()

    val context = LocalContext.current.applicationContext as KuitApplication
    val viewModel: AnimalViewModel = hiltViewModel()
    val detailViewModel: AnimalDetailViewModel = hiltViewModel()
    val registerViewModel: RegisterViewModel = hiltViewModel()

    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            detailViewModel.uiState.collect {
                if (it.isDelete) {
                    Log.d("s", "s")
                } else {
                    Log.d("it.toString()", it.toString())
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeScreen(
                padding = padding,
                navigateToRegister = {
                    navController.navigate(Route.Register) {}
                },
                navigateToDetail = { index ->

                    navController.navigate(Route.Detail(index))
                },
                navigateToPref = { navController.navigate(Route.Preference) },
                viewModel = viewModel
            )
        }
        composable<Route.Register> {
            RegisterScreen(
                padding = padding,
                navigateToBack = { navController.navigateUp() },
                viewModel = registerViewModel
            )
        }
        composable<Route.Detail> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<Route.Detail>()
            DetailScreen(
                padding = padding,
                index = args.index,
                navigateToBack = { navController.navigateUp() },
                viewModel = detailViewModel
            )
        }
        composable<Route.Preference> {
            PreferencesScreen()
        }
    }
}