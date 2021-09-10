package com.challenge.square.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.challenge.square.databinding.FragmentEmptyDataBinding

class EmptyDataFragment : Fragment() {

    companion object {
        private const val TAG = "EmptyDataFragment"
    }

    lateinit var mBinding: FragmentEmptyDataBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = FragmentEmptyDataBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }
}