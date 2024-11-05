package app.ditodev.studentrelation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import app.ditodev.studentrelation.entity.Student
import app.ditodev.studentrelation.helper.SortType

class MainViewModel(private val studentRepository: StudentRepository) : ViewModel() {
    private val _sort = MutableLiveData<SortType>()

    init {
        _sort.value = SortType.ASCENDING
    }

    fun changeSortType(sortType: SortType) {
        _sort.value = sortType
    }


    fun getAllStudent(): LiveData<List<Student>> = _sort.switchMap {
        studentRepository.getAllStudent(it)
    }


    fun getAllStudentAndUniversity() = studentRepository.getAllStudentAndUniversity()
    fun getAllUniversityAndStudent() = studentRepository.getAllUniversityAndStudent()
}