package com.devdroid.shoesapp.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devdroid.shoesapp.Adapter.CartAdapter
import com.devdroid.shoesapp.Helper.ChangeNumberItemsListener
import com.devdroid.shoesapp.Helper.ManagmentCart
import com.devdroid.shoesapp.R
import com.devdroid.shoesapp.databinding.ActivityCartBinding
import com.devdroid.shoesapp.databinding.ViewholderCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewCart.adapter = CartAdapter(managmentCart.getListCart(), this, object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }
        })

        with(binding) {
            emptyTxt.visibility=if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility=if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }
    }



    private fun calculateCart() {
        val percentTax: Double = 0.02
        val delivery: Double = 10.0
        tax = Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100

        with(binding) {
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish()}
    }
}