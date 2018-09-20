package com.jeromemarshall.crypto2k18.activity

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.jeromemarshall.crypto2k18.adapter.ScheduleRecyclerAdapter
import com.jeromemarshall.crypto2k18.database.MyDatabase
import com.jeromemarshall.crypto2k18.modal.EventDivider
import com.jeromemarshall.crypto2k18.modal.EventHeader
import com.jeromemarshall.crypto2k18.modal.EventItem
import crypto2k18.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : AppCompatActivity() {
    companion object {
        val eventHeaderDay1: EventHeader = EventHeader("25th September 2018 (Day 1)",
                "Welcome to Crypto 2k18", "9am-4pm")
        val eventHeaderDay2: EventHeader = EventHeader("26th September 2018 (Day 2)",
                "Welcome to Crypto 2k18", "9am-4pm")


        val eventItemListDay1: ArrayList<EventItem> = arrayListOf(
                EventItem("Crypto Inauguration", "Aero Seminar Hall", "9:15am-10:45am"),
                EventItem("Paper Presentation", "Seminar Halls", "11:00am-1:00pm"),
                EventItem("Lunch Break", "Catering Hall", "1:00pm-2:30pm"),
                EventItem("Photography", "Aero Seminar Hall", "2:30pm-3:00pm"),
                EventItem("Ex-Quiz Me", "Room no. 120", "3:00pm-3:35pm"),
                EventItem("Code Error Hunting", "Computer Lab II", "3:40pm-4:00pm", isLastItem = true))
        val eventItemListDay2: ArrayList<EventItem> = arrayListOf(
                EventItem("Code Error Hunting", "Computer Lab II", "8:30am-9:15am"),
                EventItem("Video Clips", "Aero Seminar Hall", "8:30am-9:15am"),
                EventItem("Ex-Quiz Me(Round 2&3)", "Aero Seminar Hall", "9:15am-10:30pm"),
                EventItem("Poster Presentation", "Aero Seminar Hall", "10:45am-11:30am"),
                EventItem("Logo Design", "Computer Lab II", "10:45am-11:30am"),
                EventItem("PUBG", "Computer Lab II", "11:30am-12:30pm"),
                EventItem("Marketing", "Aero Seminar Hall", "11:30am-12:30pm"),
                EventItem("Lunch", "Catering Hall", "12:30pm-2:00pm"),
                EventItem("WordsWorth", "Aero Seminar Hall", "2:00pm-2:30pm"),
                EventItem("Valedictory", "Aero Seminar Hall", "2:30pm-4:00pm", isLastItem = true)

        )
        var roomDatabase: MyDatabase? = null
    }

    lateinit var schudleRecyclerAdapter: ScheduleRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        Toast.makeText(this@ScheduleActivity, "Carry your college Id card in the technical fest", Toast.LENGTH_LONG).show()
        roomDatabase = Room.databaseBuilder(this@ScheduleActivity,
                MyDatabase::class.java, "mydb").build()
        schudleRecyclerAdapter = ScheduleRecyclerAdapter(getRegisteredEventName())


        recycler_view_schedule.adapter = schudleRecyclerAdapter
        recycler_view_schedule.layoutManager = LinearLayoutManager(this)
        schudleRecyclerAdapter.addEventHeader(eventHeaderDay1)
        for (i in eventItemListDay1) {
            schudleRecyclerAdapter.addEventDivider(EventDivider(R.drawable.day1))
            schudleRecyclerAdapter.addEventItem(i)
        }

        schudleRecyclerAdapter.addEventHeader(eventHeaderDay2)

        for (i in eventItemListDay2) {
            schudleRecyclerAdapter.addEventDivider(EventDivider(R.drawable.day2))
            schudleRecyclerAdapter.addEventItem(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.schedule_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getRegisteredEventName(): MutableList<String> {
        val eventList: MutableList<String> = ArrayList()
        roomDatabase?.registrationDao()?.getAllReg()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { reg -> reg.map { x -> x.event }.forEach { eventList.add(it) } }
        return eventList
    }

    private fun alertShow() {
        val textView = TextView(this@ScheduleActivity)
        textView.setTextColor(ContextCompat.getColor(this@ScheduleActivity, android.R.color.black))
        textView.text = "Your Registrations"
        textView.textSize = 24f
        textView.setTextColor(ContextCompat.getColor(this@ScheduleActivity, R.color.goingEvent))
        textView.setPadding(32, 32, 8, 8)
        AlertDialog.Builder(this@ScheduleActivity)
                .setCustomTitle(textView)
                .setAdapter(ArrayAdapter(this@ScheduleActivity,
                        android.R.layout.select_dialog_item, getRegisteredEventName())) { _, _ ->
                }
                .show()
    }
}
