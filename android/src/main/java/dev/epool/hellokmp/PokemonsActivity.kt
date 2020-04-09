package dev.epool.hellokmp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.epool.hellokmp.databinding.ActivityPokemonsBinding
import dev.epool.hellokmp.extensions.observe
import dev.epool.hellokmp.viewmodels.PokemonsViewModel

class PokemonsActivity : AppCompatActivity() {

    private val binding: ActivityPokemonsBinding by binding(R.layout.activity_pokemons)

    private val viewModel: PokemonsViewModel by viewModels()

    private val adapter by lazy { PokemonsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerViewPokemons.setHasFixedSize(true)
        binding.recyclerViewPokemons.adapter = adapter
        binding.viewModel = viewModel
        viewModel.loadPokemons()
        viewModel.viewState.observe(this) { viewState ->
            binding.viewState = viewState
            if (viewState.hasError) {
                Toast.makeText(this, viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }
            adapter.submitList(viewState.pokemons)
        }
    }

}
