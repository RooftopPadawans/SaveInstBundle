package com.flknlabs.saveinstbundle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var string: String? = ""
    var aBoolean = false
    var anInt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        // initialize array list
        val arrayList = listOf<String>(
            "Select Position",
            "1",
            "2",
            "3"
        )

        // set adapter
        spinner.adapter = ArrayAdapter<Any?>(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList
        )

        edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                string = s.toString()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        radio_group.setOnCheckedChangeListener { _, checkedId ->
            aBoolean = checkedId == R.id.rb_true
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // get int value
                anInt = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("string_value",string)
        outState.putBoolean("boolean_value",aBoolean)
        outState.putInt("int_value",anInt)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        string=savedInstanceState.getString("string_value")
        aBoolean=savedInstanceState.getBoolean("boolean_value")
        anInt=savedInstanceState.getInt("int_value")

        Toast.makeText(this, "$string - $aBoolean - $anInt", Toast.LENGTH_SHORT).show()

        super.onRestoreInstanceState(savedInstanceState)
    }
}