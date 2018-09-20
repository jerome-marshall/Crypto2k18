package com.jeromemarshall.crypto2k18.modal

import com.jeromemarshall.crypto2k18.adapter.ViewTypeSchedule

/**
 * Created by jeromemarshall on 15/3/18.
 */
data class EventItem(val eventName: String,
                     val eventLocation: String,
                     val eventTime: String,
                     val isLastItem: Boolean = false) : ViewTypeSchedule {
    override fun getViewType(): Int = ViewTypeSchedule.ITEM
}