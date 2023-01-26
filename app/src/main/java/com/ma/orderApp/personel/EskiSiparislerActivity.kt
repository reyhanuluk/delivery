package com.ma.orderApp.personel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ma.orderApp.adapter.CardEskiSiparisAdapter
import com.ma.orderApp.databinding.ActivityEskiSiparislerBinding
import com.ma.orderApp.model.Siparis

class EskiSiparislerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEskiSiparislerBinding
    private var adapter = CardEskiSiparisAdapter()

    private lateinit var database: FirebaseDatabase
    private lateinit var key: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEskiSiparislerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialDatabase()
        siparisleriGetir()

//        eskiSiparisleriYukleFAKE()
    }

    private fun initialDatabase() {
        database = FirebaseDatabase.getInstance()
    }

    private fun siparisleriGetir() {
        val siparisListesiSorgusu =
            database.reference.child("Siparisler").orderByChild("completed").equalTo(true)

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

                    rvEskiSiparisler.layoutManager =
                        LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    rvEskiSiparisler.adapter = adapter
                    adapter.setList(siparisListesi)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


//    private fun eskiSiparisleriYukleFAKE() {
//        binding.apply {
//            val tavukUrl: String = "https://images.deliveryhero.io/image/fd-tr/LH/w98w-hero.jpg"
//            val balikUrl: String =
//                "https://i.nefisyemektarifleri.com/2016/05/07/yagda-kizarmis-balik.jpeg"
//            val sogukCayUrl: String =
//                "https://cdn.yemek.com/mnresize/940/940/uploads/2014/08/kirazli-buzlu-cay-yeni.jpg"
//            val turkKahvesiUrl: String =
//                "https://i.lezzet.com.tr/images-xxlarge-recipe/turk-kahvesi-37f3e57b-4f17-427f-a1f5-d1775c861dd6.jpg"
//            val eskiSiparisListesi = ArrayList<Siparis>()
//            eskiSiparisListesi.add(Siparis(1, tavukUrl, "Kızarmış Tavuk", 1))
//            eskiSiparisListesi.add(Siparis(2, balikUrl, "Balık Kızartma", 3))
//            eskiSiparisListesi.add(Siparis(3, sogukCayUrl, "Soğuk Çay", 2))
//            eskiSiparisListesi.add(Siparis(4, turkKahvesiUrl, "Türk Kahvesi", 5))
//            eskiSiparisListesi.add(Siparis(1, tavukUrl, "Kızarmış Tavuk", 1))
//            eskiSiparisListesi.add(Siparis(2, balikUrl, "Balık Kızartma", 3))
//            eskiSiparisListesi.add(Siparis(3, sogukCayUrl, "Soğuk Çay", 2))
//            eskiSiparisListesi.add(Siparis(4, turkKahvesiUrl, "Türk Kahvesi", 5))
//            eskiSiparisListesi.add(Siparis(1, tavukUrl, "Kızarmış Tavuk", 1))
//            eskiSiparisListesi.add(Siparis(2, balikUrl, "Balık Kızartma", 3))
//            eskiSiparisListesi.add(Siparis(3, sogukCayUrl, "Soğuk Çay", 2))
//            eskiSiparisListesi.add(Siparis(4, turkKahvesiUrl, "Türk Kahvesi", 5))
//
//            rvEskiSiparisler.layoutManager =
//                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//            rvEskiSiparisler.adapter = adapter
//            adapter.setList(eskiSiparisListesi)
//
//
//        }
//    }
}