package com.example.stuinfosystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.stuinfosystem.Dao.ScoreDao
import com.example.stuinfosystem.Dao.TeaDao
import com.example.stuinfosystem.entity.Score
import com.example.stuinfosystem.entity.Teacher

class PutInScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put_in_score)
        val scoreDao: ScoreDao = AppDataBase.getDatabase(this).ScoreDao()
        val teaDao: TeaDao = AppDataBase.getDatabase(this).teaDao()
        var stuIdTextView: TextView = findViewById(R.id.score_stu_id)
        var stuNameTextView: TextView = findViewById(R.id.score_stu_name)
        var stuClassTextView: TextView = findViewById(R.id.score_stu_class)
        var stuScoreTextView: TextView = findViewById(R.id.score)
        var putinButton: Button = findViewById(R.id.putin_button)

        var stuId: String = intent.getStringExtra("studentID").toString()
        var stuName: String = intent.getStringExtra("stu_name").toString()
        var stuClass: String = intent.getStringExtra("stu_class").toString()
        var courseName: String = intent.getStringExtra("courseName").toString()
        var teaName: String = intent.getStringExtra("teaName").toString()
        //Toast.makeText(this, teaName, Toast.LENGTH_SHORT).show()
        var stu_score: Score = scoreDao.queryOneByStuIdAndCourseName(stuId.toInt(), courseName)
        if (stu_score != null) {
            stuScoreTextView.text = stu_score.sScore.toString()
        }

        stuIdTextView.text = stuId
        stuNameTextView.text = stuName
        stuClassTextView.text = stuClass
        putinButton.setOnClickListener {

            var scoreText = stuScoreTextView.getText().toString()
            if (scoreText != "") {

                if (stu_score != null) {
                    scoreDao.updateScoreByStuIdAndCourseName(scoreText.toInt(),stuId.toInt(),courseName)
                    Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show()
                } else {
                    var teacher:Teacher = teaDao.queryOneByName(teaName)
                    var score:Score = Score(0,stuId.toInt(),stuName,teacher.teacherID,teacher.teaName.toString(),courseName,scoreText.toInt())
                    scoreDao.insert(score)
                    Toast.makeText(this, "录入成功！", Toast.LENGTH_SHORT).show()
                }
            } else if (scoreText == "") {
                Toast.makeText(this, "请输入成绩！", Toast.LENGTH_SHORT).show()
            }


        }
    }
}