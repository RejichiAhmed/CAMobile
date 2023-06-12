package com.example.testmobileca.data.useCase

import com.example.testmobileca.data.model.Bank
import com.example.testmobileca.data.repository.abs.BankRepository
import com.example.testmobileca.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBanksUseCase @Inject constructor(
    private val repository: BankRepository
) {

    operator fun invoke(): Flow<Resource<List<Bank>>> = flow {
        try {
            emit(Resource.Loading())
            val bankList = repository.getBanksAndCache()
            emit(Resource.Success(bankList))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred."
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}