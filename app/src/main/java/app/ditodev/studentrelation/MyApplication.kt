package app.ditodev.studentrelation

import android.app.Application
import app.ditodev.studentrelation.db.StudentDatabase

class MyApplication : Application() {

    companion object {
        private var instance: MyApplication? = null

        fun getInstance(): MyApplication {
            if (instance == null) {
                instance = MyApplication()
            }
            return instance!!
        }
    }
    private val database by lazy { StudentDatabase.getDatabase(this) }
    val repository by lazy { StudentRepository(database.studentDao())}

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}