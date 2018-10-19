package com.mugiwara.savingcalculator.calculator.data

import android.arch.lifecycle.MutableLiveData

class PeriodRepository {

  var savingsClass: MutableLiveData<SavingsClass> = MutableLiveData()

  fun getNumberOfWeeksInAPeriod(weeks: Int) : List<Int>{
    var weeksList : MutableList<Int> = mutableListOf<Int>()

    for(i in 1..weeks){
      weeksList.add(i)
    }
    return weeksList
  }

  fun saveData(totals:List<Int>){
    var savings = SavingsClass(totals)
    savingsClass.value = savings
  }

  fun getData(): MutableLiveData<SavingsClass>{
    return savingsClass
  }
}