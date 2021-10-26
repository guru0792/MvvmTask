package com.excel.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.excel.myapplication.databinding.ActivityMainBinding

import com.excel.myapplication.room.Repository
import com.excel.myapplication.*
import com.excel.myapplication.model.ResponseModel
import com.excel.myapplication.repo.MainRepository
import com.excel.myapplication.ui.adapter.MainAdapter
import com.excel.myapplication.utils.MyViewModelFactory
import com.excel.myapplication.utils.RetrofitService
import com.excel.myapplication.utils.SwipeToDeleteCallback
import com.excel.myapplication.utils.checkInternetAvailable


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    val list = ArrayList<ResponseModel>()
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityMainBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(root)

        }
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(MainRepository(retrofitService), Repository(application))
        ).get(MainViewModel::class.java)
        binding.recyclerview.adapter = adapter
        fetchData()


        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
                Log.d("22", viewHolder.layoutPosition.toString())
                viewModel.deleteItem(list.get(viewHolder.layoutPosition).alert_id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
    }

    private fun fetchData() {
        if (checkInternetAvailable()) {
            viewModel.getAllMovies()
            viewModel.allNotes.observe(this@MainActivity, {
                list.clear()
                list.addAll(it)
                adapter.updaeData(it)
            })
        } else {
            viewModel.fetchNotesFromDB.observe(this@MainActivity, {
                list.clear()
                list.addAll(it)
                adapter.updaeData(it)
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cut -> {
                viewModel.getAllMovies()
                viewModel.allNotes.observe(this@MainActivity, {
                    list.clear()
                    list.addAll(it)
                    adapter.updaeData(it)
                })
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}