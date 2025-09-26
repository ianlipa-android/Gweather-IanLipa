package com.example.gweather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R
import com.example.gweather.models.BottomNavItem
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(onLogout: () -> Unit = {}) {

    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val scrollScope = rememberCoroutineScope()
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Current Weather",
            iconRes = painterResource(R.drawable.app_logo),
        ), BottomNavItem(
            title = "Past visits",
            iconRes = painterResource(R.drawable.ic_password_eye_open)
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bottomNavItems.count() }
    )

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Transparent)
            ) {
                Text("Hi, ")
                Spacer(Modifier.weight(1f))
                Row(modifier = Modifier.fillMaxHeight().padding(end = 12.dp)
                    .clickable(onClick = onLogout),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End) {
                    Text(
                        fontSize = 16.sp,
                        text = "Logout",
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black,
                        painter = painterResource(R.drawable.logout),
                        contentDescription = "Login"
                    )
                }

            }
        },
        bottomBar = {
            BottomNav(
                currentIndex = tabIndex,
                onSelectTab = { index ->
                    tabIndex = pagerState.settledPage
                    scrollScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                navItems = bottomNavItems
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {

                HorizontalPager(pagerState) {
                    when (it) {
                        0 -> {
                            tabIndex = pagerState.currentPage
                            CurrentWeatherScreen()
                        }

                        1 -> {
                            tabIndex = pagerState.currentPage
                            WeatherRecordsScreen()
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewCurrentWeatherScreen() {
    HomeScreen()
}