package com.armchairplan.cashbooker.cashbookRecorder

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentActivity
import android.text.format.DateFormat
//import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : FragmentActivity(), DatePickerFragment.OnSelectedDateListener {
    companion object {
        const val EXTRA_MESSAGE = "com.armchairplan.cashbooker.MESSAGE"
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val mOnClickSendMessageListener = View.OnClickListener {
        val intent: Intent = Intent(this, DisplayMessageActivity::class.java)
        //val editText: EditText = findViewById(R.id.editText) as EditText

        val message: String = editText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }

    private val mOnClickDateListener = View.OnClickListener {
        val newFragment = DatePickerFragment.newInstance(editDate.text.toString())
        newFragment.show(supportFragmentManager, "datePicker")
    }

    /*
    private val mOnSelectedDate = object: DatePickerFragment.OnSelectedDateListener {
        override fun onSelectedDate(view: DatePicker?, date: String) {
            editDate.setText(date)
        }
    }
    */

    override fun onSelectedDate(view: DatePicker?, date: Date) {
        setDate(date)
    }

    private fun setDate(date: Date) {
        editDate.setText(DateFormat.format("yyyy/M/d", date).toString())
    }
    /*
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {

        val str = String.format(Locale.US, "%d/%d/%d", year, month+1, day)
        editDate.setText(str)

    }

    private val mOnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        val str = String.format(Locale.US, "%d/%d/%d", year, month+1, day)
        editDate.setText(str)
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        button.setOnClickListener(mOnClickSendMessageListener)
        editDate.setOnClickListener(mOnClickDateListener)

        //setDate(Date())
    }
}
