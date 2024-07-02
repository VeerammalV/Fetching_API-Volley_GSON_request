package com.example.apivolleyjson

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val quoteList: ArrayList<Quote> = ArrayList()
    private lateinit var mAdapter: QuoteAdapter
    private lateinit var recyclerView: RecyclerView


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        mAdapter = QuoteAdapter(quoteList, this)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
//        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        recyclerView.addItemDecoration(itemDecoration)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val api_url = "https://jsonplaceholder.typicode.com/posts"


        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
//        val totalURL = "$API_URL?cat=$QUOTE_CATEGORY&count=$QUOTE_COUNT"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,api_url,null,
            { response ->
                Log.e("Response:", response.toString())

                val gson = Gson()
                val quotesArray = gson.fromJson(response.toString(), Array<Quote>::class.java)
                quoteList.addAll(quotesArray)
                mAdapter.notifyDataSetChanged()
            },
            { error ->
                error.printStackTrace()
            }
        )

        requestQueue.add(jsonArrayRequest)
    }

    }


//            val precount = quoteList.count()
//
//            val quoteJsonArray = JsonArray(response)
//            for (i in 0 until quoteJsonArray.length()) {
//                val singleObject = quoteJsonArray.getJSONObject(i)
//                val gsonparse = gson.fromJson(singleObject.toString(),
//                    Quote::class.java )
//                        quoteJsonArray.add(gsonparse)
//            }
//            if(quoteJsonArray.length()>0) {
//                mAdapter.notifyItemRangeChanged(precount, quoteList.count())


