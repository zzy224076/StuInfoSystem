package com.example.stuinfosystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.Dao.StuDao
import com.example.stuinfosystem.databinding.FragmentClassmatesBinding
import com.example.stuinfosystem.databinding.FragmentStuScoreBinding
import com.example.stuinfosystem.entity.Score
import com.example.stuinfosystem.entity.Student

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ClassmatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ClassmatesFragment : Fragment() {
    private var _binding: FragmentClassmatesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentClassmatesBinding.inflate(inflater, container, false)
        var name: String? = activity?.intent?.getStringExtra("userName")
        val root: View = binding.root
        val stuDao: StuDao = AppDataBase.getDatabase(requireContext()).stuDao()
        val student: Student = stuDao.queryOneByStuName(name.toString())
        val stuList:MutableList<Student> = stuDao.queryClassmates(student.stuClass.toString(), name.toString())
        val matesRecyclerView:RecyclerView = binding.matesRecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        matesRecyclerView.layoutManager = layoutManager
        val adapter = ClassmatesAdapter(stuList)
        matesRecyclerView.adapter = adapter

        return root
    }

}