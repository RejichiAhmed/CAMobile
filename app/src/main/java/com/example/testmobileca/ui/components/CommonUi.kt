package com.example.testmobileca.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp



@Composable
fun ProgressBar(isLoading: Boolean) {
    return AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(Color.Gray.copy(0.3f))
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(alignment = Alignment.Center),
                strokeWidth = 2.dp,
                color = Color.Red.copy(1f)
            )
        }
    }
}

