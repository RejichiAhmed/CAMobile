package com.example.testmobileca.di



import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.testmobileca.data.repository.abs.BankRepository
import com.example.testmobileca.data.repository.imp.BankRepositoryImp

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideBankRepository(bankRepositoryImp: BankRepositoryImp): BankRepository



}
