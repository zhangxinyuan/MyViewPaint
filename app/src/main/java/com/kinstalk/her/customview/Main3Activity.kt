package com.kinstalk.her.customview

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kinstalk.her.wyviewpaint.R

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }

    fun stopLoading(view: View) {
        val loadingView = view as LoadingView
        loadingView.success()
    }
}
