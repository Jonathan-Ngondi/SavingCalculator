package com.mugiwara.savingcalculator.calculator.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mugiwara.savingcalculator.calculator.data.PeriodRepository
import com.mugiwara.savingcalculator.calculator.data.SavingsClass
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat

class CalculatorViewModel : ViewModel() {

  val total = MutableLiveData<Int>()

  companion object {
    fun kenyaShillingFormatter(): NumberFormat {
      val df = NumberFormat.getCurrencyInstance()
      val dfs = DecimalFormatSymbols()
      dfs.currencySymbol = "KES "
      dfs.groupingSeparator = '.'
      dfs.monetaryDecimalSeparator = '.'
      (df as DecimalFormat).decimalFormatSymbols = dfs
      return df
    }
  }


  fun getTheTotalSavingsForAPeriod(weeks:Int, startingAmount:Int) : List<Int>{
    val totals = mutableListOf<Int>()
    for(i in getTheNumberOfWeeksInAPeriod(weeks)){
      totals.add(calculateTotalForPeriod(startingAmount,startingAmount, i.toFloat()))
    }
    return totals
  }


  fun getTheNumberOfWeeksInAPeriod(weeks:Int) : List<Int> {
    val periodRepository = PeriodRepository()
    return periodRepository.getNumberOfWeeksInAPeriod(weeks)
  }

  fun calculateTotal(startingAmount: Int, difference: Int) : Int {
    val numberOfWeeks = 52
    var totalInt = 0
    if(difference<0){
      totalInt = 0
      total.value = totalInt

    } else {
      totalInt = numberOfWeeks / 2 * (2*(startingAmount)+(numberOfWeeks-1)*difference)
      total.value = totalInt

    }
    return totalInt

  }

  fun calculateTotalForPeriod(startingAmount: Int, difference: Int, period: Float): Int {
    return ((2*(startingAmount)+(period-1)*(difference))*(period/2)).toInt()
  }

  fun saveListOfSavings(startingAmount: Int, period: Int, periodRepository: PeriodRepository){
    val totals = getTheTotalSavingsForAPeriod(period,startingAmount)
    periodRepository.saveData(totals)
  }

  fun getSavedTotals(periodRepository: PeriodRepository):MutableLiveData<SavingsClass>{
    return periodRepository.getData()
  }






}