package com.example.number_list

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.number_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var dataArray:ArrayList<Data> = ArrayList()
        dataArray.add(Data("zero","zeroaudio"))
        dataArray.add(Data("one","oneaudio"))
        dataArray.add(Data("two","twoaudio"))
        dataArray.add(Data("three","threeaudio"))
        dataArray.add(Data("four","fouraudio"))
        dataArray.add(Data("five","fiveaudio"))
        dataArray.add(Data("six","sixaudio"))
        dataArray.add(Data("seven","sevenaudio"))
        dataArray.add(Data("eight","eightaudio"))
        var adapter:numberAdapter = numberAdapter(this, dataArray)
        val numList:View=findViewById(R.id.num_list)
        binding.numList.adapter=adapter



    }
}

class numberAdapter : BaseAdapter {
    var arrayList:ArrayList<Data> = ArrayList()
    var context:Context?
    constructor(context: Context,arrayList: ArrayList<Data>){
        this.arrayList=arrayList
        this.context=context
    }
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View
        var inflater:LayoutInflater=context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var holder:viewHolder
        if (convertView==null){
            view=inflater.inflate(R.layout.list_item,parent,false)
            holder=viewHolder()
            holder.text=view.findViewById(R.id.text_view)
            holder.image=view.findViewById(R.id.image_view)
            view.tag=holder
        }else{
            view=convertView
            holder=convertView.tag as viewHolder
        }
        var textValue=holder.text
        textValue?.text=arrayList[position].text
        val imageValue=holder.image
        imageValue?.setOnClickListener{
            var mediaPlayer=MediaPlayer.create(context, context?.resources!!.getIdentifier(arrayList[position].audioFIle, "raw",context?.packageName))
            mediaPlayer.start()
        }

        return view
    }
    private class viewHolder{
        var text:TextView?=null
        var image:ImageView?=null
    }

}