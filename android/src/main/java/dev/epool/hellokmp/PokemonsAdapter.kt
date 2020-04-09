package dev.epool.hellokmp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.epool.hellokmp.databinding.ItemPokemonBinding
import dev.epool.hellokmp.domain.models.PokemonIdentity

class PokemonsAdapter : ListAdapter<PokemonIdentity, PokemonsAdapter.ViewHolder>(PokemonDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.pokemon = getItem(position)
    }

    class ViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root)

    class PokemonDiff : DiffUtil.ItemCallback<PokemonIdentity>() {
        override fun areItemsTheSame(oldItem: PokemonIdentity, newItem: PokemonIdentity) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: PokemonIdentity, newItem: PokemonIdentity) = oldItem == newItem
    }

}