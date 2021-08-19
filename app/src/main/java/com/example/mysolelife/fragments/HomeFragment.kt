package com.example.mysolelife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mysolelife.R
import com.example.mysolelife.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("HomeFragment", "onCreateView")

        // Fragment 데이터 바인딩은 방식이 조금 다름!
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //binding 클릭 이벤트
        // 팁 버튼을 눌렀을 경우 팁화면으로 이동
        binding.tipTap.setOnClickListener {
            Log.d("HomeFragment", "tipTap")
            it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
        }
        // 톡 버튼을 눌렀을 경우 팁화면으로 이동
        binding.talkTap.setOnClickListener {
            Log.d("HomeFragment", "talkTap")
            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
        }
        // 북마크 버튼을 눌렀을 경우 팁화면으로 이동
        binding.bookmarkTap.setOnClickListener {
            Log.d("HomeFragment", "bookmarkTap")
            it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
        }
        // 스토어 버튼을 눌렀을 경우 팁화면으로 이동
        binding.storeTap.setOnClickListener {
            Log.d("HomeFragment", "storeTap")
            it.findNavController().navigate(R.id.action_homeFragment_to_storeFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}