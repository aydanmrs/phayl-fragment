package com.example.phaylfragment.Fragment

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.viewpager2.widget.ViewPager2
import androidx.navigation.fragment.findNavController
import com.example.phaylfragment.model.OnboardingItem
import com.example.phaylfragment.Adapter.OnboardingItemsAdapter
import com.example.phaylfragment.R
import com.example.phaylfragment.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var onboardingItems: List<OnboardingItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finishAffinity() // Uygulamayı kapatır
        }


        onboardingItems = listOf(
            OnboardingItem(
                title = "Bütün dərslərinizi bir mərkəzdən izləyin",
                description = "Dərslərinizi asanlıqla idarə edin, elanlar və resurslarla aktual qalın.",
                button = "Növbəti",
                imageResId = R.drawable.mobile // Resim kaynak ID'si
            ),
            OnboardingItem(
                title = "Davamiyyət izləmənizi sadələşdirin",
                description = "İnteqrasiya edilmiş təqvimimizlə davamiyyətinizi asanlıqla izləyin və cədvəlinizdən xəbərdar olun.",
                button = "Növbəti",
                imageResId = R.drawable.mobile // Resim kaynak ID'si
            ),
            OnboardingItem(
                title = "Yeniliklərdən vaxtında xəbərdar olun",
                description = "Tapşırıqlar, testlər, elanlar və digər vacib yeniləmələr üçün dərhal bildirişlər alın.",
                button = "Başla",
                imageResId = R.drawable.mobile // Resim kaynak ID'si
            )
        )


        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }

        val adapter = OnboardingItemsAdapter(onboardingItems)
        binding.onBoardingViewPager.adapter = adapter

        setupIndicators()
        binding.onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
                binding.button.text = if (position == onboardingItems.size - 1) "Başla" else "Növbəti"
            }
        })

        binding.button.setOnClickListener {
            val nextItem = binding.onBoardingViewPager.currentItem + 1
            if (nextItem < onboardingItems.size) {
                binding.onBoardingViewPager.setCurrentItem(nextItem, true)
            } else {
                findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            }
        }
    }

    private fun setupIndicators() {
        val indicators = Array(onboardingItems.size) { ImageView(requireContext()) }
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 0, 8, 0)
        }
        indicators.forEach { indicator ->
            indicator.layoutParams = layoutParams
            binding.indicatorsContainer.addView(indicator)
        }
        updateIndicators(0)
    }

    private fun updateIndicators(position: Int) {
        for (i in 0 until binding.indicatorsContainer.childCount) {
            val indicator = binding.indicatorsContainer.getChildAt(i) as ImageView
            val drawableRes = if (i == position) {
                R.drawable.indicator_active_background
            } else {
                R.drawable.indicator_inactive_background
            }
            indicator.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableRes))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}