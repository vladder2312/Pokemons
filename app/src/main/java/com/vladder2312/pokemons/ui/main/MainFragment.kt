package com.vladder2312.pokemons.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vladder2312.pokemons.R
import com.vladder2312.pokemons.databinding.FragmentMainBinding
import com.vladder2312.pokemons.ui.pokemon.PokemonFragment
import com.vladder2312.pokemons.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val pokemonsAdapter = PokemonsAdapter { viewModel.openPokemonScreen(it) }

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

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.mainRecyclerView.adapter = pokemonsAdapter
    }

    private fun observeViewModel() {
        viewModel.pokemons.observe(viewLifecycleOwner) {
            pokemonsAdapter.submitData(lifecycle, it)
        }
        viewModel.openScreenEvent.observe(viewLifecycleOwner) {
            openPokemonScreen(it)
        }
    }

    private fun openPokemonScreen(id: String) {
        findNavController().navigate(
            resId = R.id.action_mainFragment_to_pokemonFragment,
            args = bundleOf(PokemonFragment.POKEMON_ID_PARAM to id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}