package com.example.testmobileca.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobileca.ui.accounts.MyAccountsViewModel
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.utils.calculatedTotalBalance

@ExperimentalFoundationApi
@Composable
fun CollapsableLazyColumn(
    sections: List<CollapsableSection>,
    viewModel: MyAccountsViewModel
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }
    LazyColumn(content = {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = if (dataItem.title == "") {
                false
            } else {
                collapsedState[i]
            }
            item(key = "header_$i") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .combinedClickable(
                            onClick = {
                                collapsedState[i] = !collapsed
                            })

                ) {
                    headerSubList(dataItem.title, collapsed, calculatedTotalBalance(dataItem.rows))
                }
                Divider()
            }
            if (!collapsed) {
                items(dataItem.rows) { row ->
                    BankListItem(row, viewModel)
                    Divider()
                }
            }
        }
    })
}

@Composable
fun headerSubList(title: String, collapsed: Boolean, totalBalance: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(
                Color.White
            )
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            .alpha(1f)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            modifier = Modifier
                .wrapContentWidth()
                .height(19.dp)
                .background(Color.Transparent)
                .alpha(1f)
        ) {


            Text(
                text = "$title - $totalBalance €",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                textDecoration = TextDecoration.None,
                letterSpacing = 0.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentWidth()
                    .alpha(1f),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            )
        }

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            modifier = Modifier
                .width(16.dp)
                .height(16.dp)
                .background(Color.Transparent)
                .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                .alpha(1f)

        ) {

            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .background(Color.Transparent)
                    .alpha(1f)
            ) {
                Icon(
                    painter = if (collapsed) painterResource(id = com.example.testmobileca.R.drawable.ic_expand) else painterResource(
                        id = com.example.testmobileca.R.drawable.ic_collapse
                    ),
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }
    }

}

data class CollapsableSection(val title: String, val rows: List<Account>)