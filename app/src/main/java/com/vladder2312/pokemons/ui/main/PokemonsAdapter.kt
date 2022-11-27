package com.vladder2312.pokemons.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladder2312.pokemons.databinding.ItemPokemonBinding
import com.vladder2312.pokemons.domain.Pokemon

class PokemonsAdapter(
    private val onClickListener: (id: String) -> Unit
) : PagingDataAdapter<Pokemon, PokemonsAdapter.PokemonsViewHolder>(DiffUtilCallBack) {

    override fun onBindViewHolder(holder: PokemonsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context))
        return PokemonsViewHolder(binding)
    }

    inner class PokemonsViewHolder(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { onClickListener(it.id) }
            }
        }

        fun bind(item: Pokemon) {
            binding.itemPokemonIdTv.text = item.id
            binding.itemPokemonNameTv.text = item.name
        }
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Pokemon>() {

        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }
}

