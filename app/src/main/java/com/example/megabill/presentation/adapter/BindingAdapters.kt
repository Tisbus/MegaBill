package com.example.megabill.presentation.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("tvTotalSumWithTipDetail")
fun tvTotalSumWithTipDetail(text: TextView, total: String) {
    text.text = String.format("Итого: %s", total)
}
