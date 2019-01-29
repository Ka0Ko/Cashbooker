package com.armchairplan.cashbooker.cashbookRecorder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_display_message.*

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val intent: Intent = getIntent()
        messageTextView.text = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
    }
}
