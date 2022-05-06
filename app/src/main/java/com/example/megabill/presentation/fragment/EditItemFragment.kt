package com.example.megabill.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.megabill.R
import com.example.megabill.databinding.FragmentEditItemBinding
import kotlin.math.log

class EditItemFragment : Fragment() {
    private var itemId: Int? = null

    private var _bind : FragmentEditItemBinding? = null
    private val bind : FragmentEditItemBinding
    get() = _bind ?: throw RuntimeException("FragmentEditItemBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt(ARG_ITEM_ID)
        }
        Log.i("check", "$itemId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _bind = FragmentEditItemBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.bEditItem.setOnClickListener {
            findNavController().navigate(R.id.action_editItemFragment_to_listBillFragment)
        }
    }

    companion object{
        private const val ARG_ITEM_ID = "itemId"
    }
}