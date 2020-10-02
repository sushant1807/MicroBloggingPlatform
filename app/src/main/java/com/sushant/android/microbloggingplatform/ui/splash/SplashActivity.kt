package com.sushant.android.microbloggingplatform.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.sushant.android.microbloggingplatform.R
import com.sushant.android.microbloggingplatform.ui.authorList.AuthorListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() { startAuthorListActiviy() }
        }.start()
    }

    fun startAuthorListActiviy() {
        val intent = Intent(this, AuthorListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
