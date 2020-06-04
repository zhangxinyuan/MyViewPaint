package com.kinstalk.her.myadpter

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kinstalk.her.wyviewpaint.R

class AdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapter)
        val w = findViewById<TextView>(R.id.adapter_tv)
//
//        Log.e("zxy", "getPxWidth: " + ScreenAdapter.getDpWidth(1080 / 2))
//        Log.e("zxy", "getPxHeight: " + ScreenAdapter.getDpHeight(1920 / 2))
//
//        Log.e("zxy", "getPxWidth: " + ScreenAdapter.getPxWidth(this, 1080 / 2))
//        Log.e("zxy", "getPxHeight: " + ScreenAdapter.getPxHeight(this, 1920 / 2))
//        w.width = ScreenAdapter.getPxWidth(this, 1080 / 2)
//        w.height = ScreenAdapter.getPxHeight(this, 1920 / 2)
    }


}
