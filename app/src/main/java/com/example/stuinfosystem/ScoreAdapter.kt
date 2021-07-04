package com.example.stuinfosystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.entity.Score

class ScoreAdapter(private val scoreList:MutableList<Score>):
    RecyclerView.Adapter<ScoreAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseName: TextView = view.findViewById(R.id.score_course_name)
        val score: TextView = view.findViewById(R.id.student_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.score_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val score:Score = scoreList[position]

        holder.courseName.text=score.courseName
        holder.score.text =score.sScore.toString()
    }

    override fun getItemCount() = scoreList.size

}