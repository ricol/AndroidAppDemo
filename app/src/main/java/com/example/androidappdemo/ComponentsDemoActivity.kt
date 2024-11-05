package com.example.androidappdemo

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
    lateinit var rootView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = LinearLayout(this.requireContext())
        layout.orientation = LinearLayout.VERTICAL
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
        val btnProgress = Button(requireContext())
        btnProgress.text = "Progress Dialog"
        btnProgress.setOnClickListener {
            val dlg = ProgressDialog(requireContext())
            dlg.setTitle("Please wait")
            dlg.setMessage("Processing...")
            dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            dlg.progress = 0
            dlg.max = 100
            dlg.show()
            val thread = Thread() {
                Log.d(TAG, "${Thread.currentThread()} begin...")
                try {
                    requireActivity().runOnUiThread {
                        dlg.progress = 10
                        Log.d(TAG, "${Thread.currentThread()} 1...")
                    }
                    Thread.sleep(1000)
                    requireActivity().runOnUiThread {
                        dlg.progress = 30
                        Log.d(TAG, "${Thread.currentThread()} 2...")
                    }
                    Thread.sleep(1000)
                    requireActivity().runOnUiThread {
                        dlg.progress = 70
                        Log.d(TAG, "${Thread.currentThread()} 3...")
                    }
                    Thread.sleep(1000)
                    requireActivity().runOnUiThread {
                        dlg.progress = 100
                        Log.d(TAG, "${Thread.currentThread()} 4...")
                    }
                    Thread.sleep(1000)
                }catch (e: Exception) {
                    Log.d(TAG, "exception: $e")
                }
                requireActivity().runOnUiThread {
                    dlg.progress = 100
                    dlg.dismiss()
                    Log.d(TAG, "${Thread.currentThread()} end.")
                    Thread() {
                        val btnTemp = Button(requireContext())
                        btnTemp.text = "Temp Button"
                        btnTemp.setOnClickListener {
                            Toast.makeText(requireContext(), "this is temp button", Toast.LENGTH_SHORT).show()
                        }
                        requireActivity().runOnUiThread {
                            Log.d(TAG, "${Thread.currentThread()} runOnUIThread...")
                            (rootView as LinearLayout).addView(btnTemp)
                        }
                    }.start()
                }
            }
            thread.start()
        }
        layout.addView(btnProgress)
        rootView = layout
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