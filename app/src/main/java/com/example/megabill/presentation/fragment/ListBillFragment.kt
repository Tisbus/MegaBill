package com.example.megabill.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentListBillBinding
import com.example.megabill.domain.entities.Bill
import com.example.megabill.presentation.BillApp
import com.example.megabill.presentation.adapter.ListBillAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.factory.BillViewModelFactory
import com.example.megabill.presentation.viewmodel.total.TotalViewModel
import javax.inject.Inject

class ListBillFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory : BillViewModelFactory
    private val component by lazy{
        (requireActivity().application as BillApp).component
    }

    private lateinit var viewBillModel: BillViewModel
    private lateinit var modelTotal: TotalViewModel
    private lateinit var billAdapter: ListBillAdapter
    private var billList: MutableList<Bill> = mutableListOf()

    private var _bind: FragmentListBillBinding? = null
    private val bind: FragmentListBillBinding
        get() = _bind ?: throw RuntimeException("FragmentListBillBinding == null")

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
        _bind = FragmentListBillBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBillModel = ViewModelProvider(this, viewModelFactory)[BillViewModel::class.java]
        modelTotal = ViewModelProvider(this, viewModelFactory)[TotalViewModel::class.java]
        viewBillModel.listBill.observe(viewLifecycleOwner) {
            billList = it
            recyclerMain()
            checkSizeBillListNextButton()
            checkSizeBillListAddButton()
        }
        bind.fbAddItem.setOnClickListener {
            findNavController().navigate(R.id.action_listBillFragment_to_addItemFragment)
        }
        bind.bNextGoTotal.setOnClickListener {
            setData()
            findNavController().navigate(R.id.action_listBillFragment_to_totalBillFragment)
        }
    }

    private fun itemTHDelete() {
        val itemTouchDeleteCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewBillModel.deleteBillItem(billList[viewHolder.adapterPosition].id)
            }
        }
        val itemTouchDelete = ItemTouchHelper(itemTouchDeleteCallback)
        itemTouchDelete.attachToRecyclerView(recyclerSetup())
    }

    private fun checkSizeBillListNextButton(){
        with(bind){
            if(billList.isNotEmpty()){
                bNextGoTotal.visibility = View.VISIBLE
            }else{
                bNextGoTotal.visibility = View.INVISIBLE
            }
        }
    }
    private fun checkSizeBillListAddButton(){
        with(bind){
            if(billList.size > 10){
                ivCloudBillHelp.visibility = View.GONE
            }else{
                ivCloudBillHelp.visibility = View.VISIBLE
            }
        }
    }

    private fun recyclerMain() {
        recyclerSetup()
        itemTHDelete()
        editItem()
    }

    private fun setData() {
        modelTotal.deleteAllTotal()
        val listIdName = mutableSetOf<Int>()
        for (i in 0 until billList.size) {
            listIdName.add(billList[i].idName)
        }
        for (i in 0 until listIdName.size) {
            var namePerson: String = ""
            var listBuyProduct: String = ""
            var totalSum: Int = 0
            var totalSumToPerson: String = ""
            billList.filter {
                it.idName == listIdName.toList()[i]
            }.map {
                namePerson = it.name
                listBuyProduct += "${it.item} - ${it.price} руб.\n"
                totalSum += it.price
                totalSumToPerson = "Итого: $totalSum руб."
            }
            modelTotal.addItemTotal(
                namePerson,
                listBuyProduct.trim(),
                totalSumToPerson,
                sumTips = "",
                totalSumWithTips = ""
            )
        }
    }

    private fun recyclerSetup(): RecyclerView {
        val recycler = bind.recyclerListBill
        with(recycler) {
            billAdapter = ListBillAdapter(billList)
            adapter = billAdapter
            recycledViewPool.setMaxRecycledViews(
                0, 30
            )
        }
        return recycler
    }

    private fun editItem() {
        billAdapter.itemSelect = {
            val bundle = bundleOf(ARG_ITEM_ID to it.id)
            findNavController().navigate(R.id.action_listBillFragment_to_editItemFragment, bundle)
        }
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
    }
}