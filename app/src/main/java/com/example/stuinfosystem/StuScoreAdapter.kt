package com.example.stuinfosystem


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.Dao.ScoreDao
import com.example.stuinfosystem.entity.Score
import com.example.stuinfosystem.entity.Student


class StuScoreAdapter(private val stuList: MutableList<Student>, private val courseName: String,private val teaName:String) :
    RecyclerView.Adapter<StuScoreAdapter.ViewHolder>() {
    override fun getItemCount() = stuList.size
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.stu_id)
        val nameTextView: TextView = view.findViewById(R.id.stu_name)
        val classTextView: TextView = view.findViewById(R.id.stu_class)
        val scoreTextView: TextView = view.findViewById(R.id.stu_score)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = stuList[position]
        val scoreDao: ScoreDao = AppDataBase.getDatabase(holder.idTextView.context).ScoreDao()
        val studentId: Int = student.studentID
        var score: Score = scoreDao.queryOneByStuIdAndCourseName(studentId, courseName)
        holder.idTextView.text = student.studentID.toString()
        holder.nameTextView.text = student.stuName
        holder.classTextView.text = student.stuClass
        if (score != null) {
            var sscore: Int = score.sScore
            holder.scoreTextView.text = sscore.toString()
        } else {
            holder.scoreTextView.text = "未录入"
        }



        holder.itemView.setOnClickListener {
            val student = stuList[position]
            val intent = Intent(holder.itemView.context, PutInScoreActivity::class.java)
            intent.putExtra("studentID", student.studentID.toString())
            intent.putExtra("stu_name",student.stuName)
            intent.putExtra("stu_class",student.stuClass)
            intent.putExtra("courseName",courseName)
            intent.putExtra("teaName",teaName)
            holder.itemView.context.startActivity(intent)
        }


    }
}
