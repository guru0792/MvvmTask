package com.excel.myapplication.ui
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.excel.myapplication.room.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import com.excel.myapplication.model.ResponseModel
import com.excel.myapplication.model.DataRes
import com.excel.myapplication.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel constructor(private val repository: MainRepository, private val repository2: Repository)  : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var allNotes  =  MutableLiveData<List<ResponseModel>>()
    val fetchNotesFromDB = repository2.getData()

    fun getAllMovies() {

        val response = repository.getAllMovies()
        response.enqueue(object : Callback<DataRes> {
            override fun onResponse(call: Call<DataRes>, response: Response<DataRes>) {
                allNotes.postValue(response.body()?.alerts as ArrayList<ResponseModel>?)
                viewModelScope.launch(Dispatchers.IO) {
                    repository2.inserData(response.body()?.alerts)
                }
            }

            override fun onFailure(call: Call<DataRes>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

    fun deleteItem(adapterPosition: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository2.deleteData(adapterPosition)
            Log.d("item", adapterPosition.toString())
        }
    }

}