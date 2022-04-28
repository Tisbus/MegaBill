package com.example.megabill.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.megabill.R
import com.example.megabill.data.entities.PersonData
import com.example.megabill.databinding.FragmentAddPersonBinding
import com.example.megabill.domain.entities.Person
import com.example.megabill.presentation.viewmodel.BillViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddPersonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddPersonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _bind: FragmentAddPersonBinding? = null
    private val bind: FragmentAddPersonBinding
        get() = _bind ?: throw RuntimeException("FragmentAddPersonBinding == null")

    lateinit var viewModel : BillViewModel

    private var listPerson : MutableList<Person> = mutableListOf()

    private var listPersonView : MutableList<View> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.i("check", "stage 2")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentAddPersonBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BillViewModel::class.java]
        viewModel.listPerson.observe(viewLifecycleOwner){
            clearData()
            listPerson = it
            loadViewPerson()
        }
        bind.bAddNewPerson.setOnClickListener {
            addNewEditText()
        }
        bind.bNextGoBillList.setOnClickListener {
            saveNameToDb()
            clearData()
            findNavController().navigate(R.id.action_addPersonFragment_to_listBillFragment)
        }

    }

    private fun clearData(){
        listPerson.clear()
        listPersonView.clear()
        bind.rlPerson.removeAllViews()
    }

    private fun loadViewPerson(){
        for (item in listPerson){
            val view = layoutInflater.inflate(R.layout.add_new_person, null)
            view.findViewById<EditText>(R.id.etPersonName).setText(item.name)
            val bDelete = view.findViewById<Button>(R.id.bDeletePerson)
            bDelete.setOnClickListener {
                (view.parent as LinearLayout).removeView(view)
                listPersonView.remove(view)
            }
            bind.rlPerson.addView(view)
            listPersonView.add(view)
        }
    }

    private fun saveNameToDb(){
        viewModel.deleteAllPersonItem()
        var count = Person.UNDEFINED_ID
        for(item in listPersonView){
            val name = item.findViewById<EditText>(R.id.etPersonName).text.toString()
            viewModel.addPersonItem(name, count++)
        }
    }

    private fun addNewEditText() {
        val view = layoutInflater.inflate(R.layout.add_new_person, null)
        val bDelete = view.findViewById<Button>(R.id.bDeletePerson)

        bDelete.setOnClickListener {
            (view.parent as LinearLayout).removeView(view)
            listPersonView.remove(view)
        }
        bind.rlPerson.addView(view)
        listPersonView.add(view)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddPersonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddPersonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}