package com.example.testmobileca.ui.details_account

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.testmobileca.base.BaseActivity
import com.example.testmobileca.utils.ExtraKeys
import com.example.testmobileca.utils.Navigation
import kotlinx.coroutines.InternalCoroutinesApi

class DetailsAccountActivity : BaseActivity() {

    private val viewModel: DetailsAccountViewModel by viewModels()

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailsAccountScreen()
        }
        registerBaseObservers(viewModel)
    }

    override fun navigate(navigationTo: Navigation) {
        super.navigate(navigationTo)
        when(navigationTo){
            is Navigation.Back -> {
                finish()
            }
            else -> {}
        }
    }
}