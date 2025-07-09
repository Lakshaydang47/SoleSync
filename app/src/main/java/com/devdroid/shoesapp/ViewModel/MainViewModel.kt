package com.devdroid.shoesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdroid.shoesapp.Model.BrandModel
import com.devdroid.shoesapp.Model.ItemsModel
import com.devdroid.shoesapp.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel():ViewModel() {
    private val firebaseDatabase=FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand=MutableLiveData<MutableList<BrandModel>>()
    private val _popular=MutableLiveData<MutableList<ItemsModel>>()

    val brands: LiveData<MutableList<BrandModel>> = _brand
    val popular: LiveData<MutableList<ItemsModel>> = _popular
    val banners: LiveData<List<SliderModel>> = _banner

    fun loadBanner(){
        val bannerRef=firebaseDatabase.getReference("Banner")
        bannerRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for(childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                    _banner.value=lists
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadBrand() {
        val ref=firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                    _brand.value=lists
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadPopular() {
        val ref=firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                    _popular.value=lists
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}