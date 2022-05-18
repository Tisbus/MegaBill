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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _bind : FragmentStartBinding? = null
    private val bind : FragmentStartBinding
    get() = _bind ?: throw RuntimeException("FragmentStartBinding == null")

    private lateinit var modelHistory : BillHistoryViewModel
    private lateinit var adapterStart : StartBillAdapter
    private var listStartBill : MutableList<BillHistory> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
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
        }
        bind.buttonDeleteAll.setOnClickListener {
            modelHistory.deleteAllBillHistory()
        }
        bind.flButtonAddNewBill.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addPersonFragment)
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