package com.mugiwara.savingcalculator.dependencyinjection

import android.content.Context
import com.mugiwara.savingcalculator.BaseApplicationClass
import com.mugiwara.savingcalculator.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SavingsCalculatorModule(private val application: BaseApplicationClass) {
  /**
   * Allow the application context to be injected but require that it be annotated with
   * [@Annotation][ForApplication] to explicitly differentiate it from an activity context.
   */

  @Provides @Singleton @ForApplication
  fun provideApplicationContext(): Context {
    return application
  }


}