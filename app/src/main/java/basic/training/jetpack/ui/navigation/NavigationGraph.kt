package basic.training.jetpack.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import basic.training.jetpack.data.model.NavigationItem
import basic.training.jetpack.ui.screens.DetailScreen
import basic.training.jetpack.ui.screens.HomeScreen
import basic.training.jetpack.ui.screens.ProfileScreen
import basic.training.jetpack.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    val items = listOf(
        NavigationItem(
            name = Constants.HOME,
            icon = Icons.Default.Home
        ),
        NavigationItem(
            name = Constants.PROFILE,
            icon = Icons.Default.Person
        )
    )

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val screenTitle = when(selectedItemIndex) {
        0 -> Constants.HOME
        1 -> Constants.PROFILE
        2 -> Constants.SETTINGS
        else -> Constants.HOME
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                title = { Text(text = screenTitle, color = Color.White) },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Show More", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(RoundedCornerShape(corner = CornerSize(30.dp)))
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        label = { Text(text = item.name, color = Color.White) },
                        alwaysShowLabel = false,
                        onClick = {
                            selectedItemIndex = index
                            val route = when(selectedItemIndex) {
                                0 -> Constants.HOME
                                1 -> Constants.PROFILE
                                else -> Constants.HOME
                            }

                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name,
                                tint = if (selectedItemIndex == index) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onPrimary
                                }
                            )
                        },
                    )
                }
            }
        }
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedItemIndex = when(destination.route) {
                Constants.HOME -> 0
                Constants.PROFILE -> 1
                else -> 0
            }
        }
        NavHost(navController = navController, startDestination = Constants.HOME, modifier = Modifier.padding(it)) {
            composable(Constants.HOME) {
                HomeScreen { id ->
                    navController.navigate("detail/$id")
                }
            }
            composable(Constants.PROFILE) {
                ProfileScreen()
            }
            composable(
                route = "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: ""
                DetailScreen(valkyrieId = id)
            }
        }
    }
}

