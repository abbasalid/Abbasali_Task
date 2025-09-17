package com.example.abbasali_task.presentation.ui.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.abbasali_task.presentation.theme.StockApp

@Composable
fun CustomBottomSheet(
    expanded: Boolean,
    onToggle: () -> Unit,
    peekContent: @Composable () -> Unit,
    expandedContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    peekHeight: Dp = 75.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (expanded) Modifier.wrapContentHeight()
                else Modifier.height(peekHeight)
            )
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(StockApp.colors.cardColor)
            .clickable { onToggle() }
            .padding(StockApp.spacing.large)

    ) {
        if (expanded) {
            expandedContent()
        } else {
            peekContent()
        }
    }
}


@Composable
@Preview
private fun CustomBottomSheet_Preview(){
    CustomBottomSheet(
        expanded = true,
        onToggle = {},
        peekContent = { Text("Hi") },
        expandedContent = { Text("Hi")},
    )
}
