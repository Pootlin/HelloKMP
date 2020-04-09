package dev.epool.hellokmp.viewmodels

import dev.epool.hellokmp.di.myKodein
import dev.epool.hellokmp.domain.models.PokemonIdentity
import dev.epool.hellokmp.domain.GetFirstPokemonsUseCase
import org.kodein.di.erased.instance

class PokemonsViewModel : ViewStateViewModel<PokemonsViewModel.ViewState>(ViewState()) {

    private val getFirstPokemonsUseCase: GetFirstPokemonsUseCase by myKodein.instance()

    fun loadPokemons() {
        reduce { toLoadedPokemons(getFirstPokemonsUseCase()) }
    }

    data class ViewState(
        val pokemons: List<PokemonIdentity>,
        override val basicStateView: BasicStateView
    ) : BaseStateView<ViewState> {

        /**
         * This is to be able to do ViewState() in Swift.
         * If we use these default values in the primary constructor, Swift complains with "`init()` is unavailable".
         */
        constructor() : this(emptyList(), BasicStateView())

        fun toLoadedPokemons(pokemons: List<PokemonIdentity>) = copy(pokemons = pokemons)

        override fun copyBasic(basicStateView: BasicStateView) = copy(basicStateView = basicStateView)

    }

}