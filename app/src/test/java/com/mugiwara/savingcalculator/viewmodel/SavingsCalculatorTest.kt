package com.mugiwara.savingcalculator.viewmodel

import com.mugiwara.savingcalculator.calculator.viewmodel.CalculatorViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SavingsCalculatorTest {

  private lateinit var calculatorViewModel : CalculatorViewModel

  @Before
  fun setUp(){
    calculatorViewModel = CalculatorViewModel()
  }

  @Test
  fun check_algorithm_for_positive_integers() {

    //Given
    val startingAmount: Int = 20
    val difference: Int = 20
    //Then
    assertEquals(27560, calculatorViewModel.calculateTotal(startingAmount,difference))
  }

  @Test
  fun check_algorithm_for_negative_starting_amount() {

    //Given
    val startingAmount: Int = -20
    val difference: Int = 20
    //Then
    assertEquals(25480, calculatorViewModel.calculateTotal(startingAmount,difference))
  }

  @Test
  fun check_algorithm_for_negative_difference() {

    //Given
    val startingAmount: Int = 20
    val difference: Int = -20
    //Then
    assertEquals(0, calculatorViewModel.calculateTotal(startingAmount,difference))
  }

  @Test
  fun check_algorithm_for_period_less_than_a_year(){
    //Given
    val startingAmount: Int = 20
    val difference: Int = 20
    val period = 24.toFloat()

    //Then
    assertEquals(6000, calculatorViewModel.calculateTotalForPeriod(startingAmount,difference, period))
  }

  @Test
  fun check_algorithm_for_period_that_is_odd(){
    //Given
    val startingAmount: Int = 20
    val difference: Int = 20
    val period = 5.toFloat()

    //Then
    assertEquals(300, calculatorViewModel.calculateTotalForPeriod(startingAmount,difference, period))
  }

  @Test
  fun get_number_of_weeks_in_a_period(){
    //Given
    val weeksInAPeriod = TestDataStore.tenWeeks()

    //Then
    assertEquals(weeksInAPeriod, calculatorViewModel.getTheNumberOfWeeksInAPeriod(10))

  }

  @Test
  fun calculation_for_arbitrary_period(){
    //Given
    val period = 10
    val savingsAmount = 50

    //When
    val tenWeeksofSavings = TestDataStore.tenWeeksofSavings()

    //Then
    assertEquals(tenWeeksofSavings, calculatorViewModel.getTheTotalSavingsForAPeriod(period,savingsAmount))
  }


}