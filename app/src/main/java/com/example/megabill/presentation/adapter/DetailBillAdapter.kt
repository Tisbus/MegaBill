package com.example.megabill.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.databinding.TotalItemBinding
import com.example.megabill.domain.entities.Total

class DetailBillAdapter(private val listDetail : List<Total>) : RecyclerView.Adapter<DetailBillAdapter.DetailBillViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBillViewHolder {
        val view = TotalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailBillViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailBillViewHolder, position: Int) {
        val itemList = listDetail[position]
        val binding = holder.bind
        with(binding){
            tvNamePerson.text = itemList.namePerson
            tvListProduct.text = itemList.listBuyProduct
            tvTotalSum.text = itemList.totalSumToPerson
            tvTipsSum.text = itemList.sumTips
            tvTotalWithTips.text = itemList.totalSumWithTips
        }
    }

    override fun getItemCount(): Int {
        return listDetail.size
    }

    inner class DetailBillViewHolder(val bind : TotalItemBinding) : RecyclerView.ViewHolder(bind.root)
}