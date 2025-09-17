package com.example.abbasali_task.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.abbasali_task.presentation.theme.StockTheme
import com.example.abbasali_task.presentation.vm.PortfolioViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PortfolioViewmodel by viewModels()

    private val callback = object : PortfolioScreenCallbacks {
        override val onBackPressed: () -> Unit
            get() = { this.onBackPressed() }

        override val onErrorDismissed: () -> Unit

            get() = { viewModel.clearError() }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val state by viewModel.uiState.collectAsStateWithLifecycle()

            StockTheme {
                PortfolioScreen(state,callback)
            }
        }
    }
}