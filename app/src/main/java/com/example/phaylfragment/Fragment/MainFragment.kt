package com.example.phaylfragment.Fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phaylfragment.R
import com.example.phaylfragment.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val animationDuration = 2000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startFadeInAnimation(binding.logo)
    }

    private fun startFadeInAnimation(view: View) {
        view.visibility = View.VISIBLE

        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = animationDuration
            fillAfter = true
        }

        view.startAnimation(fadeIn)

        Handler().postDelayed({
            findNavController().navigate(R.id.action_mainFragment_to_onboardingFragment)
        }, animationDuration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
