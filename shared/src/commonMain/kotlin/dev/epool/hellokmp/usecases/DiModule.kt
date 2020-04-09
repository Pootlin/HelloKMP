package dev.epool.hellokmp.usecases

import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

internal val useCaseModule = Kodein.Module("useCaseModule") {
    bind<GetUserUseCase>() with singleton { GetUserUseCase(instance()) }
    bind<GetFirstPokemonsUseCase>() with singleton { GetFirstPokemonsUseCase(instance()) }
}