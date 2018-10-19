package com.mugiwara.savingcalculator.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mugiwara.savingcalculator.R
import com.mugiwara.savingcalculator.calculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.layout_deposits_and_totals.view.deposit_card_content
import kotlinx.android.synthetic.main.layout_deposits_and_totals.view.deposit_card_title
import kotlinx.android.synthetic.main.layout_deposits_and_totals.view.period_week_text
import kotlinx.android.synthetic.main.layout_deposits_and_totals.view.totals_card_content
import kotlinx.android.synthetic.main.layout_deposits_and_totals.view.totals_card_title

class CustomAdapter(val deposit: Int, val totals: List<Int>?, val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val rootView = LayoutInflater.from(context).inflate(R.layout.layout_deposits_and_totals, parent, false)
        return ViewHolder(rootView)
  }

  class ViewHolder(
    view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    override fun onClick(p0: View?) {

    }
    val cardTitle = view.period_week_text
    val depositTitle = view.deposit_card_title
    val depositContent = view.deposit_card_content
    val totalContent = view.totals_card_content
    val totalTitle = view.totals_card_title



  }


  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {

    holder.depositTitle.text = "Deposit"
    val depositCalc = CalculatorViewModel.kenyaShillingFormatter().format(deposit*(position+1))
    holder.depositContent.text = depositCalc.toString()
    holder.totalTitle.text = "Totals"
    val totalFormatted = CalculatorViewModel.kenyaShillingFormatter().format(totals?.get(position))
    holder.totalContent.text = totalFormatted
        .toString()
    val week = "Week "+ (position+1).toString()
    holder.cardTitle.text = week

  }



  override fun getItemCount(): Int {
    return totals!!.size
  }
}

