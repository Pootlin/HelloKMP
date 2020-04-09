package dev.epool.hellokmp.di

import dev.epool.hellokmp.network.networkModule
import dev.epool.hellokmp.repository.repositoryModule
import dev.epool.hellokmp.usecases.useCaseModule
import org.kodein.di.Kodein

internal val myKodein = Kodein.lazy {
    import(networkModule)
    import(repositoryModule)
    import(useCaseModule)
}