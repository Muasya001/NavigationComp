package com.example.navigationcomp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationcomp.ui.theme.NavigationCompTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationCompTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()

                }
            }
        }
    }
}

@Composable
fun NavigationController(navController: NavHostController){
    NavHost(navController =navController, startDestination= NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
            Home()
        }
        composable(NavigationItem.Notifications.route) {
            Notifications()
        }
        composable(NavigationItem.Settings.route) {
            Settings()
        }
        composable(NavigationItem.Accounts.route) {
            Accounts()
        }


    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Settings,
        NavigationItem.Notifications,
        NavigationItem.Accounts
    )

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Kibabii  Sacco") }) },
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
               
                items.forEach {
                    BottomNavigationItem(selected = currentRoute==it.route,
                        label = {
                                Text(text = it.label,
                                    color = if (currentRoute==it.route) Color.DarkGray else Color.LightGray)
                        },
                        icon = {
                               Icon(imageVector = it.icons,contentDescription = null,
                                   tint = if (currentRoute==it.route) Color.DarkGray else Color.LightGray
                               )
                        },
                        onClick = {
                           if (currentRoute!=it.route){
                               navController.graph.startDestinationRoute?.let { navController.popBackStack(it
                               ,true) }
                               navController.navigate(it.route){
                                   launchSingleTop = true
                               }

                            }
                        })


                }

                }
            }) {
        NavigationController(navController = navController)

    }
}


@Composable
fun Home(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Home")

    }
}

 @Composable
fun Settings(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Settings")
        }
    }

}
@Composable
fun Notifications(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Notifications")
        }
    }

}
@Composable
fun Accounts(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Accounts")
        }
    }

}
