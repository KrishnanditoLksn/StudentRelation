package app.ditodev.studentrelation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.ditodev.studentrelation.adapter.StudentAndUniversityAdapter
import app.ditodev.studentrelation.adapter.StudentListAdapter
import app.ditodev.studentrelation.adapter.UniversityAndStudentAdapter
import app.ditodev.studentrelation.databinding.ActivityMainBinding
import app.ditodev.studentrelation.helper.SortType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        StudentViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvStudent.layoutManager = LinearLayoutManager(this)

        getStudent()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_single_table -> {
                getStudent()
                showSortingOptionMenu(true)
                return true
            }

            R.id.action_many_to_one -> {
                getStudentAndUniversity()
                showSortingOptionMenu(false)
                true
            }

            R.id.action_one_to_many -> {
                getUniversityAndStudent()
                showSortingOptionMenu(false)
                true
            }

            R.id.action_many_to_many -> {
                getStudentWithCourse()
                showSortingOptionMenu(false)
                true
            }

            R.id.action_sort -> {
                showSortingPopupMenu()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getStudent() {
        val adapter = StudentListAdapter()
        binding.rvStudent.adapter = adapter
        mainViewModel.getAllStudent().observe(this) {
            adapter.submitList(it)
            it.forEach(::println)
        }
    }

    private fun showSortingOptionMenu(isShow: Boolean) {
        val view = findViewById<View>(R.id.action_sort) ?: return
        view.visibility = if (isShow) View.VISIBLE else View.GONE
    }


    private fun showSortingPopupMenu() {
        val view = findViewById<View>(R.id.action_sort) ?: return
        PopupMenu(this, view).run {
            menuInflater.inflate(R.menu.sorting_menu, menu)
            setOnMenuItemClickListener {
                mainViewModel.changeSortType(
                    when (it.itemId) {
                        R.id.action_ascending -> SortType.ASCENDING
                        R.id.action_descending -> SortType.DESCENDING
                        else -> SortType.RANDOM
                    }
                )
                true
            }
            show()
        }
    }

    private fun getStudentAndUniversity() {
        val adapters = StudentAndUniversityAdapter()
        binding.rvStudent.adapter = adapters
        mainViewModel.getAllStudentAndUniversity().observe(this) {
            adapters.submitList(it)
        }

    }

    private fun getUniversityAndStudent() {
        val adapters = UniversityAndStudentAdapter()
        binding.rvStudent.adapter = adapters
        mainViewModel.getAllUniversityAndStudent().observe(this) {
            adapters.submitList(it)
        }
    }


    private fun getStudentWithCourse() {

    }

}