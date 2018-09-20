package com.jeromemarshall.crypto2k18.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.jeromemarshall.crypto2k18.modal.RegistrationEntity
import io.reactivex.Flowable

/**
 * Created by jeromemarshall on 4/3/18.
 */
@Dao
interface RegistrationDao {
    @Query("SELECT event FROM reg")
    fun getAllReg(): Flowable<MutableList<EventOnly>>

    @Insert
    fun insert(registrationEntity: RegistrationEntity)
}
data class EventOnly(val event:String)