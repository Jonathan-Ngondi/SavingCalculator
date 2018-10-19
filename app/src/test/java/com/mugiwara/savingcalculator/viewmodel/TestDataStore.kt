package com.mugiwara.savingcalculator.viewmodel

object TestDataStore {

  fun fiveWeeks() :List<Int>{

    return listOf(1,2,3,4,5)
  }

  fun tenWeeks() :List<Int>{
    return listOf(1,2,3,4,5,6,7,8,9,10)
  }

  fun tenWeeksofSavings(): List<Int>{
    return listOf(50,150,300,500,750,1050,1400,1800,2250,2750)
  }
}