package com.example.megabill.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.databinding.ChoosePersonDisableBinding
import com.example.megabill.domain.entities.Person

class ChoosePersonAdapter(private var personList : MutableList<Person>) : RecyclerView.Adapter<ChoosePersonAdapter.ChoosePersonViewHolder>() {

    var onSelectItem : ((Person) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosePersonViewHolder {
        val binding = ChoosePersonDisableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChoosePersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChoosePersonViewHolder, position: Int) {
        holder.bind(personList[position])
        holder.itemView.setOnClickListener {
            onSelectItem?.invoke(personList[position])
        }
    }

    override fun getItemCount(): Int {
       return personList.size
    }

    inner class ChoosePersonViewHolder(private val binding : ChoosePersonDisableBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(person : Person){
            binding.tvChoosePerson.text = person.name
        }
        init {
            binding.tvChoosePerson.setOnClickListener {
                onSelectItem?.invoke(personList[adapterPosition])
            }
        }
    }
}