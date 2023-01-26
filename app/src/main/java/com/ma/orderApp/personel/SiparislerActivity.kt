package com.ma.orderApp.personel

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ma.orderApp.adapter.CardSiparisAdapter
import com.ma.orderApp.databinding.ActivitySiparislerBinding
import com.ma.orderApp.model.Siparis

class SiparislerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiparislerBinding
    private var adapter = CardSiparisAdapter()

    private lateinit var database: FirebaseDatabase
    private lateinit var key: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySiparislerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialDatabase()
//        addData()
        handleClickEvents()
        siparisleriGetir()
    }

    private fun siparisleriGetir() {
        val siparisListesiSorgusu =
            database.reference.child("Siparisler").orderByChild("completed").equalTo(false)

        siparisListesiSorgusu.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val siparisListesi = ArrayList<Siparis>()
                for (i in snapshot.children) {
                    val completed = i.child("completed").value as Boolean
                    val id = i.child("id").value as String
                    val masaNumarasi = i.child("masaNumarasi").value.toString().toInt()
                    val urunAdi = i.child("urunAdi").value as String
                    val urunResimURL = i.child("urunResimURL").value as String
                    val siparis = Siparis(id, urunResimURL, urunAdi, masaNumarasi, completed)
                    siparisListesi.add(siparis)
                }
                binding.apply {

                    rvSiparisler.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    rvSiparisler.adapter = adapter
                    adapter.setList(siparisListesi)

                    adapter.teslimEteTiklandi { siparisId ->
                        siparisTeslimEtDialog(siparisId)
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

    private fun addData() {
        key = database.getReference("Siparisler").push().key.toString()
        val siparis = Siparis(
            key,
            "https://i.lezzet.com.tr/images-xxlarge-recipe/turk-kahvesi-37f3e57b-4f17-427f-a1f5-d1775c861dd6.jpg",
            "Türk Kahvesi",
            1,
            false
        )
        database.reference.child("Siparisler").child(key).setValue(siparis)
    }

    private fun handleClickEvents() {
        binding.apply {
            tvGecmisSiparisler.setOnClickListener {
                val intent = Intent(applicationContext, EskiSiparislerActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun siparisTeslimEtDialog(siparisId: String) {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Siparişin teslim edildiğini onaylıyor musunuz?")
            .setCancelable(false)
            .setPositiveButton("Evet", DialogInterface.OnClickListener { _, _ ->
                siparisiTeslimEt(siparisId)

            })
            .setNegativeButton(
                "Hayır",
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                },
            )

        val alert = dialogBuilder.create()
        alert.setTitle("Sipariş Teslim Onayı")
        alert.show()
    }

    private fun siparisiTeslimEt(siparisId: String) {
        database.reference.child("Siparisler").child(siparisId).child("completed").setValue(true)

        Toast.makeText(applicationContext, "Siparişi teslim ettiniz", Toast.LENGTH_SHORT)
            .show()
    }
}