package com.example.testmobileca.ui.accounts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.testmobileca.base.BaseActivity
import com.example.testmobileca.ui.details_account.DetailsAccountActivity
import com.example.testmobileca.utils.ExtraKeys
import com.example.testmobileca.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi


@AndroidEntryPoint
class MyAccountsActivity : BaseActivity() {

    private val viewModel: MyAccountsViewModel by viewModels()

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAccountsScreen()
        }

        registerBaseObservers(viewModel)
    }

    override fun navigate(navigationTo: Navigation) {
        super.navigate(navigationTo)
        when(navigationTo){
            is Navigation.DetailsAccountNavigation -> {
                startDetailsAccountNavigation(navigationTo)
            }
            else -> {}
        }
    }

    private fun startDetailsAccountNavigation(navigationTo: Navigation.DetailsAccountNavigation) {

        Intent(this, DetailsAccountActivity::class.java).run {
            putExtra(ExtraKeys.DetailsAccountActivity.MAIN_EXTRA_ACCOUNT_KEY, navigationTo.account)
            startActivity(this)
        }
    }
}