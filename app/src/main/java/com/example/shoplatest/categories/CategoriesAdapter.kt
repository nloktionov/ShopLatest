package com.example.shoplatest.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.DBUtil
import com.example.shoplatest.R
import com.example.shoplatest.data.CategoryEntity
import com.example.shoplatest.data.CategoryRepository
import com.example.shoplatest.databinding.CategoryItemBinding

class CategoriesAdapter(private val categoriesList: List<CategoryEntity>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CategoryItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.category_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categoriesList[position])
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}

class MyViewHolder(private val binding:CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(category: CategoryEntity) {
        val map = mutableMapOf<String, Int>()
        map["fruit"] = R.drawable.apple
        map["milk"] = R.drawable.domik_v_derevne
        map["veget"] = R.drawable.tomatoes
        map["tvorog"] = R.drawable.tvorog
        map["meat"] = R.drawable.svinina
        binding.categoryItemTextView.text = category.categoryName
        binding.categoryItemImageView.setImageResource(map[category.categoryImage]!!)
    }
}
