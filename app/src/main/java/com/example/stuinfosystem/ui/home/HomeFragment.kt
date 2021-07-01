package com.example.stuinfosystem.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stuinfosystem.AppDataBase
import com.example.stuinfosystem.Dao.UserDao
import com.example.stuinfosystem.R
import com.example.stuinfosystem.UserAdapter
import com.example.stuinfosystem.databinding.FragmentHomeBinding
import com.example.stuinfosystem.entity.User

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val userDao:UserDao =AppDataBase.getDatabase(requireContext()).userDao()
        val layoutManager = LinearLayoutManager(requireContext())
        var recyclerView: RecyclerView? = null
        recyclerView =binding.recyclerView1
        recyclerView.layoutManager = layoutManager
        var userList: MutableList<User> =userDao.selectAll()
        val adapter = UserAdapter(userList)
        recyclerView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}