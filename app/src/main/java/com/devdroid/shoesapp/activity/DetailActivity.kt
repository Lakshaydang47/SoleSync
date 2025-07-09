package com.devdroid.shoesapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdroid.shoesapp.Adapter.ColorAdapter
import com.devdroid.shoesapp.Adapter.SizeAdapter
import com.devdroid.shoesapp.Adapter.SliderAdapter
import com.devdroid.shoesapp.Model.ItemsModel
import com.devdroid.shoesapp.Model.SliderModel
import com.devdroid.shoesapp.R
import com.devdroid.shoesapp.databinding.ActivityDetailBinding
import com.devdroid.shoesapp.databinding.ActivityMainBinding
import com.devdroid.shoesapp.Helper.ManagmentCart
import com.google.firebase.auth.FirebaseAuth
import java.util.ResourceBundle.getBundle

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart=ManagmentCart(this)

        getBundle()
        banners()
        initLists()

    }

    private fun initLists(){
        val sizeList=ArrayList<String>()
        for(size in item.size){
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter=SizeAdapter(sizeList)
        binding.sizeList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList = ArrayList<String>()
        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }

        binding.colorList.adapter=ColorAdapter(colorList)
        binding.colorList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


    }

    private fun banners() {
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(SliderModel(imageUrl))
        }
        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding=true
        binding.slider.clipChildren=true
        binding.slider.offscreenPageLimit=1

        if(sliderItems.size > 1) {
            binding.dotInticator.visibility= View.VISIBLE
            binding.dotInticator.attachTo(binding.slider)
        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart=numberOrder
            managementCart.insertFood(item)
        }
        binding.backBtn.setOnClickListener{finish()}
        binding.cartBtn.setOnClickListener {
            val currentUser = FirebaseAuth.getInstance().currentUser

            if (currentUser != null) {
                startActivity(Intent(this, CartActivity::class.java))
            } else {
                Toast.makeText(this, "Please log in to view your cart.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AccountActivity::class.java))
            }
        }

    }
}