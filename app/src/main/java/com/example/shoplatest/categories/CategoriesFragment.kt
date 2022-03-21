package com.example.shoplatest.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoplatest.R
import com.example.shoplatest.data.AppDatabase
import com.example.shoplatest.data.CategoryRepository
import com.example.shoplatest.databinding.FragmentCategoriesBinding
import kotlin.math.absoluteValue

class CategoriesFragment : Fragment() {

    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_categories, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = AppDatabase.getInstance(application).categoryDatabaseDao

        val repository = CategoryRepository(dao)

        val factory = CategoriesViewModelFactory(repository, application)

        categoriesViewModel = ViewModelProvider(this, factory).get(CategoriesViewModel::class.java)

        binding.myCategoriesViewModel = categoriesViewModel

        binding.lifecycleOwner = this

        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.categoriesRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        //        binding.categoriesRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, 16, true))
        displayCategories()

    }

    private fun displayCategories() {
       categoriesViewModel.categories.observe(this, Observer {
           binding.categoriesRecyclerView.adapter = CategoriesAdapter(it)
       })
    }
}