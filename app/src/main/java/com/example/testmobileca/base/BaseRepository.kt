package com.example.testmobileca.base

import com.example.testmobileca.data.db.Database
import com.example.testmobileca.data.retrofit.APIClient
import com.example.testmobileca.utils.DataStoreManager

abstract class BaseRepository(
    protected val apiClient: APIClient,
    protected val dataStoreManager: DataStoreManager,
    protected val database: Database
)