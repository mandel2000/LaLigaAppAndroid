package es.upsa.mimo.android.laligaapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import es.upsa.mimo.android.laligaapp.db.dao.TeamDao
import es.upsa.mimo.android.laligaapp.db.entities.TeamEntity

@Database(entities = [TeamEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun teamDao(): TeamDao
}