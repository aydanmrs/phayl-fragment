package com.example.phaylfragment.Fragment

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.phaylfragment.R
import com.example.phaylfragment.databinding.FragmentNewPasswordBinding

class NewPasswordFragment : Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding
    private var isPasswordVisible: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun updateBackground(editText: EditText) {
            val backgroundRes = if (editText.hasFocus() || editText.text.isNotEmpty()) {
                R.drawable.edittext_underline
            } else {
                R.drawable.edittext_simple
            }
            editText.setBackgroundResource(backgroundRes)
        }

        binding.newPasswordEditTxt.apply {
            setOnFocusChangeListener { _, _ -> updateBackground(this) }
            addTextChangedListener { updateBackground(this) }
        }

        binding.passwordEditTxt.apply {
            setOnFocusChangeListener { _, _ -> updateBackground(this) }
            addTextChangedListener { updateBackground(this) }
        }

        binding.arrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.eyeIcon1.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            updatePasswordVisibility(binding.passwordEditTxt, binding.eyeIcon1)
        }

        binding.eyeIcon2.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            updatePasswordVisibility(binding.newPasswordEditTxt, binding.eyeIcon2)
        }
    }

    private fun updatePasswordVisibility(editText: EditText, eyeIcon: ImageView) {
        if (isPasswordVisible) {
            editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            eyeIcon.setImageResource(R.drawable.eye)
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            eyeIcon.setImageResource(R.drawable.closed_eye)
        }
        editText.typeface = editText.typeface
        editText.setSelection(editText.text.length)
    }
}
