package com.example.testmobileca.data.repository.imp

import com.example.testmobileca.data.db.Database
import com.example.testmobileca.base.BaseRepository
import com.example.testmobileca.data.model.Bank
import com.example.testmobileca.data.repository.abs.BankRepository
import com.example.testmobileca.data.retrofit.APIClient
import com.example.testmobileca.utils.DataStoreManager
import javax.inject.Inject

class BankRepositoryImp @Inject constructor(
    apiClient: APIClient,
    dataStoreManager: DataStoreManager,
    database: Database
) : BaseRepository(apiClient, dataStoreManager, database), BankRepository {

    override suspend fun getBanksAndCache(): List<Bank> {
        return apiClient.getBanks().banks

    }

}