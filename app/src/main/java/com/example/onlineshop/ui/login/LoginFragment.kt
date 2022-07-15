package com.example.onlineshop.ui.login

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentLoginBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val locationPermissionCode = 2
    lateinit var binding: FragmentLoginBinding
    val viewModel:LoginFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
            .position(sydney)
            .title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        binding.btnLocation.setOnClickListener(){
            activity?.let { it1 ->
                ActivityCompat.requestPermissions(
                    it1,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
                    ,locationPermissionCode
                )
                binding.btnLocation.visibility=View.VISIBLE
            }
        }

        val switch=binding.switchThem
        switch.setOnCheckedChangeListener(){ _, isChecked->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }


            binding.btnRegister.setOnClickListener(){
                    viewModel.completeField(binding.etName)
                    viewModel.completeField(binding.etLastName)
                    viewModel.completeField(binding.etEmail)
                    viewModel.registerCustomer(
                        binding.etName.text.toString(),
                        binding.etLastName.text.toString(), binding.etEmail.text.toString()
                    )
                    viewModel.customerLiveData.observe(viewLifecycleOwner) {
                        viewModel.saveCustomerToSharP( it)


                        viewModel.statusLivedata.observe(viewLifecycleOwner) {
                            when (it) {
                                201 -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "اطلاعات شما با موفقیت ثبت شد", Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    Toast.makeText(
                                        requireContext(),
                                        "خطایی پیش آمده لطفا بعدا دوبراه سعی کنید",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
            }
        if (!viewModel.customerIsLogin()) {
            binding.etName.setText(viewModel.name)
            binding.etLastName.setText(viewModel.lastName)
            binding.etEmail.setText(viewModel.email)
        }
    }


}