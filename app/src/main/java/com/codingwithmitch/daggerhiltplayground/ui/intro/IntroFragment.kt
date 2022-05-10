package com.codingwithmitch.daggerhiltplayground.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.codingwithmitch.daggerhiltplayground.R
import com.codingwithmitch.daggerhiltplayground.databinding.FragmentIntroBinding
import com.codingwithmitch.daggerhiltplayground.firebase.BlogRemoteEntity
import com.codingwithmitch.daggerhiltplayground.firebase.CityRemoteEntity
import com.codingwithmitch.daggerhiltplayground.firebase.Food
import com.codingwithmitch.daggerhiltplayground.ui.UserEvent
import com.codingwithmitch.daggerhiltplayground.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : Fragment() {

    private var binding: FragmentIntroBinding? = null
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentIntroBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            introFragment = this@IntroFragment
            btnNavigate.setOnClickListener {
                navigateToNextScreen()
            }
        }
    }

    private fun navigateToNextScreen() {
        findNavController().navigate(R.id.action_introFragment_to_mainFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}