package com.example.testmobileca.ui

sealed class Screen(val route: String) {
    object ProductListScreen : Screen("product_list_screen")
    object ProductDetailsScreen : Screen("product_details_screen")
    object CartDetailsScreen : Screen("cart_details_screen")
}