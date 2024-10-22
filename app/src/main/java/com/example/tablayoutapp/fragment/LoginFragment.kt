package com.example.tablayoutapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tablayoutapp.DashboardActivity
import com.example.tablayoutapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aksi ketika tombol login diklik
        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.etEmail.text.toString().trim()
            val inputPassword = binding.etPassword.text.toString().trim()

            // Ambil data user dari SharedPreferences
            val sharedPref = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val savedEmail = sharedPref.getString("email", null)
            val savedPassword = sharedPref.getString("password", null)

            // Validasi input login
            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(context, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cek apakah email dan password cocok dengan yang disimpan
            if (inputEmail == savedEmail && inputPassword == savedPassword) {
                Toast.makeText(context, "Login berhasil", Toast.LENGTH_SHORT).show()

                // Pindah ke Dashboard
                val intent = Intent(context, DashboardActivity::class.java)
                startActivity(intent)
                requireActivity().finish()

            } else {
                Toast.makeText(context, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
