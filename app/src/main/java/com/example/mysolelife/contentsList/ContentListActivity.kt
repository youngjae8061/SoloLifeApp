package com.example.mysolelife.contentsList

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysolelife.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val items = ArrayList<ContentModel>()
        val rvAdapter = ContentRVAdapter(baseContext, items)

        // Write a message to the database
        val database = Firebase.database
        // TipFragment에서 intent로 보낸 데이터 처리
        val category = intent.getStringExtra("category")

        if(category == "category1"){
            // realtimeDatabase에서 contents라는 이름의 경로를 가져옴
            myRef = database.getReference("contents")
        }else if(category == "category2"){
            // realtimeDatabase에서 contents2라는 이름의 경로를 가져옴
            myRef = database.getReference("contents2")
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(dataModel in dataSnapshot.children){
                    Log.d("ContentListActivity", dataSnapshot.toString())
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                }
                // 데이터를 동기화해줘라
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentListActivity", items.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)



        val rv : RecyclerView = findViewById(R.id.rv)


        // 여기서 만든 rv.adapter는 rvAdapter다
        // adapter에 있는 데이터를 rv에 연결
        rv.adapter = rvAdapter
        // 아이템 2개씩 한줄에 출력
        rv.layoutManager = GridLayoutManager(this, 2)
        // rv.layoutManager = LinearLayoutManager(this) // 아이템당 한줄 출력

        // 아이템 클릭시 웹뷰를 통해 해당 url을 띄움
        rvAdapter.itemClick = object :ContentRVAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                // Toast.makeText(baseContext, items[position].title, Toast.LENGTH_SHORT).show()

                val intent = Intent(this@ContentListActivity, ContentShowActivity::class.java)
                // url이라는 이름으로 해당 아이템의 url을 넘긴다.
                intent.putExtra("url", items[position].webUrl)
                startActivity(intent)
            }
        }



        // contents/ 위치에 hello world!라는 데이터 저장
        // push() - 임의의 데이터 생성(uid 같은..)
//        myRef2.push().setValue(
//            ContentModel("title5", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "https://philosopher-chan.tistory.com/1249?category=941578")
//        )
//        myRef2.push().setValue(
//            ContentModel("title6", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png", "https://philosopher-chan.tistory.com/1248?category=941578")
//        )
//        myRef2.push().setValue(
//            ContentModel("title7", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdIKDG%2Fbtq64M96JFa%2FKcJiYgKuwKuP3fIyviXm90%2Fimg.png", "https://philosopher-chan.tistory.com/1247?category=941578")
//        )
//        myRef2.push().setValue(
//            ContentModel("title8", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeEO4sy%2Fbtq69SgK8L3%2FttCUxYHx9aPNebNwkPcI21%2Fimg.png", "https://philosopher-chan.tistory.com/1246?category=941578")
//        )
//        items.add(ContentModel("title1", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FOtaMq%2Fbtq67OMpk4W%2FH1cd0mda3n2wNWgVL9Dqy0%2Fimg.png", "https://philosopher-chan.tistory.com/1249?category=941578"))
//        items.add(ContentModel("title2", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png", "https://philosopher-chan.tistory.com/1248?category=941578"))
//        items.add(ContentModel("title3", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdIKDG%2Fbtq64M96JFa%2FKcJiYgKuwKuP3fIyviXm90%2Fimg.png", "https://philosopher-chan.tistory.com/1247?category=941578"))
//        items.add(ContentModel("title4", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FeEO4sy%2Fbtq69SgK8L3%2FttCUxYHx9aPNebNwkPcI21%2Fimg.png", "https://philosopher-chan.tistory.com/1246?category=941578"))
//        items.add(ContentModel("title5", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FK917N%2Fbtq64SP5gxj%2FNzsfNAykamW7qv1hdusp1K%2Fimg.png", "https://philosopher-chan.tistory.com/1245?category=941578"))
//        items.add(ContentModel("title6", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FNNrYR%2Fbtq64wsW5VN%2FqIaAsfmFtcvh4Bketug9m0%2Fimg.png", "https://philosopher-chan.tistory.com/1244?category=941578"))
//        items.add(ContentModel("title7", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FlOnja%2Fbtq69Tmp7X4%2FoUvdIEteFbq4Z0ZtgCd4p0%2Fimg.png", "https://philosopher-chan.tistory.com/1243?category=941578"))
//        items.add(ContentModel("title8", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmBh5u%2Fbtq651yYxop%2FX3idRXeJ0VQEoT1d6Hln30%2Fimg.png", "https://philosopher-chan.tistory.com/1242?category=941578"))
//        items.add(ContentModel("title9", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fl2KC3%2Fbtq64lkUJIN%2FeSwUPyQOddzcj6OAkPKZuk%2Fimg.png", "https://philosopher-chan.tistory.com/1241?category=941578"))
//        items.add(ContentModel("title10", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F123LP%2Fbtq65qy4hAd%2F6dgpC13wgrdsnHigepoVT1%2Fimg.png", "https://philosopher-chan.tistory.com/1240?category=941578"))
//        items.add(ContentModel("title11", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fekn5wI%2Fbtq66UlN4bC%2F8NEzlyot7HT4PcjbdYAINk%2Fimg.png", "https://philosopher-chan.tistory.com/1239?category=941578"))
//        items.add(ContentModel("title12", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcOYyBM%2Fbtq67Or43WW%2F17lZ3tKajnNwGPSCLtfnE1%2Fimg.png", "https://philosopher-chan.tistory.com/1238?category=941578"))
//        items.add(ContentModel("title13", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbtig9C%2Fbtq65UGxyWI%2FPRBIGUKJ4rjMkI7KTGrxtK%2Fimg.png", "https://philosopher-chan.tistory.com/1237?category=941578"))
//        items.add(ContentModel("title14", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FznKK4%2Fbtq665AUWem%2FRUawPn5Wwb4cQ8BetEwN40%2Fimg.png", "https://philosopher-chan.tistory.com/1236?category=941578"))
//        items.add(ContentModel("title15", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FblYPPY%2Fbtq66v0S4wu%2FRmuhpkXUO4FOcrlOmVG4G1%2Fimg.png", "https://philosopher-chan.tistory.com/1235?category=941578"))
    } // onCreate()
}