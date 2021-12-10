package com.ikalimullin.core.db.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ikalimullin.core.db.impl.entity.Empty

@Database(entities = [Empty::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "APP_DATABASE_NAME"
    }
}
