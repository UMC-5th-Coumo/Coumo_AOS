package com.umc.coumo.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.umc.coumo.R
import com.umc.coumo.databinding.FragmentHomeDetailInfoBinding
import com.umc.coumo.domain.viewmodel.HomeViewModel
import com.umc.coumo.presentation.adapter.MenuAdapter
import com.umc.coumo.utils.ItemSpacingDecoration
import com.umc.coumo.utils.binding.BindingFragmentNoneBackPress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeDetailInfoFragment: BindingFragmentNoneBackPress<FragmentHomeDetailInfoBinding>(R.layout.fragment_home_detail_info), OnMapReadyCallback {

    private val viewModel : HomeViewModel by activityViewModels ()
    private lateinit var map: GoogleMap

    private var location = LatLng(0.0,0.0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val menuAdapter = MenuAdapter(0)

        binding.rvMenu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(ItemSpacingDecoration(requireContext(),0))
        }

        menuAdapter.setOnItemClickListener(object : MenuAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                findNavController().navigate(R.id.action_homeDetailFragment_to_homeMenuFragment)
            }
        })

        viewModel.storeData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch (Dispatchers.Main) {
                menuAdapter.submitList(it.menuList)
                location = LatLng(it.latitude, it.longitude)
                setMap()
            }
        }
    }

    private fun setMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0

        map.addMarker(MarkerOptions().position(location))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15f))
    }
}