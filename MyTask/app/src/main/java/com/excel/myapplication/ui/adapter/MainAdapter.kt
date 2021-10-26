package com.excel.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.excel.myapplication.model.ResponseModel
import com.excel.myapplication.databinding.AdapterMovieBinding


open class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var movies = mutableListOf<ResponseModel>()

    fun updaeData(list: List<ResponseModel>) {
        if(movies.isEmpty()){
            movies.addAll(list)
            notifyDataSetChanged()
        }else{
            val utils = DiffUtil.calculateDiff(UpdateUtils(oldList = movies,newList = list))
            movies.clear()
            movies.addAll(list)
            utils.dispatchUpdatesTo(this@MainAdapter)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.name.text = movie.alert_message

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun removeAt(position: Int) {
        movies.removeAt(position)

        notifyItemRemoved(position)
    }

    class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root)

    class UpdateUtils(
        private val oldList: List<ResponseModel>,
        private val newList: List<ResponseModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].alert_id == newList[newItemPosition].alert_id
        }

    }

}

