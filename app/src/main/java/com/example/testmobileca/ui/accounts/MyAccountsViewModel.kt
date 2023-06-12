package com.example.testmobileca.ui.accounts

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.testmobileca.base.BaseAndroidViewModel
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.Bank
import com.example.testmobileca.data.repository.abs.BankRepository
import com.example.testmobileca.data.useCase.GetBanksUseCase
import com.example.testmobileca.utils.ClickListener
import com.example.testmobileca.utils.Navigation
import com.example.testmobileca.utils.Resource
import com.example.testmobileca.utils.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MyAccountsViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    private val bankRepository: BankRepository,
    private val getBanksUseCase: GetBanksUseCase,
) : BaseAndroidViewModel(application, schedulerProvider), ClickListener<Account> {


    private val _stateBanks = mutableStateOf(BankListState())
    val stateBanks: State<BankListState>
        get() = _stateBanks

    private val _banksCA: MutableStateFlow<List<Bank>> =
        MutableStateFlow(emptyList())
    val banksCA: StateFlow<List<Bank>>
        get() = _banksCA

    private val _otherBanks: MutableStateFlow<List<Bank>> =
        MutableStateFlow(emptyList())
    val otherBanks: StateFlow<List<Bank>>
        get() = _otherBanks

    init {
        getBackList()
    }

    override fun onItemClicked(item: Account) {
        super.onItemClicked(item)
        navigate(Navigation.DetailsAccountNavigation(item))

    }

    private fun getBackList() {

        getBanksUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateBanks.value = BankListState(
                        isLoading = true
                    )
                    isLoading(true)
                }
                is Resource.Success -> {
                    _banksCA.value = result.data?.filter { it.isCA == "1" } ?: emptyList()

                    _otherBanks.value = result.data?.filter { it.isCA == "0" } ?: emptyList()

                    isLoading(false)
                }
                is Resource.Error -> {
                    _stateBanks.value = BankListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                    isLoading(false)
                }
            }
        }.launchIn(viewModelScope)
    }


}