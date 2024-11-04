package com.example.androidappdemo

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import java.util.Calendar

class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {
    interface DatePickerFragmentDelegate {
        fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int)
    }

    var delegate: DatePickerFragmentDelegate? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        delegate?.onDateSelected(year, month, dayOfMonth)
    }
}

class ChildFragment: Fragment(), DatePickerFragment.DatePickerFragmentDelegate {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = LinearLayout(this.requireContext())
        val btnDatePicker = Button(this.requireContext())
        btnDatePicker.text = "Date Picker"
        btnDatePicker.setOnClickListener {
            activity?.supportFragmentManager?.let {
                DatePickerFragment().apply {
                    delegate = this@ChildFragment
                }.show(it, null)
            }
        }
        val btnAlertDialog = Button(this.requireContext())
        btnAlertDialog.text = "Alert Dialog"
        btnAlertDialog.setOnClickListener {
            val builder = AlertDialog.Builder(this.context)
            builder.setMessage("Welcome to Android world!")
            builder.setTitle("Alert Dialog")
            builder.setPositiveButton("OK") {_, _ ->
                Toast.makeText(this.requireContext(), "Pressed OK", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(this.requireContext(), "Pressed Cancel", Toast.LENGTH_SHORT).show()
            }
            val dialog = builder.create()
            dialog.show()
        }
        layout.addView(btnDatePicker)
        layout.addView(btnAlertDialog)
        return layout
    }

    override fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        Toast.makeText(this.context, "Year: $year, Month: $monthOfYear, date: $dayOfMonth", Toast.LENGTH_SHORT).show()
    }
}

class ComponentsDemoActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = FragmentContainerView(this)
        layout.id = R.id.fragmentContainer
        setContentView(layout)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, ChildFragment()).commit()
    }
}