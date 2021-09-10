package com.challenge.square.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.square.model.Employee
import com.challenge.square.model.NetworkResponse
import com.challenge.square.repository.EmployeeDirectoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeDirectoryViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mRepository: EmployeeDirectoryRepository
    var mEmployeeList = MutableLiveData<List<Employee>>()
    var errorString = MutableLiveData<String>()

    fun fetchEmployeeDirectoryData(type: String) {

        viewModelScope.launch {
            mRepository.fetchEmployeeData(type).let { result ->
                if (result.status == NetworkResponse.Status.SUCCESS) {
                    mEmployeeList.postValue(result.data!!)
                } else {
                    errorString.postValue(result.message!!)
                }
            }
        }
    }
}
