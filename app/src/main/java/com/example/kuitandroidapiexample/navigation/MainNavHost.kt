package com.example.kuitandroidapiexample.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kuitandroidapiexample.App
import com.example.kuitandroidapiexample.detail.screen.DetailScreen
import com.example.kuitandroidapiexample.detail.viewmodel.AnimalDetailViewModel
import com.example.kuitandroidapiexample.detail.viewmodel.AnimalDetailViewModelFactory
import com.example.kuitandroidapiexample.home.screen.HomeScreen
import com.example.kuitandroidapiexample.home.screen.PreferenceScreen
import com.example.kuitandroidapiexample.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.home.viewmodel.AnimalViewModel.AnimalViewModelFactory
import com.example.kuitandroidapiexample.register.screen.RegisterScreen
import com.example.kuitandroidapiexample.register.viewmodel.RegisterViewModel
import com.example.kuitandroidapiexample.register.viewmodel.RegisterViewModelFactory


@Composable
fun MainNavHost(
    padding: PaddingValues
) {
    val navController = rememberNavController()

    val context = LocalContext.current.applicationContext as App
    val viewModel: AnimalViewModel = viewModel(
        factory = AnimalViewModelFactory(context.appContainer.provideRepository())
    )
    val detailViewModel : AnimalDetailViewModel = viewModel(
       factory = AnimalDetailViewModelFactory(context.appContainer.provideRepository())
        // factory = AnimalDetailViewModelFactory(context.appContainer.provideRepository())
    )
    val registerViewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModelFactory(context.appContainer.provideRepository())
    )

    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeScreen(
                padding = padding,
                navigateToRegister = { navController.navigate(Route.Register) },
                navigateToDetail = { index ->
                    navController.navigate(Route.Detail(index))
                },
                navigateToPref = {navController.navigate(Route.Preference)},
                viewModel=viewModel
            )
        }
        composable<Route.Register> {
            RegisterScreen(
                padding = padding,
                navigateToBack = { navController.navigateUp() },
                viewModel= registerViewModel

            )
        }
        composable<Route.Detail> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<Route.Detail>()

            DetailScreen(
                padding = padding,
                index = args.index,
                navigateToBack = { navController.navigateUp() },
                viewModel= detailViewModel
            )
        }

        composable<Route.Preference>{
            PreferenceScreen()
        }
    }

}