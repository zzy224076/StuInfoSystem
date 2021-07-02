package com.example.stuinfosystem.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stuinfosystem.AppDataBase
import com.example.stuinfosystem.Dao.StuDao
import com.example.stuinfosystem.Dao.TeaDao
import com.example.stuinfosystem.Dao.UserDao
import com.example.stuinfosystem.R
import com.example.stuinfosystem.databinding.FragmentDashboardBinding
import com.example.stuinfosystem.entity.Student
import com.example.stuinfosystem.entity.Teacher
import com.example.stuinfosystem.entity.User

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var userDao: UserDao = AppDataBase.getDatabase(requireContext()).userDao()
        var stuDao: StuDao = AppDataBase.getDatabase(requireContext()).stuDao()
        var teaDao: TeaDao = AppDataBase.getDatabase(requireContext()).teaDao()
        //教师单选框
        val teaRadioButton: RadioButton = binding.teaRadioButton
        //学生单选框
        val stuRadioButton: RadioButton = binding.stuRadioButton
        //添加按钮
        val addUserButton: Button = binding.addUserButton
        //重置按钮
        val reset: Button = binding.resetButton

        val idTextView: TextView = binding.idText

        val classTextView: TextView = binding.classText
        //性别单选框
        val sexRadioGroup: RadioGroup = binding.sexRadioGroup

        //角色单选框
        var rolesRadioGroup: RadioGroup = binding.rolesRadionGroup


        //点击教师单选框时班级不可用
        teaRadioButton.setOnClickListener {
            classTextView.isEnabled = false
            classTextView.setText("不可用")
        }
        stuRadioButton.setOnClickListener {
            classTextView.isEnabled = true
            classTextView.setText("")
        }
        addUserButton.setOnClickListener {
            //获取选中单选框的id
            var sexCheckedRadioButtonId: Int = sexRadioGroup.checkedRadioButtonId
            var rolesCheckRadioButtonId: Int = rolesRadioGroup.checkedRadioButtonId

            //选中的单选框
            var sexButton = activity?.findViewById<RadioButton>(sexCheckedRadioButtonId)
            var rolesButton = activity?.findViewById<RadioButton>(rolesCheckRadioButtonId)
            var sexText: String = sexButton?.getText().toString()
            var rolesText: String = rolesButton?.getText().toString()
            var idText: String = binding.idText.getText().toString()
            var nameText: String = binding.nameText.getText().toString()
            var telText: String = binding.telText.getText().toString()
            var classText: String = binding.classText.getText().toString()
            //Toast.makeText(requireContext(),telText,Toast.LENGTH_SHORT).show()
            if (idText != "" && nameText != "" && sexCheckedRadioButtonId != -1 && telText != "" &&
                rolesCheckRadioButtonId != -1 && classText != ""
            ) {
                var type: Int? = null
                if (rolesText == "学生") {
                    type = 1
                    var user: User = User(idText.toInt(), "123456", nameText, type)
                    userDao.insert(user)
                    var student: Student =
                        Student(idText.toInt(), nameText, sexText, telText, classText)
                    stuDao.insert(student)
                    Toast.makeText(requireContext(), "添加成功！", Toast.LENGTH_SHORT).show()
                    binding.idText.setText("")
                } else {
                    type = 2
                    var user: User = User(idText.toInt(), "123456", nameText, type)
                    userDao.insert(user)
                    var teacher: Teacher = Teacher(idText.toInt(), nameText, sexText, telText)
                    teaDao.insert(teacher)
                    Toast.makeText(requireContext(), "添加成功！", Toast.LENGTH_SHORT).show()
                    binding.idText.setText("")
                }


                //Toast.makeText(requireContext(), "完整", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "请填写完整", Toast.LENGTH_SHORT).show()
            }


        }
        //id输入框鼠标移出事件
        idTextView.setOnFocusChangeListener { v, hasFocus ->

            if (idTextView.getText().toString() != "") {
                //Toast.makeText(requireContext(), idText.getText().toString(), Toast.LENGTH_SHORT).show()
                var userID: String = binding.idText.getText().toString()
                if (hasFocus) {

                } else {
                    var user: User = userDao.selectOneById(userID.toInt())
                    if (user != null) {
                        Toast.makeText(requireContext(), "id已被使用请重新输入！", Toast.LENGTH_SHORT).show()
                        idTextView.setText("")
                    }
                }
            }
        }
        reset.setOnClickListener {
            idTextView.setText("")
            binding.nameText.setText("")
            binding.sexRadioGroup.clearCheck()
            binding.telText.setText(null)
            binding.rolesRadionGroup.clearCheck()
            binding.classText.setText("")

        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}