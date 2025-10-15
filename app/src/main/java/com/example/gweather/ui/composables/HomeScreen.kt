package com.example.gweather.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gweather.R
import com.example.gweather.models.BottomNavItem
import com.example.gweather.models.UiState
import com.example.gweather.ui.composables.widgets.BottomNav
import com.example.gweather.utils.LocationUtils
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    userName: String? = "",
    weatherState: UiState = UiState.Initial,
    weatherListState: UiState = UiState.Initial,
    locationUtils: LocationUtils,
    onLogout: () -> Unit = {}
) {

    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val scrollScope = rememberCoroutineScope()
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Current Weather",
            selectedRes = painterResource(R.drawable.atmospheric_conditions_clr),
            unselectedRes = painterResource(R.drawable.atmospheric_conditions_bnw)
        ), BottomNavItem(
            title = "Weather history",
            selectedRes = painterResource(R.drawable.img_selected_list),
            unselectedRes = painterResource(R.drawable.img_unselected_list)
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bottomNavItems.count() }
    )

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {

                    HorizontalPager(
                        modifier = Modifier.fillMaxSize(),
                        state = pagerState
                    ) {
                        when (it) {
                            0 -> {
                                tabIndex = pagerState.currentPage
                                CurrentWeatherScreen(weatherState, locationUtils)
                            }

                            1 -> {
                                tabIndex = pagerState.currentPage
                                WeatherRecordsScreen(locationUtils = locationUtils, userWeatherListState = weatherListState)
                            }
                        }
                    }
                }
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .background(Color.Black.copy(0.5f)),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Row(modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Hi $userName,",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                                color = Color.White
                            )
                            Spacer(Modifier.weight(1f))
                            Row(
                                modifier = Modifier
                                    .clickable(onClick = onLogout)
                                    .background(color = Color.White, shape = CircleShape)
                                    .padding(vertical = 6.dp, horizontal = 14.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    fontSize = 14.sp,
                                    text = "Logout",
                                    color = Color.Black,
                                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black,
                                    painter = painterResource(R.drawable.logout),
                                    contentDescription = "Login"
                                )
                            }
                        }
                    }
                }
                Box(contentAlignment = Alignment.BottomCenter) {
                    BottomNav(
                        currentIndex = tabIndex,
                        onSelectTab = { index ->
                            tabIndex = pagerState.settledPage
                            scrollScope.launch {
                                pagerState.scrollToPage(index)
                            }
                        },
                        navItems = bottomNavItems
                    )
                }
            }
        }
    )
}

/*
@Preview
@Composable
fun PreviewCurrentWeatherScreen() {
    HomeScreen()
}*/
