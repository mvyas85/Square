package com.challenge.square.repository

import android.content.Context
import android.util.Log
import com.challenge.square.dao.EmployeeDatabase
import com.challenge.square.model.Employee
import com.challenge.square.model.NetworkResponse
import com.challenge.square.service.api.EmployeeApi
import com.challenge.square.util.Constants.EMP_DATA
import com.challenge.square.util.Constants.EMP_MALFORMED_DATA
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Employee repository
 */
interface EmployeeDirectoryRepository {
    suspend fun fetchEmployeeData(type: String): NetworkResponse<List<Employee>>
}

class EmployeeDirectoryRepositoryImpl @Inject constructor(private val context: Context,
                                                          private val api: EmployeeApi,
                                                          private val database: EmployeeDatabase) : EmployeeDirectoryRepository {

    companion object {
        const val TAG = "EmployeeDirectoryRepo"
    }

    /**
     * Fetch employee's type data request
     * type : constant for type of data {EMP_DATA,EMP_MALFORMED_DATA or EMP_EMPTY_DATA}
     */
    override suspend fun fetchEmployeeData(type: String): NetworkResponse<List<Employee>> {
        return withContext(Dispatchers.IO) {
            fetchEmpData(type)
        }
    }

    /**
     * this method take in type argument and makes call to the type of
     * data request
     */
    private suspend fun fetchEmpData(type: String): NetworkResponse<List<Employee>> {
        Log.d(TAG, "fetchEmpData() for MVYAS $type")
        val preferences = context.getSharedPreferences("SquarePreferences", Context.MODE_PRIVATE)
        val lastUpdated = preferences.getLong("LastUpdated", -1)
        val dayInMillis = 0//3600 * 24 * 1000

        if (System.currentTimeMillis() - lastUpdated > dayInMillis) {
            val employeeDiffered = CoroutineScope(Dispatchers.IO).async {
                when (type) {
                    EMP_DATA -> {
                        api.fetchEmployeeData()
                    }
                    EMP_MALFORMED_DATA -> {
                        api.fetchMalformedEmployeeData()
                    }
                    else -> { //handles cases with EMP_EMPTY_DATA or unidentified string
                        api.fetchEmptyEmployeeData()
                    }
                }
            }

            val employeeResult = employeeDiffered.await()
            if (employeeResult.isSuccessful) {
                val directory = employeeResult.body()
                insertEmployees(directory?.employees!!)
            } else {
                return NetworkResponse(NetworkResponse.Status.ERROR, null, "Network error")
            }

            preferences.edit().putLong("LastUpdated", System.currentTimeMillis()).apply()
        }

        val employees = database.employeeDao().getAllEmployee()
        return NetworkResponse(NetworkResponse.Status.SUCCESS, employees, null)
    }

    private fun insertEmployees(employees: List<Employee>) {

        val list: ArrayList<Employee> = ArrayList()
        for (emp in employees) {
            if (emp.uuid != null) {
                list.add(emp)
            } else {
                Log.d(TAG, " MVYAS insertEmployees: Discarding Employee : ${emp.full_name} as he has uuid : ${emp.uuid} ")
            }

        }
        database.employeeDao().insertAllEmployees(list)
    }

}
