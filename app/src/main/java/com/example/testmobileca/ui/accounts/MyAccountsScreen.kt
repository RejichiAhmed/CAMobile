package com.example.testmobileca.ui.accounts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testmobileca.R
import com.example.testmobileca.base.BaseScreen
import com.example.testmobileca.ui.components.CollapsableLazyColumn
import com.example.testmobileca.ui.components.ProgressBar
import com.example.testmobileca.utils.collapsableSections


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyAccountsScreen(
    viewModel: MyAccountsViewModel = hiltViewModel(),
) {
    BaseScreen(viewModel = viewModel) {

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
    viewModel: MyAccountsViewModel
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth()
    ) {

        val banksCA = viewModel.banksCA.collectAsState()

        val otherBanks = viewModel.otherBanks.collectAsState()

        Box {
            Column {
                Row(
                    modifier = Modifier
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.my_accounts_title),
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.section_title_ca),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }


                CollapsableLazyColumn(collapsableSections(banksCA),viewModel)

                Row(
                    modifier = Modifier
                        .background(color = Color.LightGray)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.section_title_others),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
                CollapsableLazyColumn(collapsableSections(otherBanks),viewModel)
            }
        }

    }
}



