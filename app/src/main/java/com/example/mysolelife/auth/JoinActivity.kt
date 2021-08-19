package com.example.mysolelife.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mysolelife.MainActivity
import com.example.mysolelife.R
import com.example.mysolelife.databinding.ActivityIntroBinding
import com.example.mysolelife.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_join)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        // Initialize Firebase Auth
        auth = Firebase.auth


        // 회원가입 버튼 눌렀을때 이벤트
        binding.join.setOnClickListener {

            // ture면 회원가입 가능
            var isGoToJoin = true

            val email = binding.emailArea.text.toString()
            val pwd = binding.passwordArea.text.toString()
            val pwd2 = binding.passwordArea2.text.toString()

            // 회원가입 버튼을 눌렀을때 각 칸이 비어있는지 확인
            if(email.isEmpty()){
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(pwd.isEmpty()){
                Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(pwd2.isEmpty()){
                Toast.makeText(this, "비밀번호 체크를 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            //비밀번호와 비밀번호 확인의 값이 다를 때
            if(!pwd.equals(pwd2)){
                Toast.makeText(this, "비밀번호를 제대로 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            // 비밀번호가 6자 이상인지
            if(pwd.length < 6){
                Toast.makeText(this, "비밀번호를 6자리 이상 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(isGoToJoin){
                auth.createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            // 뒤로가기 누르면 창꺼짐
                            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}