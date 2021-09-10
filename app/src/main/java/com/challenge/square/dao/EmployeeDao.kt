package com.challenge.square.dao

import androidx.room.*
import com.challenge.square.model.Employee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees")
    fun getAllEmployee(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllEmployees(employees: List<Employee>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

}
