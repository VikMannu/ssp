package com.example.ssp.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class DatePicker {
    companion object {
        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun createDatePickerDialog(editText: EditText, activity: FragmentActivity) {
            editText.setOnClickListener {

                // on below line we are getting
                // the instance of our calendar.
                val c = Calendar.getInstance()

                // on below line we are getting
                // our day, month and year.
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                // on below line we are creating a
                // variable for date picker dialog.
                val datePickerDialog = DatePickerDialog(
                    // on below line we are passing context.
                    activity,
                    { _, year, monthOfYear, dayOfMonth ->
                        // on below line we are setting
                        // date to our edit text.
                        val formatter = SimpleDateFormat("yyyy-MM-dd")
                        val date = Date(year, monthOfYear, dayOfMonth)
                        val dat = formatter.format(date)
                        editText.setText(dat)
                    },
                    // on below line we are passing year, month
                    // and day for the selected date in our date picker.
                    year,
                    month,
                    day
                )
                // at last we are calling show
                // to display our date picker dialog.
                datePickerDialog.show()
            }
        }
    }
}