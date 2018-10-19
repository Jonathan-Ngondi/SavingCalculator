package com.mugiwara.savingcalculator

import android.app.Application
import com.mugiwara.savingcalculator.dependencyinjection.ApplicationComponent
import com.mugiwara.savingcalculator.dependencyinjection.DaggerApplicationComponent
import com.mugiwara.savingcalculator.dependencyinjection.SavingsCalculatorModule

abstract class BaseApplicationClass : Application() {

  protected  fun initDaggerComponent(): ApplicationComponent {

    return DaggerApplicationComponent.builder().savingsCalculatorModule(
        SavingsCalculatorModule(this)
    ).build()
  }

}