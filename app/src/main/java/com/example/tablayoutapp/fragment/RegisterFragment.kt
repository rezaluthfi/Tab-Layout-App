package com.example.tablayoutapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.tablayoutapp.R
import com.example.tablayoutapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    // Inisialisasi binding dengan by lazy
    private val binding by lazy {
        FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Action ketika tombol register diklik
        binding.btnRegister.setOnClickListener {
            val fullname = binding.etFullname.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val nim = binding.etNim.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            // Validasi input
            if (fullname.isEmpty() || email.isEmpty() || nim.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(context, "Mohon isi semua data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(context, "Password tidak cocok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan data user menggunakan SharedPreferences
            val sharedPref = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("fullname", fullname)
            editor.putString("email", email)
            editor.putString("nim", nim)
            editor.putString("password", password)
            editor.apply()

            Toast.makeText(context, "Registrasi berhasil", Toast.LENGTH_SHORT).show()

            // Pindah ke fragment Login setelah berhasil register
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.view_pager)
            viewPager.currentItem = 0

        }
    }
}
