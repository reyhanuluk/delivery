package com.ma.orderApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ma.orderApp.databinding.CardEskiSiparisBinding
import com.ma.orderApp.databinding.CardSiparisBinding
import com.ma.orderApp.model.Siparis


class CardEskiSiparisAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = ArrayList<Siparis>()
    val list get() = _list.toList()
    private lateinit var context: Context

    fun setList(newList: List<Siparis>) {
        _list.clear()
        _list.addAll(newList)
        notifyDataSetChanged()
    }

    class CardEskiSiparisViewHolder(var binding: CardEskiSiparisBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardEskiSiparisViewHolder(
            CardEskiSiparisBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCardEskiSiparisViewHolder(holder as CardEskiSiparisViewHolder, position)
    }

    private fun bindCardEskiSiparisViewHolder(
        holder: CardEskiSiparisViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val siparis = list[position]
            Glide.with(context).load(siparis.urunResimURL).into(imgUrunResmi)
            tvUrunAdi.text = siparis.urunAdi
            tvMasaNumarasi.text = "Masa : ${siparis.masaNumarasi}"
        }
    }

    override fun getItemCount(): Int = list.size
}