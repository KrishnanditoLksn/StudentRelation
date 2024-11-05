package app.ditodev.studentrelation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentViewModelFactory private constructor(private val eventRepository: StudentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(eventRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: StudentViewModelFactory? = null
        fun getInstance(context: Context): StudentViewModelFactory =
            instance ?: synchronized(this) {
                instance ?:StudentViewModelFactory(DependencyInjection.provideStudentRepository(context))
            }.also { instance = it }
    }
}