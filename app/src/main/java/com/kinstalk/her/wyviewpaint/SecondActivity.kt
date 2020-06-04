package com.kinstalk.her.wyviewpaint

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kinstalk.her.myadpter.DensityAdapter

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityAdapter.setDensity(application, this)
        setContentView(R.layout.activity_second)
    }

    fun start1(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
