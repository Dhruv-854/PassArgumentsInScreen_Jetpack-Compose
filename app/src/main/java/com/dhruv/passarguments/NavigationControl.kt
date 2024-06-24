package com.dhruv.passarguments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.StabilityInferred
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Composable
fun NavigationControl(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen(
                onClick = {
                    navController.navigate(Signup)
                }
            )
        }
        composable<Signup> {
            SignUpScreen(
                onClick = {it
                    navController.navigate(Home(
                        name = it
                    ))
                }
            )
        }
        composable<Home> {
            val args = it.toRoute<Home>()
            HomeScreen(
                name = args.name
            ) {
                navController.navigateUp()
            }
        }
    }
}


@Serializable
object Login
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "LoginScreen", modifier = Modifier.clickable {
            onClick()
        })
    }
}



@Serializable
object Signup
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {

    val name = remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(value = name.value, onValueChange = {
            name.value = it
        })

        Text(text = "SignUpScreen",
            modifier = Modifier.clickable {
                onClick(name.value)
            }
        )
    }

}


@Serializable
data class Home(
    val name : String
)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    name : String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, fontSize = 30.sp)
        Text(text = "HomeScreen", modifier = modifier.clickable {
            onClick()
        })
    }
}


