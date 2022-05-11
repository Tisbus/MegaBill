package com.example.megabill.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentTotalBillBinding
import com.example.megabill.domain.entities.Total
import com.example.megabill.presentation.adapter.TotalBillAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.total.TotalViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TotalBillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TotalBillFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _bind : FragmentTotalBillBinding? = null
    private val bind : FragmentTotalBillBinding
    get() = _bind ?: throw RuntimeException("FragmentTotalBillBinding == null")
    private var listAllTotal = mutableListOf<Total>()
    private lateinit var modelTotal : TotalViewModel
    private lateinit var totalAdapter : TotalBillAdapter

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
        _bind = FragmentTotalBillBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modelTotal = ViewModelProvider(this)[TotalViewModel::class.java]
        modelTotal.listTotal.observe(viewLifecycleOwner){
            listAllTotal = it
            recyclerSetup()
        }
        bind.bSaveBill.setOnClickListener {
            findNavController().navigate(R.id.action_totalBillFragment_to_startFragment)
        }
    }

    fun recyclerSetup() : RecyclerView{
        val recycler = bind.recyclerTotal
        with(recycler){
            totalAdapter = TotalBillAdapter(listAllTotal)
            adapter = totalAdapter
        }
        return recycler
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TotalBillFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TotalBillFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}