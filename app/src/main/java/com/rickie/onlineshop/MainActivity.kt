package com.rickie.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var newslist: ArrayList<model>
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newslist = ArrayList()

        val client = AsyncHttpClient(true,80,443)
        client.get("https://eric.pythonanywhere.com/api/products",object : JsonHttpResponseHandler()
        {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                response: JSONArray
            ) {
                for(i in 0 until response.length()){
                    val json = response.getJSONObject(i)
                    val title = json.optString("title").toString()
                    val description = json.optString("description").toString()
                    val cost = json.optString("cost").toString()
                    val image_url = json.optString("image_url").toString()

                    newslist.add(model(title,description,cost,image_url))

                }
                recyclerAdapter = RecyclerAdapter(applicationContext, newslist)
                recyclerview.layoutManager = LinearLayoutManager(applicationContext)
                recyclerview.adapter = recyclerAdapter
                recyclerview.setHasFixedSize(true)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseString: String?,
                throwable: Throwable?
            ) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

        })
//pull refresh
    // load more

    }
}