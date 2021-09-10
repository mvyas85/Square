package com.challenge.square.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.challenge.square.view.ui.EmployeeDirectoryFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

         return when (className) {
            EmployeeDirectoryFragment::class.java.name -> {
                EmployeeDirectoryFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}