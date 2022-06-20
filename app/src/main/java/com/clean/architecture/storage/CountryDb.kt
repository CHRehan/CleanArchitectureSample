package com.clean.architecture.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clean.architecture.features.countries.data.datasource.local.entities.CountryEntity
import com.clean.architecture.features.countries.domain.datasource.db.CountryDao

/**
 * Created by Rehan Sarwar on 07/06/2022.
 **/

@Database(
    entities = [CountryEntity::class],
    version = CountryDb.VERSION,
    exportSchema = false
)
abstract class CountryDb : RoomDatabase() {

    abstract fun CountryDao(): CountryDao

    companion object {
        internal const val VERSION = 1
        private const val NAME = "countries_db"

        fun create(applicationContext: Context): CountryDb {
            return Room.databaseBuilder(
                applicationContext,
                CountryDb::class.java,
                NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}
