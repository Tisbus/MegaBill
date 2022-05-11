package com.example.megabill.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentListBillBinding
import com.example.megabill.domain.entities.Bill
import com.example.megabill.presentation.adapter.ListBillAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.total.TotalViewModel

class ListBillFragment : Fragment() {

    private lateinit var viewBillModel : BillViewModel
    private lateinit var modelTotal : TotalViewModel
    private lateinit var billAdapter: ListBillAdapter
    private var billList: MutableList<Bill> = mutableListOf()

    private var _bind : FragmentListBillBinding? = null
    private val bind : FragmentListBillBinding
    get() = _bind ?: throw RuntimeException("FragmentListBillBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentListBillBinding.inflate(inflater,container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBillModel = ViewModelProvider(this)[BillViewModel::class.java]
        modelTotal = ViewModelProvider(this)[TotalViewModel::class.java]
        viewBillModel.listBill.observe(viewLifecycleOwner){
            billList = it
            recyclerMain()
        }
        bind.fbAddItem.setOnClickListener {
            findNavController().navigate(R.id.action_listBillFragment_to_addItemFragment)
        }
        bind.bNextGoTotal.setOnClickListener {
            findNavController().navigate(R.id.action_listBillFragment_to_totalBillFragment)
        }
    }

    private fun recyclerMain(){
        recyclerSetup()
        editItem()
    }

    private fun getData(){
    }

    private fun recyclerSetup() : RecyclerView {
        val recycler = bind.recyclerListBill
        with(recycler){
            billAdapter = ListBillAdapter(billList)
            adapter = billAdapter
            recycledViewPool.setMaxRecycledViews(
                0, 30
            )
        }
        return recycler
    }

    private fun editItem(){
        billAdapter.itemSelect = {
            Toast.makeText(activity, "${it.id}", Toast.LENGTH_SHORT).show()
            val bundle = bundleOf(ARG_ITEM_ID to it.id)
            findNavController().navigate(R.id.action_listBillFragment_to_editItemFragment, bundle)
        }
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
    }
}