package com.example.abbasali_task.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.abbasali_task.R
import com.example.abbasali_task.presentation.vm.UIState
import androidx.compose.ui.unit.dp
import com.example.abbasali_task.presentation.ui.summary.PortfolioSummaryBottomSheet

interface PortfolioScreenCallbacks {
    val onBackPressed: () -> Unit
    val onErrorDismissed: () -> Unit
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioScreen(
    state: UIState,
    callback: PortfolioScreenCallbacks
) {
    val peekHeight = 70.dp
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
                title = { Text(stringResource(R.string.portfolio)) }
            )
        },
    ) { padding ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f))
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            PortfolioContent(
                uiState = state,
                callback = callback,
                Modifier.padding(bottom = peekHeight)
            )

            PortfolioSummaryBottomSheet(
                modifier = Modifier.align(Alignment.BottomCenter),
                state = state,
                peekHeight = peekHeight
            )
        }
    }
}

@Preview
@Composable
private fun PortFolioScreen_Preview() {
    PortfolioScreen(state = UIState(), callback = object : PortfolioScreenCallbacks {
        override val onBackPressed: () -> Unit
            get() = {}
        override val onErrorDismissed: () -> Unit
            get() = { }
    })
}