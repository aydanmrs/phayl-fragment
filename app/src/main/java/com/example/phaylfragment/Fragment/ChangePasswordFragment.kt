package com.example.phaylfragment.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phaylfragment.R
import com.example.phaylfragment.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : Fragment() {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_changePasswordFragment_to_otpFragment)
        }

        binding.arrow.setOnClickListener {
            findNavController().popBackStack()
        }

        fun updateBackground(editText: EditText) {
            val backgroundRes = if (editText.hasFocus() || editText.text.isNotEmpty()) {
                R.drawable.edittext_underline
            } else {
                R.drawable.edittext_simple
            }
            editText.setBackgroundResource(backgroundRes)
        }

        binding.emailEditTxt.apply {
            setOnFocusChangeListener { _, _ -> updateBackground(this) }
            addTextChangedListener { updateBackground(this) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
