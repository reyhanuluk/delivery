package com.ma.orderApp.musteri

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.ma.orderApp.R
import com.ma.orderApp.databinding.ActivityMasaSecBinding

class MasaSecActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMasaSecBinding

    val cardListesi = ArrayList<MaterialCardView>()
    private var seciliMasaNumarasi: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masa_sec)

        binding = ActivityMasaSecBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        cardElemanlariniListeyeEkle()
        tiklamaDinleyicisi()
    }

    private fun cardElemanlariniListeyeEkle() {
        binding.apply {
            cardListesi.addAll(
                arrayListOf(
                    card1,
                    card2,
                    card3,
                    card4,
                    card5,
                    card6,
                    card7,
                    card8,
                    card9
                )
            )
        }
    }

    private fun tiklamaDinleyicisi() {
        binding.apply {
            card1.setOnClickListener {
                masaSec(0)
            }
            card2.setOnClickListener {
                masaSec(1)
            }
            card3.setOnClickListener {
                masaSec(2)
            }
            card4.setOnClickListener {
                masaSec(3)
            }
            card5.setOnClickListener {
                masaSec(4)
            }
            card6.setOnClickListener {
                masaSec(5)

            }
            card7.setOnClickListener {
                masaSec(6)
            }
            card8.setOnClickListener {
                masaSec(7)
            }
            card9.setOnClickListener {
                masaSec(8)
            }

            cardMasayiSec.setOnClickListener {
                if (seciliMasaNumarasi == -1) {
                    Toast.makeText(this@MasaSecActivity, "Masa seçmelisiniz", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val intent = Intent(applicationContext, KategorilerActivity::class.java)
                    intent.putExtra("masaNumarasi", seciliMasaNumarasi + 1)
                    startActivity(intent)
                }
            }
        }
    }

    private fun masaSec(masaNumarasi: Int) {
        seciliMasaNumarasi = masaNumarasi
        for (i in cardListesi) {
            i.setCardBackgroundColor(ContextCompat.getColor(applicationContext, R.color.white))
        }
        cardListesi[seciliMasaNumarasi].setCardBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.orange3
            )
        )

    }
}