package com.example.megabill.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentAddItemBinding
import com.example.megabill.domain.entities.Person
import com.example.megabill.presentation.BillApp
import com.example.megabill.presentation.adapter.ChoosePersonAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.factory.BillViewModelFactory
import com.example.megabill.presentation.viewmodel.person.PersonViewModel
import javax.inject.Inject

class AddItemFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: BillViewModelFactory

    private val component by lazy{
        (requireActivity().application as BillApp).component
    }

    private lateinit var billViewModel: BillViewModel
    private lateinit var personViewModel: PersonViewModel
    private lateinit var adapterChoosePerson: ChoosePersonAdapter
    private var choosePersonList: MutableList<Person> = mutableListOf()
    private lateinit var namePerson: String
    private var _nameId: Int? = null
    private val nameId: Int
        get() = _nameId ?: throw RuntimeException("_nameId == null")

    private var _bind: FragmentAddItemBinding? = null
    private val bind: FragmentAddItemBinding
        get() = _bind ?: throw RuntimeException("FragmentAddItemBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentAddItemBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billViewModel = ViewModelProvider(this, viewModelFactory)[BillViewModel::class.java]
        personViewModel = ViewModelProvider(this, viewModelFactory)[PersonViewModel::class.java]
        personViewModel.listPerson.observe(viewLifecycleOwner) {
            choosePersonList = it
            setupRecyclerView()
            itemSelect()
            addTextChangedListener()
            addItemBill()
        }
    }

    private fun addItemBill() {
        bind.bAddItem.setOnClickListener {
            val itemName = bind.etItemName.text.toString()
            val price = bind.etPrice.text.toString()
            billViewModel.addBillItem(
                namePerson,
                nameId,
                itemName,
                price
            )
            if(checkError(itemName, price)){
                findNavController().navigate(R.id.action_addItemFragment_to_listBillFragment)
            }

        }
    }

    private fun checkError(item : String, price : String) : Boolean{
        return item.isNotBlank() && price.toInt() >= 0
    }

    private fun setupRecyclerView(): RecyclerView {
        val recyclerView = bind.recyclerPerson
        with(recyclerView) {
            adapterChoosePerson = ChoosePersonAdapter(choosePersonList)
            adapter = adapterChoosePerson
            bind.bill = billViewModel
            bind.lifecycleOwner = viewLifecycleOwner
        }
        return recyclerView
    }

    private fun addTextChangedListener(){
        bind.etItemName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                billViewModel.resetCheckErrorName()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        bind.etPrice.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                billViewModel.resetCheckErrorPrice()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun itemSelect() {
        adapterChoosePerson.onSelectItem = {
            namePerson = it.name
            _nameId = it.id
            it.status = true
            choosePersonList.filter { i -> i.id != it.id}.map { i -> i.status = false }
            adapterChoosePerson.notifyDataSetChanged()
            Toast.makeText(activity, "Name is ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }
}