package com.jeromemarshall.crypto2k18.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jeromemarshall.crypto2k18.modal.RegistrationEntity

/**
 * Created by jeromemarshall on 4/3/18.
 */

@Database(entities = arrayOf(RegistrationEntity::class), version = 1 ,exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun registrationDao(): RegistrationDao

}