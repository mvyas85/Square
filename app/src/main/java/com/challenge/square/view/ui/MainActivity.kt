package com.challenge.square.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.challenge.square.R
import com.challenge.square.databinding.ActivityMainBinding
import com.challenge.square.util.Constants
import com.challenge.square.view.MainFragmentFactory
import com.challenge.square.viewmodel.EmployeeDirectoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    lateinit var mBinding: ActivityMainBinding

    private val mViewModel: EmployeeDirectoryViewModel by viewModels()

    @Inject
    lateinit var mFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.fragmentFactory = mFactory
        setContentView(mBinding.root)

        mBinding.employeeData.setOnClickListener(this)
        mBinding.employeeEmptyData.setOnClickListener(this)
        mBinding.employeeMalformedData.setOnClickListener(this)
    }

    private fun addEmployeeFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container,
                EmployeeDirectoryFragment(), null).commit()
    }

    private fun addEmptyEmployeeFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.main_fragment_container,
                EmptyDataFragment(), null).commit()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.employee_data -> {
                Log.d(TAG, "onClick() Employee Data")
                addEmployeeFragment()
                mViewModel.fetchEmployeeDirectoryData(Constants.EMP_DATA)
            }
            R.id.employee_malformed_data -> {
                Log.d(TAG, "onClick() Employee Malformed Data")
                addEmployeeFragment()
                mViewModel.fetchEmployeeDirectoryData(Constants.EMP_MALFORMED_DATA)
            }
            R.id.employee_empty_data -> {
                addEmptyEmployeeFragment()
                Log.d(TAG, "onClick() Employee Empty Data")
                mViewModel.fetchEmployeeDirectoryData(Constants.EMP_EMPTY_DATA)

            }
        }
    }

}