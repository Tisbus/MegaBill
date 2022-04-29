package com.example.megabill.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentListBillBinding
import com.example.megabill.domain.entities.Bill
import com.example.megabill.presentation.adapter.ListBillAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListBillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListBillFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewBillModel : BillViewModel
    private lateinit var billAdapter: ListBillAdapter
    private var billList: MutableList<Bill> = mutableListOf()

    private var _bind : FragmentListBillBinding? = null
    private val bind : FragmentListBillBinding
    get() = _bind ?: throw RuntimeException("FragmentListBillBinding == null")

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
        _bind = FragmentListBillBinding.inflate(inflater,container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBillModel = ViewModelProvider(this)[BillViewModel::class.java]
        viewBillModel.listBill.observe(viewLifecycleOwner){
            billList = it
            recyclerSetup()
        }
        bind.fbAddItem.setOnClickListener {
            findNavController().navigate(R.id.action_listBillFragment_to_addItemFragment)
        }
        bind.bNextGoTotal.setOnClickListener {
            findNavController().navigate(R.id.action_listBillFragment_to_totalBillFragment)
        }
    }

    private fun recyclerSetup() : RecyclerView {
        val recyclerMain = bind.recyclerListBill
        with(recyclerMain){
            billAdapter = ListBillAdapter(billList)
            adapter = billAdapter
            recycledViewPool.setMaxRecycledViews(
                0, 30
            )
        }
        return recyclerMain
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListBillFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListBillFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}