package com.example.paymentstask.view.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paymentstask.data.entity.Payment
import com.example.paymentstask.databinding.ItemPaymentBinding

class PaymentAdapter :
    ListAdapter<Payment, PaymentAdapter.PaymentViewHolder>(PaymentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaymentBinding.inflate(inflater, parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = getItem(position)
        holder.bind(payment)
    }

    inner class PaymentViewHolder(private val binding: ItemPaymentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(payment: Payment) {
            binding.apply {
                title.text = payment.title
                paymentId.text = "ID: ${payment.id}"
                payment.amount?.takeIf { it.isNotBlank() }?.let {
                    amount.text = "Amount: $it"
                } ?: run {
                    amount.visibility = View.GONE
                }
                payment.created?.takeIf { it.isNotBlank() }?.let {
                    created.text = "Created: $it"
                } ?: run {
                    created.visibility = View.GONE
                }
            }
        }
    }

    private class PaymentDiffCallback : DiffUtil.ItemCallback<Payment>() {
        override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
            return oldItem == newItem
        }
    }
}
