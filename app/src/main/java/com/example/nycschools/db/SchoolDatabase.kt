package ccom.example.nycschools.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nycschools.db.ListTypeConverter
import com.example.nycschools.db.SchoolDao
import com.example.nycschools.models.SchoolList
import com.example.nycschools.models.SchoolListItem

/**
 * this is the database class which is an abstract class and it extends RoomDatabase class.
 * Here we create a singleton object of our database so that we have only one instacne of
 * our database throughout the project.
 */

@Database(entities = [SchoolList::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class SchoolDatabase : RoomDatabase() {

    abstract fun schoolDao(): SchoolDao

    companion object {
        @Volatile
        private var INSTANCE: SchoolDatabase? = null

        fun getDatabase(context: Context): SchoolDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                    SchoolDatabase::class.java,
                    "schoolDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}