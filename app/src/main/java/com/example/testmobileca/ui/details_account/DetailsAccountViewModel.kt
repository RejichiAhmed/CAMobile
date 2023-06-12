package com.example.testmobileca.ui.details_account

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.testmobileca.base.BaseAndroidViewModel
import com.example.testmobileca.data.model.Account
import com.example.testmobileca.data.model.Operation
import com.example.testmobileca.utils.ExtraKeys
import com.example.testmobileca.utils.Navigation
import com.example.testmobileca.utils.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsAccountViewModel @Inject constructor(
    application: Application,
    schedulerProvider: SchedulerProvider,
    savedStateHandle: SavedStateHandle,
) : BaseAndroidViewModel(application, schedulerProvider) {


    private val _account: MutableStateFlow<Account> =
        MutableStateFlow(savedStateHandle.get<Account>(ExtraKeys.DetailsAccountActivity.MAIN_EXTRA_ACCOUNT_KEY)!!)
    val account: StateFlow<Account>
        get() = _account


    private val _operations: MutableStateFlow<List<Operation>> =
        MutableStateFlow(emptyList())
    val operations: StateFlow<List<Operation>>
        get() = _operations

    init {
        initListOperations()
    }

    private fun initListOperations() {
        val listOperation = account.value.operations
        _operations.value = listOperation.toMutableList().sortedWith(
            compareBy<Operation>{ it.date }.thenBy { it.title }
        )
    }

    fun onBackClicked() {
        navigate(Navigation.Back)
    }


}