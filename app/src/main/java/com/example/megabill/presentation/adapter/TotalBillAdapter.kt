package com.example.megabill.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.databinding.TotalItemBinding
import com.example.megabill.domain.entities.Total

class TotalBillAdapter(private val totalList : MutableList<Total>) : RecyclerView.Adapter<TotalBillAdapter.TotalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalViewHolder {
        val view = TotalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TotalViewHolder(view)
    }

    override fun onBindViewHolder(holder: TotalViewHolder, position: Int) {
        val bind = holder.binding
        val itemTotal = totalList[position]
        with(bind){
            tvNamePerson.text = itemTotal.namePerson
            tvListProduct.text = itemTotal.listBuyProduct
            tvTotalSum.text = itemTotal.totalSumToPerson
            tvTipsSum.text = itemTotal.sumTips
            tvTotalWithTips.text = itemTotal.totalSumWithTips
        }
    }

    override fun getItemCount(): Int {
        return totalList.size
    }

    inner class TotalViewHolder(val binding : TotalItemBinding) : RecyclerView.ViewHolder(binding.root)
}