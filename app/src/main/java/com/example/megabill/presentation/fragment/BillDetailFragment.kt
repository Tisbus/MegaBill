package com.example.megabill.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.megabill.R
import com.example.megabill.databinding.FragmentBillDetailBinding


class BillDetailFragment : Fragment() {

    private var itemId : Int? = null

    private var _bind : FragmentBillDetailBinding? = null
    private val bind : FragmentBillDetailBinding
    get() = _bind ?: throw RuntimeException("FragmentBillDetailBinding == null")

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
        bind.tvTitle.text = itemId.toString()
    }

    companion object {
        private const val ARG_ITEM_ID = "itemId"
    }
}