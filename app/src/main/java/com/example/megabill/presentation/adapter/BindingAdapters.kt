package com.example.megabill.presentation.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("tvTotalSumWithTipDetail")
fun tvTotalSumWithTipDetail(text: TextView, total: String) {
    text.text = String.format("Итого: %s", total)
}
@BindingAdapter("etItemNameCheck")
fun bindCheckNameError(edit : TextInputLayout, isError : Boolean){
    val message = if(isError){
        "Пустой текст"
    }else{
        null
    }
    edit.error = message
}

@BindingAdapter("etPriceCheck")
fun bindCheckPriceError(edit : TextInputLayout, isError : Boolean) {
    val message = if(isError){
        "Цена путая или ровна нулю"
    }else{
        null
    }
    edit.error = message
}