package com.example.stuinfosystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AdminActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager:LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val userDao =AppDataBase.getDatabase(this).userDao()
        val layoutManager = LinearLayoutManager(this)
        var recyclerView:RecyclerView? = null
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = layoutManager
        val userList:List<User> =userDao.selectAll()

        val adapter = UserAdapter(userList)
        recyclerView.adapter = adapter
        adapter.setOnKotlinItemClickListener(object :UserAdapter.IKotlinItemClickListener{
            override fun onItemClickListener(position: Int) {
                adapter.getItemId(position)

            }

        }


        )

//        var textView:TextView?=null
//        textView=findViewById(R.id.textView)
//        val bundle = this.intent.extras
//        val userList:List<User> = userDao.selectAll()
        //给TextView 赋值
        //textView?.text = bundle?.get("userName").toString()

    }
}