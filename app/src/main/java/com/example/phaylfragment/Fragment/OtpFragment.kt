package com.example.phaylfragment.Fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phaylfragment.R
import com.example.phaylfragment.databinding.FragmentOtpBinding

class OtpFragment : Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fullText = "Lomarova@std.beu.edu.az e-poçt ünvanınıza göndərilən təsdiq kodunu daxil edərək hesabınızı təsdiqləyin. Lomarova@std.beu.edu.az"
        val spannableString = SpannableString(fullText)
        val boldText = "Lomarova@std.beu.edu.az"
        val start = fullText.indexOf(boldText)
        val end = start + boldText.length
        spannableString.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtView.text = spannableString

        binding.pin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updatePinViewColors()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.arrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_otpFragment_to_newPasswordFragment)
        }

        updatePinViewColors()
        startCountdown()
    }

    private fun startCountdown() {
        val timerTextView = binding.second

        val countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                timerTextView.text = String.format("00:%02d", secondsRemaining)
            }

            override fun onFinish() {
                timerTextView.text = "00:00"
            }
        }

        countDownTimer.start()
    }

    private fun updatePinViewColors() {
        val pinView = binding.pin
        val filledColor = Color.parseColor("#7962FA")
        val emptyColor = Color.parseColor("#DEDFE1")

        val textLength = pinView.text.toString().length
        for (i in 0 until pinView.itemCount) {
            val lineColor = if (i < textLength) filledColor else emptyColor
            pinView.setLineColor(lineColor)
        }
        pinView.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
