package com.example.megabill.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.databinding.BillItemBinding
import com.example.megabill.domain.entities.Bill

class ListBillAdapter(private var billList :MutableList<Bill>) : RecyclerView.Adapter<ListBillAdapter.ListBillViewHolder>(){

    var itemSelect : ((Bill)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBillViewHolder {
        val binding = BillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListBillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListBillViewHolder, position: Int) {
        val bill = billList[position]
        with(holder){
            bindItem(bill)
            bind.root.setOnClickListener {
                itemSelect?.invoke(bill)
            }
        }
    }

    override fun getItemCount(): Int {
        return billList.size
    }

    class ListBillViewHolder(val bind : BillItemBinding) : RecyclerView.ViewHolder(bind.root){
        fun bindItem(bill : Bill){
            bind.tvName.text = bill.name
            bind.tvItem.text = bill.item
            bind.tvPrice.text = String.format("%s руб.", bill.price.toString())
        }
    }
}
