package com.mugiwara.savingcalculator.dependencyinjection

import com.mugiwara.savingcalculator.BaseApplicationClass
import com.mugiwara.savingcalculator.calculator.view.CalculatorActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SavingsCalculatorModule::class])

interface ApplicationComponent {

  fun application(application: BaseApplicationClass)

  fun inject(app: CalculatorActivity)

}
