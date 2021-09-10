package com.challenge.square.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.square.databinding.FragmentEmpBinding
import com.challenge.square.view.adapter.EmployeeAdapter
import com.challenge.square.viewmodel.EmployeeDirectoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeDirectoryFragment : Fragment() {

    companion object {
        private const val TAG = "EmployeeDirectoryFragme"
    }

    private val mViewModel: EmployeeDirectoryViewModel by activityViewModels()
    lateinit var mBinding: FragmentEmpBinding

    @Inject
    lateinit var mAdapter: EmployeeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentEmpBinding.inflate(layoutInflater, container, false)
        mBinding.employeeRecyclerView.adapter = mAdapter

        val decorView = DividerItemDecoration(mBinding.employeeRecyclerView.context,
                LinearLayoutManager.VERTICAL)
        mBinding.employeeRecyclerView.addItemDecoration(decorView)
        mViewModel.mEmployeeList.observe(viewLifecycleOwner, Observer {
            mAdapter.setAdapterData(it)
            Log.d(TAG, "onCreateView() observed data $it")
        })
        return mBinding.root
    }


}