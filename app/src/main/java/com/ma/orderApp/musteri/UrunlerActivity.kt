package com.ma.orderApp.musteri

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ma.orderApp.adapter.CardUrunAdapter
import com.ma.orderApp.databinding.ActivityUrunlerBinding
import com.ma.orderApp.model.Siparis
import com.ma.orderApp.model.Urun

class UrunlerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUrunlerBinding

    private var adapter = CardUrunAdapter()

    private var masaNumarasi = 0

    private lateinit var database: FirebaseDatabase
    private lateinit var key: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUrunlerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialDatabase()
        urunleriGetir()

    }

    private fun urunleriGetir() {
        val gelenMasaNumarasi = intent.getIntExtra("masaNumarasi", 0)
        masaNumarasi = gelenMasaNumarasi

        val gelenKategoriID = intent.getIntExtra("kategoriId", 0)

        val urunListesiSorgusu =
            database.reference.child("Urunler").orderByChild("kategoriId")
                .equalTo(gelenKategoriID.toDouble())


        urunListesiSorgusu.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val urunListesi = ArrayList<Urun>()
                for (i in snapshot.children) {

                    val id = i.child("id").value as String
                    val kategoriId = i.child("kategoriId").value.toString().toInt()
                    val urunAdi = i.child("urunAdi").value.toString()
                    val urunFiyati = i.child("urunFiyati").value.toString().toDouble()
                    val urunResimURL = i.child("urunResimURL").value as String
                    val urun = Urun(id, urunResimURL, urunAdi, urunFiyati, kategoriId)
                    urunListesi.add(urun)
                }
                binding.apply {

                    rvUrunler.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    rvUrunler.adapter = adapter
                    adapter.setList(urunListesi)

                    adapter.siparisVereTiklandi { urun ->
                        siparisVerDialog(urun)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun initialDatabase() {
        database = FirebaseDatabase.getInstance()
    }


    private fun siparisVerDialog(urun: Urun) {
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage("Siparişinizi onaylıyor musunuz?")
            .setCancelable(false)
            .setPositiveButton("Evet", DialogInterface.OnClickListener { _, _ ->
                siparisVer(urun, masaNumarasi)

            })
            .setNegativeButton(
                "Hayır",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                },
            )

        val alert = dialogBuilder.create()
        alert.setTitle("Sipariş Onayı")
        alert.show()
    }

    private fun siparisVer(urun: Urun, masaNumarasi: Int) {

        key = database.getReference("Siparisler").push().key.toString()
        val siparis = Siparis(
            key,
            urun.urunResimURL,
            urun.urunAdi,
            masaNumarasi,
            false
        )

        database.reference.child("Siparisler").child(key).setValue(siparis)

        Toast.makeText(applicationContext, "Siparişiniz alınmıştır", Toast.LENGTH_SHORT)
            .show()

    }
}