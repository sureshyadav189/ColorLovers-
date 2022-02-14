package com.example.colourlovers

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colourlovers.adapter.ItemOffsetDecoration
import com.example.colourlovers.adapter.MyAdapter
import com.example.colourlovers.adapter.OnLoverClicked
import com.example.colourlovers.model.ColourResponseItem
import com.example.colourlovers.viewmodel.MainViewModel
import com.example.colourlovers.viewmodel.MainViewModelFactorey

class MainActivity : AppCompatActivity(), OnLoverClicked {
    private lateinit var viewModel: MainViewModel
    private lateinit var factorey: MainViewModelFactorey
    var mRecyclerView: RecyclerView? = null
    var metSearch: EditText? = null
    lateinit var myAdapter: MyAdapter
    lateinit var progress: ProgressDialog
    var arrayList = ArrayList<ColourResponseItem>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = (application as MyApplication).repositorey
        factorey = MainViewModelFactorey(repo)
        viewModel = ViewModelProvider(this,factorey).get(MainViewModel::class.java)

        intView()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun intView() {
        mRecyclerView = findViewById(R.id.recyclerview)
        metSearch = findViewById(R.id.etSearch)
        val mGridLayoutManager = GridLayoutManager(this@MainActivity, 2)
        mRecyclerView?.setLayoutManager(mGridLayoutManager)
        mRecyclerView?.setHasFixedSize(true);
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        mRecyclerView?.addItemDecoration(ItemOffsetDecoration(spacingInPixels))
        metSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                filter(s.toString())
            }
        })

        viewModel.colours.observe(this, Observer {
            //Toast.makeText(this,"size:${it.size}",Toast.LENGTH_LONG).show()
            myAdapter = MyAdapter(this,it,this@MainActivity)
            mRecyclerView!!.adapter = myAdapter
            arrayList = it as ArrayList<ColourResponseItem>
        })
    }

    fun filter(text: String) {
        val temp: ArrayList<ColourResponseItem> = ArrayList()
        for (d in arrayList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.title.contains(text,ignoreCase = true)) {
                temp.add(d)
            }
        }
        //update recyclerview
        myAdapter?.updateList(temp)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onItemClicked(item: ColourResponseItem,isliked:Boolean) {
        Toast.makeText(this,item.title,Toast.LENGTH_LONG).show()
        viewModel.updateLike(isliked,item.id)

    }

}