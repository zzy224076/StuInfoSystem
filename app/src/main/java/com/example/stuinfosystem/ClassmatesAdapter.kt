package com.example.stuinfosystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.Dao.StuDao
import com.example.stuinfosystem.entity.Student
import com.example.stuinfosystem.entity.User

class ClassmatesAdapter(private val stuList: MutableList<Student>) :
    RecyclerView.Adapter<ClassmatesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.class_stu_id)
        val nameTextView: TextView = view.findViewById(R.id.name_textView)
        val sexTextView: TextView = view.findViewById(R.id.sex_textView)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ClassmatesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.classmates_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassmatesAdapter.ViewHolder, position: Int) {
        var student:Student = stuList[position]
        holder.idTextView.text =  student.studentID.toString()
        holder.nameTextView.text = student.stuName
        holder.sexTextView.text = student.stuSex

    }

    override fun getItemCount(): Int=stuList.size
}