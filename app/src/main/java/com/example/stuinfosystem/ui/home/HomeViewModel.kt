package com.example.stuinfosystem.ui.home

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.stuinfosystem.AppDataBase
import com.example.stuinfosystem.entity.User

class HomeViewModel : ViewModel() {

    private var mApplication:Application?=null
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var users:MutableList<User>? = null

    fun loadUsers(){
        val userDao = mApplication?.let { AppDataBase.getDatabase(it.applicationContext).userDao() }
        if (userDao != null) {
            users= userDao.selectAll()
        }
    }
    fun AndroidViewModel(@NonNull application: Application){
        mApplication=application
    }
}