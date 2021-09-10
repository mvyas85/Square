package com.challenge.square.view.adapter

import android.content.Context
import android.graphics.Color
import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.challenge.square.R
import com.challenge.square.databinding.EmployeeListItemBinding
import com.challenge.square.model.Employee
import javax.inject.Inject

class EmployeeAdapter @Inject constructor() : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    private var mEmployeeList = ArrayList<Employee>()
    lateinit var mBinding: EmployeeListItemBinding
    private lateinit var mContext: Context

    fun setAdapterData(employeeList: List<Employee>) {
        mEmployeeList = employeeList as ArrayList<Employee>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mBinding = EmployeeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return ViewHolder(mBinding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(mEmployeeList[position]) {
            showDataOrError(full_name, mBinding.empFullName)
            showDataOrError(PhoneNumberUtils.formatNumber(phone_number), mBinding.empPhone)
            showDataOrError(email_address, mBinding.empEmail)
            showDataOrError(biography, mBinding.empBiography)
            showDataOrError(team, mBinding.empTeam)
            showDataOrError(employee_type, mBinding.empType)
            setImageData(mBinding.empImage, photo_url_small, photo_url_large)
        }
    }

    private fun showDataOrError(data: String?, textView: TextView) {
        textView.text = if (data.isNullOrEmpty()) {
            textView.setTextColor(Color.RED)
            mContext.getString(R.string.emp_data_not_found)
        } else {
            textView.setTextColor(Color.BLACK)
            data
        }
    }

    private fun setImageData(imageViewProfile: ImageView, photoURLSmall: String?, photoURLLarge: String?) {
        Glide.with(imageViewProfile)
                .load(photoURLSmall)
                .centerCrop()
                .circleCrop()
                .placeholder(mBinding.empImage.drawable)
                .error(mBinding.empImage.drawable)
                .fallback(mBinding.empImage.drawable)
                .into(imageViewProfile)
//        imageViewProfile.setOnClickListener {
//            imageClickLiveData.postValue(photoURLLarge)
//        }
    }

    override fun getItemCount(): Int {
        return mEmployeeList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    //    private fun showDataEmptyError
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}