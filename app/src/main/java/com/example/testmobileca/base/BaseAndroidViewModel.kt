package com.example.testmobileca.base

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.testmobileca.utils.Navigation
import com.example.testmobileca.utils.SchedulerProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseAndroidViewModel(
    application: Application,
    protected val schedulerProvider: SchedulerProvider,
) : AndroidViewModel(application) {

    private val job = SupervisorJob()
    protected val viewModelScope = CoroutineScope(job + schedulerProvider.dispatchersUI())


    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _navigation = MutableSharedFlow<Navigation>(replay = 0)
    val navigation: SharedFlow<Navigation>
        get() = _navigation

    //for blocking progress bar
    private val _progressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val progressBar: StateFlow<Boolean>
        get() = _progressBar

    protected fun isLoading(boolean: Boolean) {
        viewModelScope.launch {
            _isLoading.value = boolean
        }
    }



    /**
     * Used for navigation events.
     *
     * @param navigationTo The new destination.
     *
     */
    fun navigate(navigationTo: Navigation) {
        viewModelScope.launch {
            _navigation.emit(navigationTo)
        }
    }

}