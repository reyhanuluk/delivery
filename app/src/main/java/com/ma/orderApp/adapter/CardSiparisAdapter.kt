package com.ma.orderApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ma.orderApp.databinding.CardSiparisBinding
import com.ma.orderApp.model.Siparis


class CardSiparisAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = ArrayList<Siparis>()
    val list get() = _list.toList()
    private lateinit var context: Context

    fun setList(newList: List<Siparis>) {
        _list.clear()
        _list.addAll(newList)
        notifyDataSetChanged()
    }

    class CardSiparisViewHolder(var binding: CardSiparisBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardSiparisViewHolder(
            CardSiparisBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCardSiparisViewHolder(holder as CardSiparisViewHolder, position)
    }

    private fun bindCardSiparisViewHolder(
        holder: CardSiparisViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val siparis = list[position]
            Glide.with(context).load(siparis.urunResimURL).into(imgUrunResmi)
            tvUrunAdi.text = siparis.urunAdi
            tvMasaNumarasi.text = "Masa : ${siparis.masaNumarasi}"
            cardTeslimEt.setOnClickListener {
                teslimEtClickListenerCustom?.let {
                    it(siparis.id)
                }
            }
        }
    }

    /** teslim et butonuna tıkladığımızda teslimEteTiklandi metodunu tetiklemek için kullanırız.  **/
    private var teslimEtClickListenerCustom: ((siparisId: String) -> Unit)? = null
    fun teslimEteTiklandi(f: ((siparisId: String) -> Unit)) {
        teslimEtClickListenerCustom = f
    }

    override fun getItemCount(): Int = list.size
}