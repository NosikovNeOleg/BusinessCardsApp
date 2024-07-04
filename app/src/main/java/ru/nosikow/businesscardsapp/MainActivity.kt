package ru.nosikow.businesscardsapp

import android.app.PendingIntent
import android.content.Intent
import android.content.res.Configuration
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import ru.nosikow.businesscardsapp.ui.compose.tabs.CardsTab
import ru.nosikow.businesscardsapp.ui.compose.tabs.UserTab
import ru.nosikow.businesscardsapp.ui.compose.views.Views

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }

    }



}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home() {
    var tabState by remember { mutableIntStateOf(0) }

    val setState = {value: Int -> tabState = value}
    val pagerState = rememberPagerState {
        2
    }
    LaunchedEffect(key1 = tabState) {
        pagerState.animateScrollToPage(tabState)
    }
    LaunchedEffect(
        key1 = pagerState.currentPage,
        key2 = pagerState.isScrollInProgress
    ) {
        if (!pagerState.isScrollInProgress) {
            setState(pagerState.currentPage)
        }

    }
    Surface() {
        Column (Modifier.fillMaxSize() ) {
            TabRow(selectedTabIndex = tabState) {
                UserTab(tabState,0, setState);
                CardsTab(tabState,1, setState);
            }
            HorizontalPager(
                state = pagerState,
                Modifier.fillMaxWidth()
            ) { index ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Views(index)
                }
            }
        }

    }

}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)

@Composable
fun PreviewHome()
{
    Home()
}