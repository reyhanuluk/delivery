package com.ma.orderApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ma.orderApp.databinding.ActivityMainBinding
import com.ma.orderApp.musteri.KategorilerActivity
import com.ma.orderApp.musteri.MasaSecActivity
import com.ma.orderApp.personel.PersonelGirisActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        handleClickEvents()
    }

    private fun handleClickEvents() {
        binding.apply {
            cardSiparisVer.setOnClickListener {
                // sipariş ver butonuna tıklayınca
                masaSecSayfasinaGit()
            }
            cardPersonel.setOnClickListener {
                // personel butonuna tıklayınca PERSONEL GİRİŞ sayfasına yönlendir
                personelGirisSayfasineGit()
            }
        }
    }

    private fun masaSecSayfasinaGit() {
        val intent = Intent(this, MasaSecActivity::class.java)
        startActivity(intent)
    }


    private fun personelGirisSayfasineGit() {
        val intent = Intent(this, PersonelGirisActivity::class.java)
        startActivity(intent)
    }
}