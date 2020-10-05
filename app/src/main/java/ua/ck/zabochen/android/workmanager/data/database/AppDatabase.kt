package ua.ck.zabochen.android.workmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.ck.zabochen.android.workmanager.data.database.AppDatabase.Companion.DATABASE_VERSION
import ua.ck.zabochen.android.workmanager.model.database.AudioFile

@Database(
    entities = [AudioFile::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun audioFileDao(): AudioFile.AudioFilesDao

    companion object {

        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "app_database.db"

        @Volatile
        private var appDatabaseInstance: AppDatabase? = null

        operator fun invoke(application: Context): AppDatabase {
            return appDatabaseInstance ?: synchronized<AppDatabase>(Any()) {
                return appDatabaseInstance ?: buildDatabase(application).also {
                    appDatabaseInstance = it
                }
            }
        }

        private fun buildDatabase(application: Context): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                // Without migration: clear database
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
