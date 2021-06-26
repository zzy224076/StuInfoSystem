package com.example.stuinfosystem


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList:MutableList<User>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

        override fun getItemCount()=userList.size
        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val idTextView:TextView = view.findViewById(R.id.id_textView)
            val nameTextView:TextView=view.findViewById(R.id.name_textView)
            val typeTextView:TextView = view.findViewById(R.id.type_textView)
            val deleteBtn:Button = view.findViewById(R.id.delete_btn)
            val editBtn:Button = view.findViewById(R.id.edit_btn)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item,parent,false)


        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userDao:UserDao=AppDataBase.getDatabase(holder.itemView.context).userDao()
        val user = userList[position]
        holder.idTextView.text = user.userID.toString()
        holder.nameTextView.text = user.name
        holder.typeTextView.text = user.type.toString()
        holder.deleteBtn.setOnClickListener{
            userDao.deleteOne(user)
            userList.remove(user)
            notifyItemRemoved(position)


//            // 页面跳转
//            var intent = Intent()
//            intent.setClass(holder.itemView.context,AdminActivity().javaClass)
//
//            startActivity(intent)
        }

    }




}

