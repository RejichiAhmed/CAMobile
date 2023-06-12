package com.example.testmobileca.utils

import com.example.testmobileca.utils.SchedulerProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppSchedulerProvider : SchedulerProvider {

    override fun dispatchersUI(): CoroutineDispatcher = Dispatchers.Main

    override fun dispatchersDefault(): CoroutineDispatcher = Dispatchers.Default

    override fun dispatchersIO(): CoroutineDispatcher = Dispatchers.IO

    override fun dispatchersUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}