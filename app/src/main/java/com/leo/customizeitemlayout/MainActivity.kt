package com.leo.customizeitemlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_sample.setOnClickListener {
            startActivity(Intent(this, SampleActivity::class.java))
        }
        tv_wms.setOnClickListener {
            startActivity(Intent(this, WMSActivity::class.java))
        }
    }
}
