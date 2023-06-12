package com.example.testmobileca.utils

import com.example.testmobileca.data.model.Account


sealed class Navigation {

    object Back : Navigation()

    data class  DetailsAccountNavigation (val account: Account): Navigation()

}