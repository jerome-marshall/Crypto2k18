package com.jeromemarshall.crypto2k18.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import crypto2k18.R
import kotlinx.android.synthetic.main.activity_bubble_picker.*


class BubblePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(crypto2k18.R.layout.activity_bubble_picker)
        val titles = resources.getStringArray(crypto2k18.R.array.countries)
        val colors = resources.obtainTypedArray(crypto2k18.R.array.colors)
        val images = resources.obtainTypedArray(crypto2k18.R.array.images)
        val w = window
        val anim = AlphaAnimation(1.0f, 0.3f)

        anim.duration = 750 //You can manage the time of the blink with this parameter
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        blink_text.startAnimation(anim)
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        picker.adapter = object : BubblePickerAdapter {

            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {
                    title = titles[position]
//                    color = colors.getColor(position, ContextCompat.getColor(this@BubblePickerActivity, R.color.colorAccent))
                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                    typeface = ResourcesCompat.getFont(applicationContext, R.font.oxygen_regular)!!
                    textColor = ContextCompat.getColor(this@BubblePickerActivity, R.color.white)
                    backgroundImage = ContextCompat.getDrawable(this@BubblePickerActivity, images.getResourceId(position, 0))
                }
            }
        }



        colors.recycle()
        images.recycle()
        picker.bubbleSize = 20

        picker.centerImmediately = true
        picker.listener = object : BubblePickerListener {
            override fun onBubbleSelected(item: PickerItem) {
                when (item.title) {
                    "Event" -> respondToIntent(Intent(this@BubblePickerActivity, MainActivity::class.java))

                    "Location" -> respondToIntent(Intent(this@BubblePickerActivity, LocationActivity::class.java))

                    "Developer Info" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, DeveloperActivity::class.java))
                    }

                    "Rules and Details" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, RulesActivity::class.java))
                    }

                    "About Us" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, AboutActivity::class.java))

                    }

                    "The Pride CSE" -> {
                            respondToIntent(Intent(this@BubblePickerActivity, ThePrideActivity::class.java))
                    }

                    "Schedule" -> {
                        respondToIntent(Intent(this@BubblePickerActivity, ScheduleActivity::class.java))
                    }
                }
            }


            override fun onBubbleDeselected(item: PickerItem) = toast("${item.title} deselected")
        }
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()


    override fun onResume() {
        super.onResume()
        picker.onResume()
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    fun respondToIntent(intent: Intent) {
        Handler().postDelayed({
            startActivity(intent)
        }, 250)
    }

    override fun onBackPressed() {
        finish()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
