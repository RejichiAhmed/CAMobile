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

    private val gson = Gson()

    private val jsonString = """[
    {
        "accounts": [
            {
                "balance": 2031.84,
                "contract_number": "32216549871",
                "holder": "Corinne Martin",
                "id": "151515151151",
                "label": "Compte de dépôt",
                "operations": [
                    {
                        "amount": "-15,99",
                        "category": "leisure",
                        "date": "1644870724",
                        "id": "2",
                        "title": "Prélèvement Netflix"
                    },
                    {
                        "amount": "-95,99",
                        "category": "online",
                        "date": "1644611558",
                        "id": "4",
                        "title": "CB Amazon"
                    }
                ],
                "order": 1,
                "product_code": "00004",
                "role": 1
            },
            {
                "balance": 843.15,
                "contract_number": "09320939231",
                "holder": "M. et Mme Martin",
                "id": "9892736780987654",
                "label": "Compte joint",
                "operations": [
                    {
                        "amount": "-15,99",
                        "category": "leisure",
                        "date": "1644784369",
                        "id": "2",
                        "title": "Prélèvement Netflix"
                    },
                    {
                        "amount": "-750,00",
                        "category": "housing",
                        "date": "1644179569",
                        "id": "3",
                        "title": "Prélèvement Century 21"
                    }
                ],
                "order": 2,
                "product_code": "00007",
                "role": 2
            },
            {
                "balance": 209.39,
                "contract_number": "29389382872",
                "holder": "Thaïs Martin",
                "id": "2354657678098765",
                "label": "Compte Mozaïc",
                "operations": [
                    {
                        "amount": "-15,99",
                        "category": "leisure",
                        "date": "1644438769",
                        "id": "2",
                        "title": "Orange"
                    }
                ],
                "order": 3,
                "product_code": "00007",
                "role": 6
            }
        ],
        "isCA": 1,
        "name": "CA Languedoc"
    },
    {
        "accounts": [
            {
                "balance": 45.84,
                "contract_number": "32216549871",
                "holder": "Corinne Martin",
                "id": "09878900000",
                "label": "Compte de dépôt",
                "operations": [
                    {
                        "amount": "-1,99",
                        "category": "costs",
                        "date": "1588690878",
                        "id": "2",
                        "title": "Tenue de compte"
                    },
                    {
                        "amount": "-1,99",
                        "category": "costs",
                        "date": "1641760369",
                        "id": "3",
                        "title": "Tenue de compte"
                    }
                ],
                "order": 1,
                "product_code": "00004",
                "role": 1
            }
        ],
        "isCA": 0,
        "name": "Boursorama"
    },
    {
        "accounts": [
            {
                "balance": 675.04,
                "contract_number": "32216549871",
                "holder": "Jean Martin",
                "id": "3982997777",
                "label": "Compte Chèques",
                "operations": [
                    {
                        "amount": "-1331,44",
                        "category": "costs",
                        "date": "1644179569",
                        "id": "2",
                        "title": "Prêt immo"
                    },
                    {
                        "amount": "-53,20",
                        "category": "food",
                        "date": "1644784369",
                        "id": "2",
                        "title": "CB La Vie Claire"
                    },
                    {
                        "amount": "-10,00",
                        "category": "leisure",
                        "date": "1644611558",
                        "id": "3",
                        "title": "Prélèvement Spotify"
                    },
                    {
                        "amount": "-53,00",
                        "category": "trip",
                        "date": "1644870724",
                        "id": "4",
                        "title": "CB Billets SNCF"
                    }
                ],
                "order": 1,
                "product_code": "00004",
                "role": 1
            }
        ],
        "isCA": 0,
        "name": "Banque Pop"
    },
    {
        "accounts": [
            {
                "balance": 425.84,
                "contract_number": "32216549871",
                "holder": "Corinne Martin",
                "id": "3982938",
                "label": "Compte de dépôt",
                "operations": [
                    {
                        "amount": "-1,99",
                        "category": "costs",
                        "date": "1644870724",
                        "id": "2",
                        "title": "Tenue de compte"
                    },
                    {
                        "amount": "-45,99",
                        "category": "leisure",
                        "date": "1644870724",
                        "id": "2",
                        "title": "Prélèvement Orange"
                    }
                ],
                "order": 1,
                "product_code": "00004",
                "role": 1
            }
        ],
        "isCA": 1,
        "name": "CA Centre-Est"
    }
]"""

    private val banks: List<Bank> = gson.fromJson(jsonString, Array<Bank>::class.java).toList()

    operator fun invoke(): Flow<Resource<List<Bank>>> = flow {
        try {
            emit(Resource.Loading())
            //val bankList = repository.getBanksAndCache()
            val bankList = banks
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