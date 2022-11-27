package com.vladder2312.pokemons.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vladder2312.pokemons.databinding.FragmentPokemonBinding
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        arguments?.getString(POKEMON_ID_PARAM)?.let {
            viewModel.getDetails(it)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonDetails.observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> it.data?.let { data -> render(data) }
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
    }

    private fun render(data: PokemonDetails) {
        with(data) {
            binding.pokemonNameTv.text = name
            binding.pokemonHeightTv.text = height.toString()
            binding.pokemonWeightTv.text = weight.toString()
            Glide.with(requireContext())
                .load(sprites.frontDefault ?: sprites.frontFemale)
                .into(binding.pokemonFrontIv)
            Glide.with(requireContext())
                .load(sprites.backDefault ?: sprites.backFemale)
                .into(binding.pokemonBackIv)
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