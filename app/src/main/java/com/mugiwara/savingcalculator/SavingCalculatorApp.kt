package com.mugiwara.savingcalculator

import com.mugiwara.savingcalculator.dependencyinjection.ApplicationComponent

class SavingCalculatorApp : BaseApplicationClass() {

  lateinit var component: ApplicationComponent


  override fun onCreate() {

    super.onCreate()
    val component = initDaggerComponent()
    this.component = component
  }


}