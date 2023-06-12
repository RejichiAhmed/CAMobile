package com.example.testmobileca.ui.details_account


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testmobileca.base.BaseScreen
import com.example.testmobileca.data.model.Operation
import com.example.testmobileca.ui.accounts.BodyContent
import com.example.testmobileca.ui.accounts.MyAccountsViewModel
import com.example.testmobileca.ui.components.CATopBar
import com.example.testmobileca.ui.components.CollapsableLazyColumn
import com.example.testmobileca.ui.components.ProgressBar
import com.example.testmobileca.utils.collapsableSections
import com.example.testmobileca.utils.timestampToDate


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsAccountScreen(
    viewModel: DetailsAccountViewModel = hiltViewModel(),
) {
    BaseScreen(viewModel = viewModel,
        topBar = {
            CATopBar(
                title = "Mes comptes", titleColor = Color.Blue, leftIcon = Icons.Filled.ArrowBack
            ) { viewModel.onBackClicked() }
        }) {

        val isLoading by viewModel.isLoading

        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(color = Color.White)

        ) {
            BodyContent(viewModel)
            ProgressBar(isLoading = isLoading)
        }
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyContent(
    viewModel: DetailsAccountViewModel
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth()
    ) {

        val account = viewModel.account.collectAsState()
        val operations = viewModel.operations.collectAsState()
        Box {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = account.value.balance + "€",
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = account.value.label,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
                LazyColumn {
                    items(operations.value) { operation ->
                        OperationItemList(operation)

                    }
                }

            }
        }
    }
}

@Composable
fun OperationItemList(operation: Operation) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = operation.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = timestampToDate(operation.date.toLong()),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                modifier = Modifier.padding(16.dp),
                text = operation.amount + "€",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.LightGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
