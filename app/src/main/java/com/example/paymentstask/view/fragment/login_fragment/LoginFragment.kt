package com.example.paymentstask.view.fragment.login_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.paymentstask.data.base.ResultResponse
import com.example.paymentstask.data.entity.LoginInfo
import com.example.paymentstask.databinding.FragmentLoginBinding
import com.example.paymentstask.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLoginBtn()
    }

    private fun setUpLoginBtn() {
        binding.loginBtn.setOnClickListener {
            showLoadingState(true)
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    when (val tokenResponse = viewModel.login(
                        LoginInfo(
                            binding.login.text.toString(),
                            binding.password.text.toString()
                        )
                    )) {
                        is ResultResponse.Loading -> {

                        }

                        is ResultResponse.Success -> {
                            viewModel.saveToken(tokenResponse.data.response[LoginViewModel.TOKEN])
                            (requireActivity() as MainActivity).openPayments()
                        }

                        is ResultResponse.Error -> {
                            withContext(Dispatchers.Main) {
                                showLoadingState(false)
                                if (tokenResponse.message.isEmpty())
                                    Toast.makeText(
                                        requireContext(),
                                        "Something went wrong.\n" +
                                                "Check your login and password.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                else
                                    Toast.makeText(
                                        requireContext(),
                                        tokenResponse.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showLoadingState(isLoading: Boolean) {
        if (isLoading) {
            with(binding) {
                loginBtn.isEnabled = false
                loginBtn.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                loginBtn.isEnabled = true
                loginBtn.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

}