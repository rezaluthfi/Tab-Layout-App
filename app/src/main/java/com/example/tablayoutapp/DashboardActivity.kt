package com.example.tablayoutapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tablayoutapp.databinding.ActivityDashboardBinding
import com.example.tablayoutapp.databinding.ViewDialogLogOutBinding

class DashboardActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Mengatur action bar
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                // Mengambil data user dari SharedPreferences
                val sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
                val fullname = sharedPref.getString("fullname", "N/A")
                val nim = sharedPref.getString("nim", "N/A")

                // Pindah ke ProfileActivity dengan membawa data user
                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtra("name", fullname)
                    putExtra("nim", nim)
                }
                startActivity(intent)
                true
            }
            R.id.action_logout -> {
                // Menampilkan dialog konfirmasi logout
                val dialogBinding = ViewDialogLogOutBinding.inflate(layoutInflater)
                val dialog = Dialog(this@DashboardActivity)

                // Set content view dari dialog dengan menggunakan binding
                dialog.setContentView(dialogBinding.root)

                // Menangani klik pada btnOk (logout dan kembali ke MainActivity)
                dialogBinding.btnOk.setOnClickListener {
                    // Pindah ke MainActivity dan hapus tumpukan
                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    finish()

                    // Menampilkan pesan Toast
                    Toast.makeText(this@DashboardActivity, "Berhasil logout", Toast.LENGTH_SHORT).show()

                    // Menutup dialog setelah logout berhasil
                    dialog.dismiss()
                }

                // Menangani klik pada btnCancel (tutup dialog)
                dialogBinding.btnCancel.setOnClickListener {
                    dialog.dismiss()  // Menutup dialog tanpa logout
                }

                dialog.show()

                // Mengatur ukuran dialog agar sesuai dengan layar atau ukuran tertentu
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,  // Lebar dialog: sesuai layar
                    ViewGroup.LayoutParams.WRAP_CONTENT   // Tinggi dialog: mengikuti konten
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
