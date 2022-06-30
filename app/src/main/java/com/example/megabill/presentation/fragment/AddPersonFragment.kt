package com.example.megabill.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.megabill.R
import com.example.megabill.databinding.FragmentAddPersonBinding
import com.example.megabill.domain.entities.Person
import com.example.megabill.presentation.BillApp
import com.example.megabill.presentation.viewmodel.factory.BillViewModelFactory
import com.example.megabill.presentation.viewmodel.person.PersonViewModel
import javax.inject.Inject

class AddPersonFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory : BillViewModelFactory

    private val component by lazy{
        (requireActivity().application as BillApp).component
    }

    private var _bind: FragmentAddPersonBinding? = null
    private val bind: FragmentAddPersonBinding
        get() = _bind ?: throw RuntimeException("FragmentAddPersonBinding == null")

    lateinit var viewModel: PersonViewModel

    private var listPerson: MutableList<Person> = mutableListOf()

    private var listPersonView: MutableList<View> = mutableListOf()

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
        _bind = FragmentAddPersonBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[PersonViewModel::class.java]
        viewModel.listPerson.observe(viewLifecycleOwner) {
            clearData()
            listPerson = it
            loadViewPerson()
            checkAllButton()
        }
        bind.bAddNewPerson.setOnClickListener {
            addNewEditText()
            checkAllButton()
        }
        bind.bNextGoBillList.setOnClickListener {
            saveNameToDb()
            clearData()
            findNavController().navigate(R.id.action_addPersonFragment_to_listBillFragment)
        }

    }

    private fun checkSizeListPerson() {
        with(bind) {
            if (listPersonView.size > 5) {
                ivCloudPersonHelp.visibility = View.GONE
            } else {
                ivCloudPersonHelp.visibility = View.VISIBLE
            }
        }
    }

    private fun checkAllButton(){
        checkCanNextStep()
        checkSizeListPerson()
    }

    private fun checkCanNextStep() {
        with(bind) {
            if (listPersonView.isNotEmpty()) {
                bNextGoBillList.visibility = View.VISIBLE
            } else {
                bNextGoBillList.visibility = View.INVISIBLE
            }
        }
    }

    private fun clearData() {
        listPerson.clear()
        listPersonView.clear()
        bind.rlPerson.removeAllViews()
    }

    private fun loadViewPerson() {
        for (item in listPerson) {
            val view = layoutInflater.inflate(R.layout.add_new_person, null)
            view.findViewById<EditText>(R.id.etPersonName).setText(item.name)
            val bDelete = view.findViewById<Button>(R.id.bDeletePerson)
            bDelete.setOnClickListener {
                (view.parent as LinearLayout).removeView(view)
                listPersonView.remove(view)
                checkAllButton()
            }
            bind.rlPerson.addView(view)
            listPersonView.add(view)
            checkAllButton()
        }
    }

    private fun saveNameToDb() {
        viewModel.deleteAllPersonItem()
        var id = Person.UNDEFINED_ID
        for (item in listPersonView) {
            val name = item.findViewById<EditText>(R.id.etPersonName).text.toString()
            viewModel.addPersonItem(id++, name)
        }
    }

    private fun addNewEditText() {
        val view = layoutInflater.inflate(R.layout.add_new_person, null)
        val bDelete = view.findViewById<Button>(R.id.bDeletePerson)

        bDelete.setOnClickListener {
            (view.parent as LinearLayout).removeView(view)
            listPersonView.remove(view)
            checkAllButton()
        }
        bind.rlPerson.addView(view)
        listPersonView.add(view)
    }
}