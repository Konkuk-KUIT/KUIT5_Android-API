package com.example.kuitandroidapiexample.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kuitandroidapiexample.App
import com.example.kuitandroidapiexample.ui.detail.screen.DetailScreen
import com.example.kuitandroidapiexample.ui.detail.viewmodel.AnimalDetailViewModel
import com.example.kuitandroidapiexample.ui.detail.viewmodel.AnimalDetailViewModelFactory
import com.example.kuitandroidapiexample.ui.home.screen.HomeScreen
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModelFactory
import com.example.kuitandroidapiexample.ui.register.screen.RegisterScreen
import com.example.kuitandroidapiexample.ui.register.viewmodel.AnimalRegisterViewModel
import com.example.kuitandroidapiexample.ui.register.viewmodel.AnimalRegisterViewModelFactory

@Composable
fun MainNavHost(
    padding: PaddingValues
) {
    val navController = rememberNavController()

    val context = LocalContext.current.applicationContext as App
//    val viewModel: AnimalViewModel = viewModel(
//        factory = AnimalViewModelFactory(context.appContainer.provideApiService())
//    )
    val viewModel: AnimalViewModel = viewModel(
        factory = AnimalViewModelFactory(context.appContainer.provideAnimalRepository())
    )
    val detailViewModel: AnimalDetailViewModel = viewModel(
        factory = AnimalDetailViewModelFactory(context.appContainer.provideAnimalRepository())
    )
    val registerViewModel: AnimalRegisterViewModel = viewModel(
        factory = AnimalRegisterViewModelFactory(context.appContainer.provideAnimalRepository())
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
    }

}