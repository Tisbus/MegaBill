package com.example.megabill.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.megabill.R
import com.example.megabill.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private var _bind : FragmentSplashBinding? = null
    private val bind : FragmentSplashBinding
    get() = _bind ?: throw RuntimeException("FragmentSplashBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentSplashBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
        postDelayed()
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun postDelayed() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                findNavController().navigate(R.id.action_splashFragment_to_startFragment)
            }, 5000
        )
    }
}