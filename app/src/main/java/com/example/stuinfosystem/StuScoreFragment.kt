package com.example.stuinfosystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.Dao.ScoreDao
import com.example.stuinfosystem.databinding.FragmentStuScoreBinding
import com.example.stuinfosystem.databinding.FragmentTeaPutinBinding
import com.example.stuinfosystem.entity.Score
import com.example.stuinfosystem.entity.Student
import com.example.stuinfosystem.entity.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StuScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StuScoreFragment : Fragment() {
    private var _binding: FragmentStuScoreBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStuScoreBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var name: String? = activity?.intent?.getStringExtra("userName")
        val scoreDao:ScoreDao = AppDataBase.getDatabase(requireContext()).ScoreDao()
        //Toast.makeText(requireContext(),name, Toast.LENGTH_SHORT).show()
        var scoreRecyclerView:RecyclerView = binding.scoreRecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        scoreRecyclerView.layoutManager = layoutManager
        var scoreList: MutableList<Score> =scoreDao.queryByStuId(name.toString())
        val adapter = ScoreAdapter(scoreList)
        scoreRecyclerView.adapter = adapter




        return root
    }

}