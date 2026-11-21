package com.example.studentapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.R
import com.example.studentapp.data.AppDatabase
import com.example.studentapp.repository.StudentRepository
import com.example.studentapp.viewmodel.StudentViewModel
import com.example.studentapp.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { StudentRepository(database.studentDao()) }
    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo ViewModel (kèm Factory) NGAY tại Activity. Các Fragment dùng
        // activityViewModels() không có Factory sẽ lấy lại đúng instance này,
        // nếu không sẽ crash vì StudentViewModel không có constructor rỗng.
        viewModel

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, StudentListFragment())
                .commit()
        }
    }
}
