package com.vladder2312.pokemons.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vladder2312.pokemons.databinding.FragmentPokemonBinding
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Status
import com.vladder2312.pokemons.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private val viewModel by viewModels<PokemonViewModel>()

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleArguments()
        observeViewModel()
    }

    private fun handleArguments() {
        arguments?.getString(POKEMON_ID_PARAM)?.let {
            viewModel.getPokemonDetails(it)
        }
    }

    private fun observeViewModel() {
        viewModel.pokemonDetails.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { data -> showPokemonData(data) }
                Status.LOADING -> {
                    Toast.makeText(context, it.status.name, Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Snackbar.make(binding.root, it.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showPokemonData(data: PokemonDetails) {
        with(binding) {
            pokemonNameTv.text = data.name
            pokemonHeightTv.text = data.height.toString()
            pokemonWeightTv.text = data.weight.toString()
            Glide.with(requireContext())
                .load(data.sprites.frontDefault ?: data.sprites.frontFemale)
                .into(pokemonFrontIv)
            Glide.with(requireContext())
                .load(data.sprites.backDefault ?: data.sprites.backFemale)
                .into(pokemonBackIv)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val POKEMON_ID_PARAM = "pokemonId"
    }
}