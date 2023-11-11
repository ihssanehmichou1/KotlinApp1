package com.example.kotlin_app1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activity1 = findViewById<ConstraintLayout>(R.id.activity1)
        val click = findViewById<Button>(R.id.click)
        val longText = findViewById<TextView>(R.id.longText)

        click.setOnClickListener {
            Toast.makeText(this, "Button click ", Toast.LENGTH_SHORT).show()
            longText.text = "Simple Click On Button "
        }

        click.setOnLongClickListener {
            Toast.makeText(this@MainActivity, "Button Long click", Toast.LENGTH_SHORT).show()
            longText.text = "Long Click On Button"
            true
        }

        activity1.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            @SuppressLint("ClickableViewAccessibility")
            override fun onSwipeLeft(): Boolean {
                val i = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(i)
                return true
            }
        })
    }

    open class OnSwipeTouchListener(context: Context?) : View.OnTouchListener {
        companion object {
            private const val SwipeThreshold = 100
            private const val SwipeVelocityThreshold = 100
        }

        private val gestureDetector = GestureDetector(context, GestureListener())

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        open fun onSwipeRight(): Boolean {
            return false
        }

        open fun onSwipeLeft(): Boolean {
            return false
        }

        open fun onSwipeUp(): Boolean {
            return false
        }

        open fun onSwipeDown(): Boolean {
            return false
        }

        private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

              override fun onFling(
                  e1: MotionEvent,
                  e2: MotionEvent,
                  velocityX: Float,
                  velocityY: Float
              ): Boolean {
                try {
                    val diffY = e2.y - e1!!.y
                    val diffX = e2.x - e1.x

                    if (abs(diffX) > abs(diffY)) {
                        if (abs(diffX) > SwipeThreshold && abs(velocityX) > SwipeVelocityThreshold) {
                            return when {
                                diffX > 0 -> onSwipeRight()
                                else -> onSwipeLeft()
                            }
                        }
                    } else if (abs(diffY) > SwipeThreshold && abs(velocityY) > SwipeVelocityThreshold) {
                        return when {
                            diffY > 0 -> onSwipeDown()
                            else -> onSwipeUp()
                        }
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                return false
            }
        }
    }
}