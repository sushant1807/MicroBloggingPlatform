package com.sushant.android.microbloggingplatform.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.sushant.android.microbloggingplatform.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Muhammet ÇAĞATAY on 16,Şubat,2020
 */
class Utils {

    companion object {

        fun showLoadingDialog(context: Context): ProgressDialog {
            var progressDialog: ProgressDialog = ProgressDialog(context)
            if (!(context as Activity).isFinishing) {
                progressDialog.show()
            }
            if (progressDialog.window != null) {
                progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }


        @SuppressLint("SimpleDateFormat")
        fun generateDateDesc(date_time:String): String?
        {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            var convertedDate: Date? = null
            var formattedDate: String? = null
            try {
                convertedDate = sdf.parse(date_time)
                formattedDate = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(convertedDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return  formattedDate
        }

    }

}