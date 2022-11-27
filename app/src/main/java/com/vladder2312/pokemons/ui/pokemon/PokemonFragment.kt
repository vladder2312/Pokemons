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
import com.vladder2312.pokemons.databinding.FragmentPokemonBinding
import com.vladder2312.pokemons.domain.PokemonDetails
import com.vladder2312.pokemons.domain.Resource
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

        viewLifecycleOwner.lifecycleScope.launch {
            arguments?.getString("pokemonId")?.let {
                viewModel.getDetails(it)
            }
            viewModel.pokemonDetails.observe(viewLifecycleOwner) {
                render(it)
            }
        }
    }

    fun render(data: Resource<PokemonDetails>) {
        when (data.status) {
            Status.LOADING -> {
                Toast.makeText(context, data.status.name, Toast.LENGTH_SHORT).show()
            }
            Status.ERROR -> {
                Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
            }
            Status.SUCCESS -> {
                binding.pokemonNameTv.text = data.data?.name
                binding.pokemonHeightTv.text = data.data?.height.toString()
                binding.pokemonWeightTv.text = data.data?.weight.toString()
                Glide.with(requireContext())
                    .load(data.data?.sprites?.frontDefault ?: data.data?.sprites?.frontFemale)
                    .into(binding.pokemonFrontIv)
                Glide.with(requireContext())
                    .load(data.data?.sprites?.backDefault ?: data.data?.sprites?.backFemale)
                    .into(binding.pokemonBackIv)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}