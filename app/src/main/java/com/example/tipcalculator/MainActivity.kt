package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.widget.SeekBar
import android.text.Editable
import android.text.TextWatcher




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseInput: EditText = findViewById(R.id.baseInput)
        val percentageSeekBar: SeekBar = findViewById(R.id.percentseekBar)
        val percentageValueTextView: TextView = findViewById(R.id.percentageValueTextView)
        val showTipTextView: TextView = findViewById(R.id.showTip)
        val showTotalTextView: TextView = findViewById(R.id.showTotal)

        // Function to calculate and display the tip and total
        fun calculateAndDisplay() {
            val baseAmount = baseInput.text.toString().toDoubleOrNull() ?: 0.0
            val tipPercentage = percentageSeekBar.progress
            val tipAmount = baseAmount * tipPercentage / 100.0
            val totalAmount = baseAmount + tipAmount

            showTipTextView.text = String.format("%.2f", tipAmount)
            showTotalTextView.text = String.format("%.2f", totalAmount)
        }

        baseInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateAndDisplay()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

// Set initial value
        percentageValueTextView.text = "0"

// Set listener
        percentageSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                percentageValueTextView.text = "$progress"
                calculateAndDisplay()  //  update the calculations when the SeekBar changes
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }
}