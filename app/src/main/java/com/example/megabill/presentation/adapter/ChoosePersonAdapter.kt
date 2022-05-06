package com.example.megabill.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.ChoosePersonDisableBinding
import com.example.megabill.databinding.ChoosePersonEnableBinding
import com.example.megabill.domain.entities.Person

class ChoosePersonAdapter(private var personList : MutableList<Person>) : RecyclerView.Adapter<ChoosePersonAdapter.ChoosePersonViewHolder>() {

    var onSelectItem : ((Person) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosePersonViewHolder {
        val layout = when(viewType){
            STATUS_DISABLED -> R.layout.choose_person_disable
            STATUS_ENABLED -> R.layout.choose_person_enable
            else -> throw RuntimeException("Error: $viewType")
        }
        val bindLayout = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layout, parent, false)
        return ChoosePersonViewHolder(bindLayout)
    }

    override fun onBindViewHolder(holder: ChoosePersonViewHolder, position: Int) {
        val bind = holder.binding
        val itemPerson = personList[position]
        when(bind){
            is ChoosePersonDisableBinding ->{
                bind.tvChoosePerson.text = itemPerson.name
            }
            is ChoosePersonEnableBinding ->{
                bind.tvChoosePerson.text = itemPerson.name
            }
        }

        bind.root.setOnClickListener {
            onSelectItem?.invoke(itemPerson)
        }
    }

    override fun getItemCount(): Int {
       return personList.size
    }

    override fun getItemViewType(position: Int): Int {
        val itemPerson = personList[position]
        return if(itemPerson.status){
            STATUS_ENABLED
        }else{
            STATUS_DISABLED
        }
    }

    companion object{
        const val STATUS_DISABLED = 100
        const val STATUS_ENABLED = 101
    }

    inner class ChoosePersonViewHolder(val binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}