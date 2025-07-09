package com.devdroid.shoesapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.devdroid.shoesapp.Adapter.BrandAdapter
import com.devdroid.shoesapp.Adapter.PopularAdapter
import com.devdroid.shoesapp.Adapter.SliderAdapter
import com.devdroid.shoesapp.Model.SliderModel
import com.devdroid.shoesapp.ViewModel.MainViewModel
import com.devdroid.shoesapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity: BaseActivity() {

    private val viewModel= MainViewModel()
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initBrand()
        initPopular()
        initBottomMenu()

        binding.wishlist.setOnClickListener{
            val currentUser = FirebaseAuth.getInstance().currentUser

            if (currentUser != null) {
                startActivity(Intent(this, CartActivity::class.java))
            } else {
                Toast.makeText(this, "Please log in to view your orders.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AccountActivity::class.java))
            }
        }

        binding.cartBtn.setOnClickListener{
            val currentUser = FirebaseAuth.getInstance().currentUser

            if (currentUser != null) {
                startActivity(Intent(this, CartActivity::class.java))
            } else {
                Toast.makeText(this, "Please log in to view your cart.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AccountActivity::class.java))
            }
        }

        fun openProfile() {
            startActivity(Intent(this, ProfileAcivity::class.java))
        }

        binding.pflimg.setOnClickListener { openProfile() }
        binding.pfltxt.setOnClickListener { openProfile() }

    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, { items -> banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanner()
    }

    private fun banners(images:List<SliderModel>){
        binding.viewpagerSlider.adapter = SliderAdapter(images, binding.viewpagerSlider)
        binding.viewpagerSlider.clipToPadding=false
        binding.viewpagerSlider.clipChildren=false
        binding.viewpagerSlider.offscreenPageLimit=3
        binding.viewpagerSlider.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewpagerSlider.setPageTransformer(compositePageTransformer)
        if(images.size > 1) {
            binding.dotInticator.visibility= View.VISIBLE
            binding.dotInticator.attachTo(binding.viewpagerSlider)
        }
    }

    private fun initBrand() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer{
            binding.viewBrand.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
            binding.viewBrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrand()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer{
            binding.viewPopular.layoutManager= GridLayoutManager(this,2)
            binding.viewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPopular()
    }
}