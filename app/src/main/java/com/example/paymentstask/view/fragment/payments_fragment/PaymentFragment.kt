package com.example.paymentstask.view.fragment.payments_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paymentstask.data.base.ResultResponse
import com.example.paymentstask.databinding.FragmentPaymentBinding
import com.example.paymentstask.view.MainActivity
import com.example.paymentstask.view.rv_adapters.PaymentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PaymentFragment : Fragment() {
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PaymentsViewModel by viewModels()
    private val paymentAdapter = PaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLogout()
        setUpPaymentAdapter()
        getPayments()

    }

    private fun setUpLogout() {
        binding.logout.setOnClickListener {
            viewModel.removeToken()
            (requireActivity() as MainActivity).openLogin()
        }
    }

    private fun getPayments() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getPayments().collect { result ->
                    when (result) {
                        is ResultResponse.Loading -> {
                            withContext(Dispatchers.Main) {
                                showLoadingState(true)
                            }
                        }

                        is ResultResponse.Success -> {
                            withContext(
                                Dispatchers.Main
                            ) {
                                showLoadingState(false)
                                paymentAdapter.submitList(result.data.response)
                            }
                        }

                        is ResultResponse.Error -> {
                            withContext(Dispatchers.Main) {
                                showLoadingState(false)
                                if (result.message.isEmpty())
                                    Toast.makeText(
                                        requireContext(), "Something went wrong",
                                        Toast.LENGTH_LONG
                                    ).show()
                                else
                                    Toast.makeText(
                                        requireContext(), result.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setUpPaymentAdapter() {
        binding.recycler.apply {
            adapter = paymentAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun showLoadingState(isLoading: Boolean) {
        if (isLoading) {
            with(binding) {
                recycler.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                recycler.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }
}