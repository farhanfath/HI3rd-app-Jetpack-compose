package basic.training.jetpack.ui.view.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import basic.training.jetpack.data.NavigationItem
import basic.training.jetpack.ui.view.screen.HomeScreen
import basic.training.jetpack.ui.view.screen.ProfileScreen
import basic.training.jetpack.ui.view.screen.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    val items = listOf(
        NavigationItem(
            name = "Home",
            icon = Icons.Default.Home
        ),
        NavigationItem(
            name = "Profile",
            icon = Icons.Default.Person
        ),
        NavigationItem(
            name = "Settings",
            icon = Icons.Default.Settings
        )
    )

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val screenTitle = when(selectedItemIndex) {
        0 -> "Home"
        1 -> "Profile"
        2 -> "Settings"
        else -> "Home"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                title = { Text(text = screenTitle) },
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
                modifier = Modifier.padding(top = 2.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        label = { Text(text = item.name, color = Color.White) },
                        alwaysShowLabel = false,
                        onClick = {
                            selectedItemIndex = index
                            val route = when(selectedItemIndex) {
                                0 -> "home"
                                1 -> "profile"
                                2 -> "settings"
                                else -> "home"
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
                "Home" -> 0
                "Profile" -> 1
                "Settings" -> 2
                else -> 0
            }
        }
        NavHost(navController = navController, startDestination = "Home", modifier = Modifier.padding(it)) {
            composable("Home") {
                HomeScreen()
            }
            composable("Profile") {
                ProfileScreen()
            }
            composable("Settings") {
                SettingsScreen()
            }
        }
    }
}

