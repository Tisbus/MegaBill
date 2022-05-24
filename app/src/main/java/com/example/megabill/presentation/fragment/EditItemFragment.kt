package com.example.megabill.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentEditItemBinding
import com.example.megabill.domain.entities.Person
import com.example.megabill.presentation.adapter.ChoosePersonAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.person.PersonViewModel

class EditItemFragment : Fragment() {

    private var itemId: Int? = null

    private var _bind: FragmentEditItemBinding? = null
    private val bind: FragmentEditItemBinding
        get() = _bind ?: throw RuntimeException("FragmentEditItemBinding == null")

    private lateinit var viewBillModel: BillViewModel
    private lateinit var viewPersonModel: PersonViewModel
    private lateinit var adapterChoosePerson: ChoosePersonAdapter
    private var choosePersonList: MutableList<Person> = mutableListOf()

    private lateinit var namePerson: String
    private var _nameId: Int? = null
    private val nameId: Int
        get() = _nameId ?: throw java.lang.RuntimeException("_nameId == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArg()
    }

    private fun parseArg() {
        itemId = arguments?.getInt(ARG_ITEM_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentEditItemBinding.inflate(inflater, container, false)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBillModel = ViewModelProvider(this)[BillViewModel::class.java]
        viewPersonModel = ViewModelProvider(this)[PersonViewModel::class.java]
        getData()
        viewPersonModel.listPerson.observe(viewLifecycleOwner) {
            choosePersonList = it
            setupRecycler()
            itemSelect()
        }
        saveEditBill()
    }

    private fun saveEditBill() {
        bind.bEditItem.setOnClickListener {
            val item = bind.etItemName.text.toString()
            val price = bind.etPrice.text.toString()
            viewBillModel.editBillItem(namePerson, nameId, item, price)
            if(checkError(item, price)){
                findNavController().navigate(R.id.action_editItemFragment_to_listBillFragment)
            }
        }
    }

    private fun checkError(item : String, price : String) : Boolean{
        return item.isNotBlank() && price.toInt() >= 0
    }

    private fun setupRecycler(): RecyclerView {
        val recycler = bind.recyclerPerson
        with(recycler) {
            adapterChoosePerson = ChoosePersonAdapter(choosePersonList)
            adapter = adapterChoosePerson
            bind.viewBill = viewBillModel
            bind.lifecycleOwner = viewLifecycleOwner
        }
        return recycler
    }

    private fun itemSelect() {
        adapterChoosePerson.onSelectItem = {
            _nameId = it.id
            namePerson = it.name
            it.status = true
            choosePersonList.filter { i -> i.id != it.id}.map { i -> i.status = false }
            adapterChoosePerson.notifyDataSetChanged()
            Toast.makeText(activity, "Name is ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData() {
        itemId?.let {
            viewBillModel.getBillItem(it)
        }
        with(bind) {
            viewBill = viewBillModel
            lifecycleOwner = viewLifecycleOwner
            val bill = viewBillModel.getBillItemLD.value
            with(bill) {
                this?.let {
                    _nameId = it.idName
                    namePerson = it.name
                }
            }
        }
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
    }
}