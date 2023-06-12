package com.example.testmobileca.base

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.testmobileca.ui.theme.TestMobileCATheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding

/**
 * The Home screen.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun <T> BaseScreen(
    viewModel: T,
    topBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    body: @Composable () -> Unit
) where T : BaseAndroidViewModel {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    ProvideWindowInsets {
        TestMobileCATheme {
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier.systemBarsPadding(),
                floatingActionButton = fab,
                topBar = topBar,
                snackbarHost = {
                    scaffoldState.snackbarHostState
                }
            ) {
                body()
            }
        }
    }
    RegisterBaseObservers(viewModel, scaffoldState)
}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalMaterialApi
@Composable
private fun <T> RegisterBaseObservers(
    viewModel: T,
    scaffoldState: ScaffoldState
) where T : BaseAndroidViewModel {
    val coroutineScope = rememberCoroutineScope()

    val snackHostState = remember { scaffoldState.snackbarHostState }

    val showProgressBar by viewModel.progressBar.collectAsState(false)

    RegisterProgressBar(showProgressBar)


}


@Composable
private fun RegisterProgressBar(showProgressBar: Boolean) {
    BackHandler(enabled = showProgressBar) {
        return@BackHandler
    }
    if (showProgressBar) {
        ShowProgressBar()
    }
}

@Composable
private fun ShowProgressBar() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .clickable { return@clickable },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}