package com.ma.orderApp.personel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ma.orderApp.databinding.ActivityPersonelGirisBinding

class PersonelGirisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonelGirisBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var key: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonelGirisBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialDatabase()
        handleClickEvents()
    }


    private fun initialDatabase() {
        database = FirebaseDatabase.getInstance()
    }

    private fun handleClickEvents() {
        binding.apply {
            cardGirisYap.setOnClickListener {
                val kullaniciAdi = tieKullaniciAdi.text.toString().trim()
                val parola = tieParola.text.toString().trim()

                girisYap(kullaniciAdi, parola)
            }
        }
    }

    private fun girisYap(kullaniciAdi: String, parola: String) {
        binding.apply {
            val searchText = kullaniciAdi
            val sorguSonucuListe = database.reference.child("Personeller").orderByChild("username")
                .startAt(searchText)
                .endAt(searchText + "\uf8ff")

            sorguSonucuListe.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var isLoginSuccess = false
                    for (i in snapshot.children) {
                        if (parola == i.child("password").value) {
                            isLoginSuccess = true
                            val intent = Intent(applicationContext, SiparislerActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    if (!isLoginSuccess) {
                        Toast.makeText(
                            applicationContext,
                            "Kullanıcı adı veya parola yanlış",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

    }
}
