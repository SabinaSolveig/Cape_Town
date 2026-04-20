package com.example.capetown.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.capetown.R
import com.example.capetown.databinding.FragmentSettingsBinding
import com.example.capetown.utils.AnimationUtils

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        AnimationUtils.animateContentSlideIn(binding.root)

        binding.notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            val prefs = requireContext().getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
            prefs.edit().putBoolean("notifications_enabled", isChecked).apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}