package com.example.megabill.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentStartBinding
import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.presentation.adapter.StartBillAdapter
import com.example.megabill.presentation.viewmodel.history.BillHistoryViewModel
import java.lang.RuntimeException

class StartFragment : Fragment() {

    private var _bind : FragmentStartBinding? = null
    private val bind : FragmentStartBinding
    get() = _bind ?: throw RuntimeException("FragmentStartBinding == null")

    private lateinit var modelHistory : BillHistoryViewModel
    private lateinit var adapterStart : StartBillAdapter
    private var listStartBill : MutableList<BillHistory> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentStartBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modelHistory = ViewModelProvider(this)[BillHistoryViewModel::class.java]

        modelHistory.listBillHistory.observe(viewLifecycleOwner){
            listStartBill = it
            setupRecycler()
            itemDeleteTouch()
            goDetailHistoryItem()
            checkSizeListHistory()
        }
        bind.buttonDeleteAll.setOnClickListener {
            modelHistory.deleteAllBillHistory()
        }
        bind.flButtonAddNewBill.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addPersonFragment)
        }
    }

    private fun checkSizeListHistory(){
        val checkSize = modelHistory.listBillHistory.value?.isNotEmpty()
        with(bind){
            if(checkSize == true){
                svBillHistory.visibility = View.VISIBLE
                iwCloudStartHelp.visibility = View.GONE
            }else{
                svBillHistory.visibility = View.INVISIBLE
                iwCloudStartHelp.visibility = View.VISIBLE
            }
        }

    }

    private fun itemDeleteTouch() {
            val itemDeleteTouchCallback = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    modelHistory.deleteBillHistoryItem(listStartBill[viewHolder.adapterPosition].id)
                }
            }
            val itemDeleteTouchHelper = ItemTouchHelper(itemDeleteTouchCallback)
            itemDeleteTouchHelper.attachToRecyclerView(setupRecycler())
    }

    private fun goDetailHistoryItem(){
        adapterStart.itemSelect = {
            val bundle = bundleOf(ARG_ITEM_ID to it.id)
            findNavController().navigate(R.id.action_startFragment_to_billDetailFragment, bundle)
        }
    }

    private fun setupRecycler() : RecyclerView{
        val recycler = bind.recyclerMain
        with(recycler){
            adapterStart = StartBillAdapter(listStartBill)
            adapter = adapterStart
        }
        return recycler
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
    }
}