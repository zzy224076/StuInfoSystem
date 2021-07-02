package com.example.stuinfosystem.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stuinfosystem.AppDataBase
import com.example.stuinfosystem.Dao.CouDao
import com.example.stuinfosystem.Dao.TeaDao
import com.example.stuinfosystem.databinding.FragmentNotificationsBinding
import com.example.stuinfosystem.entity.Course
import com.example.stuinfosystem.entity.Teacher
import com.example.stuinfosystem.entity.User

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val couDao: CouDao = AppDataBase.getDatabase(requireContext()).CouDao()
        val teaDao: TeaDao = AppDataBase.getDatabase(requireContext()).teaDao()


        var cclassSpinner: Spinner = binding.spinnerClass
        val addCourse: Button = binding.addCourse
        var classItem: String? = null

        binding.teaName.setOnFocusChangeListener { v, hasFocus ->
            if (binding.teaName.getText().toString() != "") {
                //Toast.makeText(requireContext(), idText.getText().toString(), Toast.LENGTH_SHORT).show()
                var teaName: String = binding.teaName.getText().toString()
                if (hasFocus) {

                } else {
                    var teacher:Teacher = teaDao.queryOneByName(teaName)
                    if (teacher == null) {
                        Toast.makeText(requireContext(), "该老师不存在！", Toast.LENGTH_SHORT).show()
                        binding.teaName.setText("")
                    }
                }
            }
        }
        addCourse.setOnClickListener {
            var courseNameText: String = binding.courseName.getText().toString()
            var teaNameText: String = binding.teaName.getText().toString()
            classItem = cclassSpinner.selectedItem.toString()


            if (courseNameText != "" && teaNameText != "") {


                //Toast.makeText(requireContext(), "完整", Toast.LENGTH_SHORT).show()
                val course:Course = couDao.queryByCourseAndClass(courseNameText, classItem!!)
                if (course!=null){
                    Toast.makeText(requireContext(), "此课程已存在！", Toast.LENGTH_SHORT).show()
                }else{
                    var course:Course = Course(0,courseNameText,teaNameText,classItem!!)
                    couDao.insert(course)
                    Toast.makeText(requireContext(), "添加成功！", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "请填写完整！", Toast.LENGTH_SHORT).show()
            }

        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}