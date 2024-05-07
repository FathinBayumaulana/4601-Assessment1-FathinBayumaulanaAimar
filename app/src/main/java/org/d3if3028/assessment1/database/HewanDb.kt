package org.d3if3028.assessment1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3028.assessment1.model.Hewan

@Database(entities = [Hewan::class], version = 1, exportSchema = false)
abstract class HewanDb : RoomDatabase() {
    abstract val dao: HewanDao
    companion object {
        @Volatile
        private var INSTANCE: HewanDb? = null
        fun getInstance(context: Context): HewanDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HewanDb::class.java,
                        "hewan.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}