package com.example.lab3.ui.gamemenuscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lab3.shared.model.GameViewModel
import com.example.lab3.R
import com.example.lab3.databinding.FragmentGameMenuBinding

class GameMenuFragment : Fragment() {
    private lateinit var binding: FragmentGameMenuBinding
    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "colormatcher"
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_gameMenuFragment_to_registrationFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartGame.setOnClickListener {
            val radioButton: RadioButton = binding.root.findViewById(binding.rgGameDifficulties.checkedRadioButtonId)
            viewModel.setDifficulty(radioButton.text.toString())
            findNavController().navigate(R.id.action_gameMenuFragment_to_gameFragment)
        }
    }
}