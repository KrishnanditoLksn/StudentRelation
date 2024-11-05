package app.ditodev.studentrelation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ditodev.studentrelation.entity.Student
import kotlinx.coroutines.launch

class MainViewModel(private val studentRepository: StudentRepository) : ViewModel() {

    init {
        insertAllData()
    }

    fun getAllStudent(): LiveData<List<Student>> = studentRepository.getAllStudent()

    private fun insertAllData() = viewModelScope.launch {
        studentRepository.insertAllData()
    }

    fun getAllStudentAndUniversity() = studentRepository.getAllStudentAndUniversity()
    fun getAllUniversityAndStudent() = studentRepository.getAllUniversityAndStudent()
}