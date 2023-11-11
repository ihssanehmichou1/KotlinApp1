package com.example.kotlin_app1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val myInput = findViewById<EditText>(R.id.input)
        val textView = findViewById<TextView>(R.id.text)
        val activity2 = findViewById<ConstraintLayout>(R.id.activity2)

        myInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }
            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textView.text = "Text : $p0"
            }
            override fun afterTextChanged(p0: Editable?) {
                //
            }
        })
        myInput.onFocusChangeListener = View.OnFocusChangeListener { _, p1 ->
            if(p1) {
                Toast.makeText(this@MainActivity2, "Edit Text As Focus",Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this@MainActivity2, "EditText Lost Focus", Toast.LENGTH_SHORT).show()
            }
        }
        myInput.setOnKeyListener(View.OnKeyListener{ _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                Toast.makeText(this@MainActivity2,"Key Enter",Toast.LENGTH_SHORT).show()
                return@OnKeyListener true
            }
            false
        })

        activity2.setOnTouchListener(object : MainActivity.OnSwipeTouchListener(this@MainActivity2) {
            override fun onSwipeRight(): Boolean {
                val i = Intent(this@MainActivity2,MainActivity::class.java )
                startActivity(i)
                return true
            }
            override fun onSwipeLeft(): Boolean {
                val i = Intent(this@MainActivity2,MainActivity3::class.java )
                startActivity(i)
                return true
            }
        })
    }
}