package com.mugiwara.savingcalculator.calculator.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.mugiwara.savingcalculator.R
import com.mugiwara.savingcalculator.SavingCalculatorApp
import com.mugiwara.savingcalculator.calculator.data.PeriodRepository
import com.mugiwara.savingcalculator.calculator.viewmodel.CalculatorViewModel
import com.mugiwara.savingcalculator.utils.CustomAdapter
import com.mugiwara.savingcalculator.utils.ViewModelFactory
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import javax.inject.Inject

class CalculatorActivity : AppCompatActivity() {

  companion object {
    const val PERIOD_YEAR = 52


  }

  lateinit var viewModelFactory: ViewModelFactory
  lateinit var calculatorViewModel: CalculatorViewModel
  lateinit var editText: EditText
  lateinit var totalTextView: TextView
  private lateinit var recyclerView: RecyclerView
  var amount: Int = 0
  lateinit var periodRepository: PeriodRepository
  var emptyEditText = MutableLiveData<EditText>()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_calculator)
    (application as SavingCalculatorApp).component.inject(this)

    editText = findViewById(R.id.starting_amount_edit_text)
    totalTextView = findViewById(R.id.calc_yearly_total)
    recyclerView = this.findViewById(R.id.calc_recycler_view)

    viewModelFactory = ViewModelFactory()
    calculatorViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(CalculatorViewModel::class.java)


    periodRepository = PeriodRepository()



    editText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(p0: Editable?) {
        if (editText.text.isNotEmpty()) {

          emptyEditText.value = editText

          amount = editText.text.toString()
              .toInt()

          calculatorViewModel.saveListOfSavings(amount, PERIOD_YEAR, periodRepository)
          observeData()

        } else{

          calculatorViewModel.saveListOfSavings(amount, PERIOD_YEAR, periodRepository)
          calculatorViewModel.calculateTotal(amount,amount)
          observeData()
        }

      }

      override fun beforeTextChanged(
        p0: CharSequence?,
        p1: Int,
        p2: Int,
        p3: Int
      ) {

      }

      override fun onTextChanged(
        p0: CharSequence?,
        p1: Int,
        p2: Int,
        p3: Int
      ) {
      }
    })

  }

  fun observeData() {
    calculatorViewModel.getSavedTotals(periodRepository)
        .observe(this, Observer {

          updateView(it!!.listOfTotals)

        })

  }

  fun updateView(totals: List<Int>?) {
    val adapter = CustomAdapter(amount, totals, this)
    recyclerView.adapter = adapter
    adapter.notifyDataSetChanged()
    var totalText = totals?.get(51).toString()

    if(totalText.length>=4){
      totalText = CalculatorViewModel.kenyaShillingFormatter().format(totalText.toInt()).toString()
    }
    totalText.replace("£","KES",true)
    totalTextView.text = totalText


    recyclerView.layoutManager = LinearLayoutManager(this)


  }



}
