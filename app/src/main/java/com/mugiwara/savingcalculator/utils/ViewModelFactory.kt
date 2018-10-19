package com.mugiwara.savingcalculator.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mugiwara.savingcalculator.calculator.data.PeriodRepository
import com.mugiwara.savingcalculator.calculator.viewmodel.CalculatorViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory : ViewModelProvider.Factory {

  @Inject
  constructor(

  )
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
          return CalculatorViewModel() as T
        }
    else {
      throw IllegalArgumentException("ViewModel Not Found")
    }
  }


}