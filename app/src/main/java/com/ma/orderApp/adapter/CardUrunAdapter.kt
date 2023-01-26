package com.ma.orderApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ma.orderApp.databinding.CardUrunBinding
import com.ma.orderApp.model.Urun

class CardUrunAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var _list = ArrayList<Urun>()
    val list get() = _list.toList()
    private lateinit var context: Context

    fun setList(newList: List<Urun>) {
        _list.clear()
        _list.addAll(newList)
        notifyDataSetChanged()
    }

    class CardUrunViewHolder(var binding: CardUrunBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardUrunViewHolder(
            CardUrunBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCardUrunViewHolder(holder as CardUrunViewHolder, position)
    }

    /** gelen verileri card_urun.xml deki tasarım elemanlarına yerleştirir **/
    private fun bindCardUrunViewHolder(holder: CardUrunViewHolder, position: Int) {
        holder.binding.apply {
            val urun = list[position]
            Glide.with(context).load(urun.urunResimURL).into(imgUrunResmi)
            tvUrunFiyati.text = urun.urunFiyati.toString()
            tvUrunAdi.text = urun.urunAdi

            cardSiparisVer.setOnClickListener {
                siparisVerClickListenerCustom?.let {
                    it(urun)
                }
            }
        }
    }

    /** sipariş ver butonuna tıkladığımızda siparisVereTiklandi metodunu tetiklemek için kullanırız.  **/
    private var siparisVerClickListenerCustom: ((urun: Urun) -> Unit)? = null
    fun siparisVereTiklandi(f: ((urun: Urun) -> Unit)) {
        siparisVerClickListenerCustom = f
    }

    override fun getItemCount(): Int = list.size

}