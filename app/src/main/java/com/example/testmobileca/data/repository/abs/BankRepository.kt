package com.example.testmobileca.data.repository.abs

import androidx.annotation.WorkerThread
import com.example.testmobileca.data.model.Bank

interface BankRepository {

    @WorkerThread
    suspend fun getBanksAndCache(): List<Bank>

}