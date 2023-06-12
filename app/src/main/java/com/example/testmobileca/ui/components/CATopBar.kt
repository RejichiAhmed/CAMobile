package com.example.testmobileca.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CATopBar(
    title: String,
    titleColor: Color,
    leftIcon: ImageVector,
    leftIconClickListener: () -> Unit
) {
    Surface(
        modifier = Modifier.wrapContentSize(),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = titleColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            },
            navigationIcon = {
                IconButton(onClick = leftIconClickListener) {
                    Icon(
                        imageVector = leftIcon,
                        contentDescription = null,
                        tint = Color.Blue
                    )
                }
            },
            backgroundColor = Color.LightGray
        )
    }
}