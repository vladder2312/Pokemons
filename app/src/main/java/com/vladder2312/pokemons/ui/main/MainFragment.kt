package com.vladder2312.pokemons.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vladder2312.pokemons.R
import com.vladder2312.pokemons.databinding.FragmentMainBinding
import com.vladder2312.pokemons.ui.pokemon.PokemonFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val pokemonsAdapter = PokemonsAdapter {
        findNavController().navigate(
            resId = R.id.action_mainFragment_to_pokemonFragment,
            args = bundleOf(PokemonFragment.POKEMON_ID_PARAM to it)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.mainRecyclerView) {
            adapter = pokemonsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemons.observe(viewLifecycleOwner) {
                pokemonsAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}