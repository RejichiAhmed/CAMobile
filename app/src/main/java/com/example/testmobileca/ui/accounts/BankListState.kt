package com.example.testmobileca.ui.accounts

import com.example.testmobileca.data.model.Bank


data class BankListState(
    val isLoading: Boolean = false,
    val banksCA: List<Bank> = emptyList(),
    val otherBanks: List<Bank> = emptyList(),
    val error: String = ""
)