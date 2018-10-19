package com.mugiwara.savingcalculator.calculator.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mugiwara.savingcalculator.calculator.data.PeriodRepository
import com.mugiwara.savingcalculator.calculator.data.SavingsClass

class CalculatorViewModel : ViewModel() {

  val total = MutableLiveData<Int>()


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