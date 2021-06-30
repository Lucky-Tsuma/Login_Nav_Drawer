package com.lucky.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lucky.login.databinding.FragmentNotificationsBinding

class FragmentNotifications: Fragment() {
    /*property only valid between onCreateView and onDestroyView*/
    private var _binding: FragmentNotificationsBinding? = null
    /*custom getter*/
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        binding.buttonNotifications.setOnClickListener {
            Toast.makeText(context, "Testing view binding on Notifications Fragment", Toast.LENGTH_SHORT)
                .show()
        }

        return binding.root

        /*view = binding.root
        * return binding.root
        *
        * above code has been compressed to what is being used*/

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}