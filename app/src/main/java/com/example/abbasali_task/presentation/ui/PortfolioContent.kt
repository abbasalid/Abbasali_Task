package com.example.abbasali_task.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.abbasali_task.presentation.vm.UIState

@Composable
fun PortfolioContent(
    uiState: UIState,
    callback: PortfolioScreenCallbacks,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        uiState.error?.let { error ->
            ErrorMessage(
                error = error,
                onDismiss = {
                    callback.onErrorDismissed.invoke()
                },
            )
        }
        LazyColumn {
            items(
                items = uiState.holdings,
                key = { it.name }
            ) { stock ->
                HoldingItem(stock = stock)
            }
        }
    }
}

@Composable
@Preview
private fun PortfolioContent_Preview() {
    PortfolioContent(
        uiState = UIState(),
        callback = object : PortfolioScreenCallbacks {
            override val onBackPressed: () -> Unit
                get() = {}
            override val onErrorDismissed: () -> Unit
                get() = { }

        }
    )
}