package com.ma.orderApp.musteri

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ma.orderApp.databinding.ActivityKategorilerBinding

class KategorilerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKategorilerBinding

    private var masaNumarasi: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategorilerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        masaNumarasiniAl()
        handleClickEvents()

    }

    private fun masaNumarasiniAl() {
        masaNumarasi = intent.getIntExtra("masaNumarasi", -1)
        Log.e("v", "gelen masa numarasi : $masaNumarasi")
    }

    private fun handleClickEvents() {
        binding.apply {
            cardBaharat.setOnClickListener {
                urunlerSayfasineGit(kategoriId = 1)
            }
            cardSogukIcecekler.setOnClickListener {
                urunlerSayfasineGit(kategoriId = 2)
            }
            cardBalik.setOnClickListener {
                urunlerSayfasineGit(kategoriId = 3)
            }
            cardMeyve.setOnClickListener {
                urunlerSayfasineGit(kategoriId = 4)
            }
            cardSicakIcecekler.setOnClickListener {
                urunlerSayfasineGit(kategoriId = 5)
            }
            cardTavuk.setOnClickListener {
                urunlerSayfasineGit(kategoriId = 6)
            }
        }
    }

    private fun urunlerSayfasineGit(kategoriId: Int) {
        val intent = Intent(this, UrunlerActivity::class.java)
        intent.putExtra("kategoriId", kategoriId)
        intent.putExtra("masaNumarasi", masaNumarasi)
        startActivity(intent)
    }
}