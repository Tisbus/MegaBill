package com.example.megabill.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentTotalBillBinding
import com.example.megabill.domain.entities.Total
import com.example.megabill.presentation.adapter.TotalBillAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.history.BillHistoryViewModel
import com.example.megabill.presentation.viewmodel.person.PersonViewModel
import com.example.megabill.presentation.viewmodel.total.TotalViewModel
import java.text.SimpleDateFormat
import java.util.*

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
    private var _bind: FragmentTotalBillBinding? = null
    private val bind: FragmentTotalBillBinding
        get() = _bind ?: throw RuntimeException("FragmentTotalBillBinding == null")
    private var listAllTotal = mutableListOf<Total>()
    private lateinit var modelTotal: TotalViewModel
    private lateinit var modelHistory: BillHistoryViewModel
    private lateinit var modelPerson: PersonViewModel
    private lateinit var modelBill: BillViewModel
    private lateinit var totalAdapter: TotalBillAdapter
    private var _percentTips: Int? = null
    private val percentTips: Int
        get() = _percentTips ?: throw RuntimeException("percentTips == null")
    private var _sumTip: Int? = null
    private val sumTip: Int
        get() = _sumTip ?: throw RuntimeException("sumTip == null")
    private var _totalSumTipToPerson: Int? = null
    private val totalSumTipToPerson: Int
        get() = _totalSumTipToPerson ?: throw RuntimeException("totalSumWithTip == null")
    private var _totalSumWithTip: Int? = null
    private val totalSumWithTip: Int
        get() = _totalSumWithTip ?: throw RuntimeException("totalSumWithTip == null")


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
        bind.etTipsSize.setText("0")
        modelTotal = ViewModelProvider(this)[TotalViewModel::class.java]
        modelHistory = ViewModelProvider(this)[BillHistoryViewModel::class.java]
        modelPerson = ViewModelProvider(this)[PersonViewModel::class.java]
        modelBill = ViewModelProvider(this)[BillViewModel::class.java]
        modelTotal.listTotal.observe(viewLifecycleOwner) {
            listAllTotal = it
            recyclerSetup()
            bind.tvSumBill.text =
                String.format("%s %s %s", "Сумма счёта:", calculateTotalSum().toString(), "руб.")
        }
        changeSwitchTip()
        bind.bAcceptTip.setOnClickListener {
            calculateTips()

        }
/*        getPercentTip()*/
        bind.bSaveBill.setOnClickListener {
            saveDataToBillHistory()
            deleteAllTable()
            findNavController().navigate(R.id.action_totalBillFragment_to_startFragment)
        }
    }

    private fun deleteAllTable() {
        modelPerson.deleteAllPersonItem()
        modelBill.deleteAllBillItem()
        modelTotal.deleteAllTotal()
    }

    private fun saveDataToBillHistory() {
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val data = sdf.format(Date())
        val partyName = bind.etNameOfParty.text.toString()
        val totalSum = calculateTotalSum().toString()
        val totalSumWithTips = totalSumWithTip.toString().ifEmpty {""}
        modelHistory.addBillHistoryItem(data, partyName, listAllTotal, totalSum, totalSumWithTips)
    }

    private fun calculateTips() {
        val total: Int = calculateTotalSum()
        _percentTips = bind.etTipsSize.text.toString().toInt()
        _sumTip = total * percentTips / 100
        _totalSumTipToPerson = sumTip / listAllTotal.size
        _totalSumWithTip = total + sumTip
        bind.tvSumBillWithTip.text =
            String.format("%s %s %s", "С чаевыми", totalSumWithTip.toString(), "руб.")
        for (item in 0 until listAllTotal.size) {
            listAllTotal[item].sumTips = "${_totalSumTipToPerson.toString()} руб."
            listAllTotal[item].totalSumWithTips =
                "Итого: ${
                    (totalSumTipToPerson + listAllTotal[item].totalSumToPerson.substring(
                        7,
                        listAllTotal[item].totalSumToPerson.length - 5
                    ).toInt())
                } руб."
        }
        totalAdapter.notifyDataSetChanged()
    }

    private fun calculateTotalSum(): Int {
        var total: Int = 0
        for (i in 0 until listAllTotal.size) {
            total += listAllTotal[i].totalSumToPerson.substring(
                7,
                listAllTotal[i].totalSumToPerson.length - 5
            ).toInt()
        }
        return total
    }

/*    private fun getPercentTip() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
               return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                _percentTips = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        }
        bind.etTipsSize.addTextChangedListener(textWatcher)
    }*/

    private fun changeSwitchTip() {
        with(bind) {
            swTips.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    etTipsSize.visibility = View.VISIBLE
                    bAcceptTip.visibility = View.VISIBLE
                } else {
                    etTipsSize.visibility = View.GONE
                    bAcceptTip.visibility = View.GONE
                    bind.etTipsSize.setText("0")
                    calculateTips()
                    bind.tvSumBillWithTip.text = ""
                }
            }
        }
    }

    private fun recyclerSetup(): RecyclerView {
        val recycler = bind.recyclerTotal
        with(recycler) {
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