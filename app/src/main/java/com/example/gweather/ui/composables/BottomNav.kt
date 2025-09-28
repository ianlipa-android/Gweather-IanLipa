package com.example.gweather.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R
import com.example.gweather.models.BottomNavItem

@Composable
fun BottomNav(
    currentIndex: Int = 0,
    onSelectTab: (Int) -> Unit = {},
    navItems: List<BottomNavItem>
) {

    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        Box(
            modifier = Modifier
                .height(120.dp)
                .background(
                    color = Color.White.copy(0.8f),
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize().padding(bottom = 24.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                navItems.forEachIndexed { index, item ->
                    isSelected = currentIndex == index
                    NavigationBarItem(
                        modifier = Modifier
                            .wrapContentSize(),
                        selected = isSelected,
                        interactionSource = null,
                        onClick = {
                            onSelectTab(index)
                        },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(if (isSelected) 100.dp else 50.dp)
                                    .offset(y = if (isSelected) 0.dp else 30.dp).animateContentSize()
                                    .padding(bottom = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxSize().animateContentSize(),
                                    contentScale = ContentScale.FillHeight,
                                    painter = if (isSelected)
                                        item.selectedRes else
                                        item.unselectedRes,
                                    contentDescription = "icon"
                                )
                            }
                        },
                        label = {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .fillMaxWidth()
                                    .offset(y = if (isSelected) 0.dp else 22.dp)
                                    .animateContentSize(),
                                text = item.title!!,
                                maxLines = 1,
                                textAlign = TextAlign.Center,
                                fontWeight = if (isSelected) FontWeight.Black else FontWeight.Medium,
                                color = if (isSelected)
                                    Color.Blue
                                else Color.Black,
                                fontSize = if (isSelected) 16.sp else 14.sp
                            )

                        },
                        colors = NavigationBarItemDefaults.colors().copy(
                            selectedIndicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent,
                            selectedIconColor = Color.Transparent,
                            unselectedTextColor = Color.Transparent,
                            selectedTextColor = Color.Transparent,
                            disabledIconColor = Color.Transparent,
                            disabledTextColor = Color.Transparent
                        )
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun BottomNavPreview() {
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Current Weather",
            selectedRes = painterResource(R.drawable.atmospheric_conditions_clr),
            unselectedRes = painterResource(R.drawable.atmospheric_conditions_bnw)
        ), BottomNavItem(
            title = "Past visits",
            selectedRes = painterResource(R.drawable.img_selected_list),
            unselectedRes = painterResource(R.drawable.img_unselected_list)

        )
    )
    BottomNav(navItems = bottomNavItems)
}