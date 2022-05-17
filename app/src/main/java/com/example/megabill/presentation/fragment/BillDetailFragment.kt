package com.example.megabill.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentBillDetailBinding
import com.example.megabill.domain.entities.Total
import com.example.megabill.presentation.adapter.DetailBillAdapter
import com.example.megabill.presentation.viewmodel.history.BillHistoryViewModel


class BillDetailFragment : Fragment() {

    private var itemId : Int? = null

    private var _bind : FragmentBillDetailBinding? = null
    private val bind : FragmentBillDetailBinding
    get() = _bind ?: throw RuntimeException("FragmentBillDetailBinding == null")
    private lateinit var modelBillHistory : BillHistoryViewModel
    private lateinit var adapterDetail : DetailBillAdapter
    private var listTotalForDetail : List<Total> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs(){
        itemId = arguments?.getInt(ARG_ITEM_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentBillDetailBinding.inflate(inflater,container, false )
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modelBillHistory = ViewModelProvider(this)[BillHistoryViewModel::class.java]
        getData()
        setupRecycler()
        backButton()
    }

    private fun getData() {
        itemId?.let { modelBillHistory.getBillHistoryItem(it) }
        with(bind){
            history = modelBillHistory
            lifecycleOwner = viewLifecycleOwner
        }
        listTotalForDetail = modelBillHistory.getItemHistoryLD.value?.itemProduct
            ?: throw RuntimeException("itemProduct == null")
    }

    private fun setupRecycler() : RecyclerView{
        val recycler = bind.recyclerDetail
        with(recycler){
            adapterDetail = DetailBillAdapter(listTotalForDetail)
            adapter = adapterDetail
        }
        return recycler
    }

    private fun backButton() {
        bind.bBack.setOnClickListener {
            findNavController().navigate(R.id.action_billDetailFragment_to_startFragment)
        }
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
    }
}