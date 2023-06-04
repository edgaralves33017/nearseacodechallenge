package com.codechallenge.nearshoretest.ui.loading

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codechallenge.nearshoretest.R
import com.codechallenge.nearshoretest.databinding.FragmentLoadingBinding

class LoadingFragment : Fragment() {

    private var _binding: FragmentLoadingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        transitionToHomeFragment()
        return root
    }

    private fun transitionToHomeFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_LoadingScreen_to_Home)
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}