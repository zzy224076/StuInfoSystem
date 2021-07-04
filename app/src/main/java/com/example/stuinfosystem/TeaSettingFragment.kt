package com.example.stuinfosystem

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.stuinfosystem.databinding.FragmentStuScoreBinding
import com.example.stuinfosystem.databinding.FragmentTeaSettingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeaSettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeaSettingFragment : Fragment() {

    private var _binding: FragmentTeaSettingBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTeaSettingBinding.inflate(inflater,container,false)
        val root: View = binding.root
        var name: String? = activity?.intent?.getStringExtra("userName")
        binding.username.text = name
        binding.logout.setOnClickListener {
            val intent= activity?.intent?.setClass(requireContext(),MainActivity::class.java)
            startActivity(intent)

        }



        return root

    }

   
}