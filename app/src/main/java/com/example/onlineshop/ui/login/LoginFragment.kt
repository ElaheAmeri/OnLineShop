package com.example.onlineshop.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    val viewModel:LoginFragmentViewModel by viewModels()
    var customerId:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switch=binding.switchThem
        switch.setOnCheckedChangeListener(){buttonView,isChecked->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }


            binding.btnRegister.setOnClickListener(){
                if (!viewModel.customerIsLogin(requireContext())) {
                    viewModel.completeField(binding.etName)
                    viewModel.completeField(binding.etLastName)
                    viewModel.completeField(binding.etEmail)
                    viewModel.registerCustomer(
                        binding.etName.text.toString(),
                        binding.etLastName.text.toString(), binding.etEmail.text.toString()
                    )
                    viewModel.customerLiveData.observe(viewLifecycleOwner){
                        viewModel.saveCustomerToSharP(requireContext(),it)
                    }

                }else{
                    binding.etName.setText(viewModel.name)
                    binding.etLastName.setText(viewModel.lastName)
                    binding.etEmail.setText(viewModel.email)
                }
                    viewModel.statusLivedata.observe(viewLifecycleOwner){
                        when(it){
                            201->{Toast.makeText(requireContext(),
                                "اطلاعات شما با موفقیت ثبت شد",Toast.LENGTH_SHORT).show()}

                            else-> {
                                Toast.makeText(
                                    requireContext(),
                                    "خطایی پیش آمده لطفا بعدا دوبراه سعی کنید", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

    }
}