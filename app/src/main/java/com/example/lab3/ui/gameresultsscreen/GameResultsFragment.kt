package com.example.lab3.ui.gameresultsscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lab3.R
import com.example.lab3.databinding.FragmentGameResultBinding
import com.example.lab3.shared.model.GameViewModel

class GameResultsFragment : Fragment() {
    private lateinit var binding: FragmentGameResultBinding
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = "Results"
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_gameResultsFragment_to_gameMenuFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameResultBinding.inflate(inflater, container, false)
        sharedPref = requireActivity().getSharedPreferences("colormatcher_settings", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvScore.text = getString(R.string.final_score, viewModel.email, viewModel.score.value)
        binding.btnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_gameResultsFragment_to_gameMenuFragment)
        }

        if (sharedPref.getBoolean("hideButton", false)) {
            binding.btnSendResults.visibility = View.INVISIBLE
        }

        binding.btnSendResults.setOnClickListener {
            val resultSummary = getString(
                R.string.results_summary,
                viewModel.score.value
            )

            val mailTo = "mailto:${viewModel.email}" +
                    "?subject=" + Uri.encode(getString(R.string.color_matcher)) +
                    "&body=" + Uri.encode(resultSummary)

            val intent = Intent(Intent.ACTION_SENDTO).setData(Uri.parse(mailTo))

            startActivity(intent)
        }
    }
}