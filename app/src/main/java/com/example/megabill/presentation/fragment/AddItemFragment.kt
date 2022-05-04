package com.example.megabill.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.megabill.R
import com.example.megabill.databinding.FragmentAddItemBinding
import com.example.megabill.domain.entities.Person
import com.example.megabill.presentation.adapter.ChoosePersonAdapter
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.person.PersonViewModel
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var billViewModel : BillViewModel
    private lateinit var personViewModel : PersonViewModel
    private lateinit var adapterChoosePerson : ChoosePersonAdapter
    private var choosePersonList : MutableList<Person> = mutableListOf()
    private lateinit var namePerson : String
    private var _nameId : Int? = null
    private val nameId : Int
    get() = _nameId ?: throw RuntimeException("_nameId == null")

    private var _bind : FragmentAddItemBinding? = null
    private val bind : FragmentAddItemBinding
    get() = _bind ?: throw RuntimeException("FragmentAddItemBinding == null")

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
        _bind = FragmentAddItemBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billViewModel = ViewModelProvider(this)[BillViewModel::class.java]
        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        personViewModel.listPerson.observe(viewLifecycleOwner){
            choosePersonList = it
            setupRecyclerView()
        }
        bind.bAddItem.setOnClickListener {
            val itemName = bind.etItemName.text.toString()
            val price = Integer.valueOf(bind.etPrice.text.toString())
            billViewModel.addBillItem(namePerson, nameId, itemName, price)
            findNavController().navigate(R.id.action_addItemFragment_to_listBillFragment)
        }
    }

    private fun setupRecyclerView() : RecyclerView{
        val recyclerView = bind.recyclerPerson
        with(recyclerView){
            adapterChoosePerson = ChoosePersonAdapter(choosePersonList)
            adapter = adapterChoosePerson
            adapterChoosePerson.onSelectItem = {
                namePerson = it.name
                _nameId = it.id
                Toast.makeText(activity, "Name is ${it.name}", Toast.LENGTH_SHORT).show()
            }
        }
        return recyclerView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}