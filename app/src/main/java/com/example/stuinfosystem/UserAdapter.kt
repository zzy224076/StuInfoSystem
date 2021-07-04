package com.example.stuinfosystem


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.Dao.StuDao
import com.example.stuinfosystem.Dao.TeaDao
import com.example.stuinfosystem.Dao.UserDao
import com.example.stuinfosystem.entity.User

class UserAdapter(private val userList:MutableList<User>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>(){

        override fun getItemCount()=userList.size
        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val idTextView:TextView = view.findViewById(R.id.name_textView)
            val nameTextView:TextView=view.findViewById(R.id.sex_textView)
            val typeTextView:TextView = view.findViewById(R.id.tel_textView)
            val deleteBtn:Button = view.findViewById(R.id.delete_btn)


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userDao: UserDao =AppDataBase.getDatabase(holder.itemView.context).userDao()
        val stuDao:StuDao = AppDataBase.getDatabase(holder.itemView.context).stuDao()
        val teaDao:TeaDao = AppDataBase.getDatabase(holder.itemView.context).teaDao()

        val user = userList[position]
        holder.idTextView.text = user.userID.toString()
        holder.nameTextView.text = user.name
        holder.typeTextView.text = user.type.toString()
        holder.deleteBtn.setOnClickListener{
            userDao.deleteOne(user)
            if(user.type==1){
                stuDao.deleteOneById(user.userID)
            }else{
                teaDao.deleteOneById(user.userID)
            }

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


