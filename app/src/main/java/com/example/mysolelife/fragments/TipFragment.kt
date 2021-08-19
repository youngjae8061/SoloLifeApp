package com.example.mysolelife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mysolelife.R
import com.example.mysolelife.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private lateinit var binding : FragmentTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Fragment 데이터 바인딩은 방식이 조금 다름!
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip, container, false)

        //binding 클릭 이벤트
        // 팁 버튼을 눌렀을 경우 팁화면으로 이동
        binding.homeTap.setOnClickListener {
            Log.d("TipFragment", "homeTap")
            it.findNavController().navigate(R.id.action_tipFragment_to_homeFragment)
        }
        // 톡 버튼을 눌렀을 경우 팁화면으로 이동
        binding.talkTap.setOnClickListener {
            Log.d("TipFragment", "talkTap")
            it.findNavController().navigate(R.id.action_tipFragment_to_talkFragment)
        }
        // 북마크 버튼을 눌렀을 경우 팁화면으로 이동
        binding.bookmarkTap.setOnClickListener {
            Log.d("TipFragment", "bookmarkTap")
            it.findNavController().navigate(R.id.action_tipFragment_to_bookmarkFragment)
        }
        // 스토어 버튼을 눌렀을 경우 팁화면으로 이동
        binding.storeTap.setOnClickListener {
            Log.d("TipFragment", "storeTap")
            it.findNavController().navigate(R.id.action_tipFragment_to_storeFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}