package com.example.gweather.ui.composables

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.gweather.models.BottomNavItem

@Composable
fun BottomNav(currentIndex: Int, onSelectTab: (Int) -> Unit, navItems: List<BottomNavItem>) {

    NavigationBar(containerColor = Color.White) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentIndex == index,
                onClick = {
                    onSelectTab(index)
                },
                icon = { item.iconRes },
                label = {
                    Text(
                        item.title!!,
                        color = if (index == currentIndex)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors()
            )
        }
    }
}