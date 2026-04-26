package com.example.smartplantcare


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartplantcare.ui.theme.screens.*
import com.example.smartplantcare.ui.theme.PlantAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color    = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController    = navController,
                        startDestination = Routes.SPLASH1
                    ) {
                        composable(Routes.SPLASH1) {
                            SplashScreen1(
                                onGetStarted = { navController.navigate(Routes.SPLASH2) }
                            )
                        }
                        composable(Routes.SPLASH2) {
                            SplashScreen2(
                                onNext = { navController.navigate(Routes.SPLASH3) },
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.SPLASH3) {
                            SplashScreen3(
                                onCreateAccount = { navController.navigate(Routes.SIGN_UP) },
                                onLogin         = { navController.navigate(Routes.LOGIN) }
                            )
                        }
                        composable(Routes.LOGIN) {
                            LoginScreen(
                                onBack   = { navController.popBackStack() },
                                onSignUp = {
                                    navController.navigate(Routes.SIGN_UP) {
                                        popUpTo(Routes.SPLASH3)
                                    }
                                }
                            )
                        }
                        composable(Routes.SIGN_UP) {
                            SignUpScreen(
                                onBack    = { navController.popBackStack() },
                                onLogin   = {
                                    navController.navigate(Routes.LOGIN) {
                                        popUpTo(Routes.SPLASH3)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}