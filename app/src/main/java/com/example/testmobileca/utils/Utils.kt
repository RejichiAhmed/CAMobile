package com.example.testmobileca.utils

import androidx.compose.runtime.State
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.Bank
import com.example.testmobileca.ui.components.CollapsableSection
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun calculatedTotalBalance(listAccounts: List<Account>): Float {
    var count = 0F
    listAccounts.forEach {
        count += it.balance.toFloat()
    }
    return count

}

fun collapsableSections(banks: State<List<Bank>>): ArrayList<CollapsableSection> {
    val collapsableSectionList: ArrayList<CollapsableSection> = ArrayList()
    for (bank in banks.value) {
        CollapsableSection(
            bank.name,
            bank.accounts
        ).let {
            collapsableSectionList.add(
                it
            )
        }
    }
    return collapsableSectionList
}


fun timestampToDate(timestamp: Long): String {
    val dateFormat = "dd/MM/yyyy" // Example date format
    val date =  Date(timestamp)
    val format = SimpleDateFormat(dateFormat, Locale.getDefault())
    return format.format(date)
}