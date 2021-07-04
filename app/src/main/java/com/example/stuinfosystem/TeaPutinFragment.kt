package com.example.stuinfosystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.Dao.CouDao
import com.example.stuinfosystem.Dao.StuDao
import com.example.stuinfosystem.Dao.UserDao
import com.example.stuinfosystem.databinding.FragmentTeaPutinBinding
import com.example.stuinfosystem.entity.Course
import com.example.stuinfosystem.entity.Student
import com.example.stuinfosystem.entity.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeaPutinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeaPutinFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentTeaPutinBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeaPutinBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val couDao: CouDao = AppDataBase.getDatabase(requireContext()).CouDao()
        var courseSpinner: Spinner = binding.courseSpinner
        val classSpinner:Spinner = binding.classSpinner
        val queryButton:Button = binding.queryButton
        //此处
        var courseSet: MutableSet<String> = mutableSetOf()

        //获取登录页面传过来的姓名
        var name: String? = activity?.intent?.getStringExtra("userName")
        if (name != null) {
            //Toast.makeText(requireContext(), name.toString(), Toast.LENGTH_SHORT).show()
            //查询到涉及到的选课记录
            val courseList: List<Course> = couDao.queryCourseNameByTeaName(name)
            //此处使用set过滤掉重复的数据
            for (value in courseList) {
                //Toast.makeText(requireContext(), value.courseName, Toast.LENGTH_SHORT).show()
                courseSet.add(value.courseName)
            }

            //Toast.makeText(requireContext(), courseSet.toString(), Toast.LENGTH_SHORT).show()
        }
        var courseNameList: MutableList<String> = mutableListOf()

        //将过滤过的课程名给list
        for (value in courseSet) {
            if (courseNameList != null) {
                courseNameList.add(value)
            }
            //Toast.makeText(requireContext(), courseNameList.toString(), Toast.LENGTH_SHORT).show()
        }

        //往spinner中放值
        //course

        val courseAdapter =
            ArrayAdapter(requireContext(), R.layout.color_spinner_layout, courseNameList)
        courseAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        courseSpinner.adapter = courseAdapter
        courseSpinner.setOnItemSelectedListener(this)

        //查询按钮点击事件绑定
        queryButton.setOnClickListener {
            var className:String = classSpinner.selectedItem.toString()
            //配置recycleview
            val stuDao: StuDao = AppDataBase.getDatabase(requireContext()).stuDao()
            val layoutManager = LinearLayoutManager(requireContext())
            var recyclerView: RecyclerView? = null
            val courseSpinner:Spinner = binding.courseSpinner
            val courseName :String= courseSpinner.selectedItem.toString()
            var name: String? = activity?.intent?.getStringExtra("userName")
            val scoreTextView: TextView? = activity?.findViewById<TextView>(R.id.stu_score)
            val  score:String = scoreTextView?.getText().toString()
            recyclerView = binding.recyclerView1
            recyclerView.layoutManager = layoutManager
            var stuList: MutableList<Student> = stuDao.queryStuByClassname(className)
            val adapter = StuScoreAdapter(stuList,courseName, name.toString())
            recyclerView.adapter = adapter

        }
        return root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //Log.e("TAG", "position: " + p2 + ",content: " + p0!!.getItemAtPosition(p2).toString())
        var classSpinner: Spinner = binding.classSpinner
        val couDao: CouDao = AppDataBase.getDatabase(requireContext()).CouDao()
        val courseList: List<Course> =
            couDao.queryClassNameByCourseName(p0!!.getItemAtPosition(p2).toString())
        //Toast.makeText(requireContext(), courseList.toString(), Toast.LENGTH_SHORT).show()
        var classSet: MutableSet<String> = mutableSetOf()
        var classNameList: MutableList<String> = mutableListOf()
        for (value in courseList) {
            classSet.add(value.cClass)
            //Toast.makeText(requireContext(), classSet.toString(), Toast.LENGTH_SHORT).show()

            //Toast.makeText(requireContext(), courseNameList.toString(), Toast.LENGTH_SHORT).show()
        }
        for (value in classSet) {
            classNameList.add(value)
        }

        val classAdapter =
            ArrayAdapter(requireContext(), R.layout.color_spinner_layout, classNameList)
        classAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        classSpinner.adapter = classAdapter



    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}



