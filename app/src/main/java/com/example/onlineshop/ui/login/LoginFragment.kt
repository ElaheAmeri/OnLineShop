package com.example.onlineshop.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.onlineshop.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    val viewModel:LoginFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listOfCustomer.observe(viewLifecycleOwner){
            viewModel.registerCustomer(binding.etName.text.toString()
                ,binding.etLastName.text.toString(),binding.etEmail.text.toString())
        }

        binding.btnRegister.setOnClickListener(){
            Toast.makeText(requireContext(),"اطلاعات شما با موفقیت ثبت شد",Toast.LENGTH_SHORT).show()
        }
    }
}