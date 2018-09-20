package com.jeromemarshall.crypto2k18.modal

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by jeromemarshall on 4/3/18.
 */
@Entity(tableName = "reg")
data class RegistrationEntity(
        @PrimaryKey(autoGenerate = true)
        val uid: Long,
        val event: String
)