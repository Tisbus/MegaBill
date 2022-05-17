package com.example.megabill.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.databinding.StartItemBinding
import com.example.megabill.domain.entities.BillHistory

class StartBillAdapter(private val listBillHistory : MutableList<BillHistory>) : RecyclerView.Adapter<StartBillAdapter.StartBillViewHolder>() {

    var itemSelect : ((BillHistory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartBillViewHolder {
        val view = StartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StartBillViewHolder(view)
    }

    override fun onBindViewHolder(holder: StartBillViewHolder, position: Int) {
        val bind = holder.binding
        val itemHistory = listBillHistory[position]
        with(bind){
            tvNameParty.text = itemHistory.partyName
            tvDateParty.text = itemHistory.data
            tvTotalSumWithTip.text = itemHistory.totalSumWithTip
            root.setOnClickListener {
                itemSelect?.invoke(itemHistory)
            }
        }
    }

    override fun getItemCount(): Int {
        return listBillHistory.size
    }

    inner class StartBillViewHolder(val binding : StartItemBinding) :  RecyclerView.ViewHolder(binding.root)
}