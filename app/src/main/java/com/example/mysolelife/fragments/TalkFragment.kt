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
import com.example.mysolelife.databinding.FragmentTalkBinding


class TalkFragment : Fragment() {

    private lateinit var binding : FragmentTalkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment 데이터 바인딩은 방식이 조금 다름!
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        //binding 클릭 이벤트
        // 팁 버튼을 눌렀을 경우 팁화면으로 이동
        binding.homeTap.setOnClickListener {
            Log.d("TalkFragment", "homeTap")
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }
        // 톡 버튼을 눌렀을 경우 팁화면으로 이동
        binding.tipTap.setOnClickListener {
            Log.d("TalkFragment", "tipTap")
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }
        // 북마크 버튼을 눌렀을 경우 팁화면으로 이동
        binding.bookmarkTap.setOnClickListener {
            Log.d("TalkFragment", "bookmarkTap")
            it.findNavController().navigate(R.id.action_talkFragment_to_bookmarkFragment)
        }
        // 스토어 버튼을 눌렀을 경우 팁화면으로 이동
        binding.storeTap.setOnClickListener {
            Log.d("TalkFragment", "storeTap")
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}