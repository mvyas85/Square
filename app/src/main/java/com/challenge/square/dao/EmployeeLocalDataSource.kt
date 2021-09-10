package com.challenge.square.dao

import com.challenge.square.model.Employee
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class EmployeeLocalDataSource @Inject constructor(
        private val employeeDao: EmployeeDao,
        private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun insertEmployee(employee: Employee) {
        TODO("Not yet implemented")
    }

    suspend fun insertAllEmployee(employeeList: List<Employee>) {
        employeeDao.insertAllEmployees(employeeList)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }

    fun observeAllEmployee(): List<Employee> {
        return employeeDao.getAllEmployee()
    }
}