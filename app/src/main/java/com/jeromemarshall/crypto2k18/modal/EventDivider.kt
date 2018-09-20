package com.jeromemarshall.crypto2k18.modal

import com.jeromemarshall.crypto2k18.adapter.ViewTypeSchedule

/**
 * Created by jeromemarshall on 15/3/18.
 */
data class EventDivider(val imageId: Int) : ViewTypeSchedule {
    override fun getViewType(): Int = ViewTypeSchedule.LINE
}