package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener { calculateTip() }

    }
    private fun calculateTip(){
       val costofservice= binding.costOfService.text.toString().toDoubleOrNull()
        if (costofservice==null){
            Toast.makeText(this,"enter in numbers",Toast.LENGTH_LONG).show()
            binding.totaltip.text=""
            return
        }
        val tippercent= when(binding.tipoptions.checkedRadioButtonId){
            R.id.amazing-> 20
           R.id.good->10
           else->5
        }

        val tipamount=tippercent*costofservice/100
        val totalamount=tipamount+costofservice
        NumberFormat.getCurrencyInstance().format(tipamount)
        if (binding.rounduptip.isChecked){
            val roundupamount= ceil(totalamount)
            val showstring="tipamount: ${NumberFormat.getCurrencyInstance().format(tipamount)}  totalamount: $totalamount   roundupamount:$roundupamount"
            binding.totaltip.text=showstring
        }
        else {
            val showstring = "tipamount:  ${NumberFormat.getCurrencyInstance().format(tipamount)}   totalamount: $totalamount"
            binding.totaltip.text = showstring
        }
    }
}