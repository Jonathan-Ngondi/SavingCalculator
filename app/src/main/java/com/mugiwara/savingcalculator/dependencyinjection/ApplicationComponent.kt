package com.mugiwara.savingcalculator.dependencyinjection

import com.mugiwara.savingcalculator.BaseApplicationClass
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SavingsCalculatorModule::class])

interface ApplicationComponent {

  fun application(application: BaseApplicationClass)

}
