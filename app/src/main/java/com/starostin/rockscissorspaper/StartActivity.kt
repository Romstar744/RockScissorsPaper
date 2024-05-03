package com.starostin.rockscissorspaper

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    fun startGame(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
