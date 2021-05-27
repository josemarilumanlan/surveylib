package com.androidadvance.androidsurvey.views

import android.content.Context
import android.text.Html
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.androidadvance.androidsurvey.Answers
import com.androidadvance.androidsurvey.R
import com.androidadvance.androidsurvey.SurveyView
import com.androidadvance.androidsurvey.models.Question
import java.util.*

class RadioBoxesView(val surveyView: SurveyView, val q_data: Question) : FrameLayout(surveyView.context) {

    private val allRb = ArrayList<RadioButton>()
    private var at_leaset_one_checked = false
    private var button_continue : Button? = null
    private var textview_q_title : TextView? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root  = inflater.inflate(R.layout.fragment_radioboxes, null)

         button_continue = root.findViewById(R.id.button_continue) as Button
        textview_q_title = root.findViewById(R.id.textview_q_title) as TextView
        val radioGroup = root.findViewById(R.id.radioGroup) as RadioGroup
        button_continue?.setOnClickListener { surveyView!!.go_to_next() }


        textview_q_title!!.text = q_data!!.questionTitle
        val qq_data = q_data!!.choices
        if (q_data!!.randomChoices) {
            Collections.shuffle(qq_data)
        }
        for (choice in qq_data) {
            val rb = RadioButton(context)
            rb.text = Html.fromHtml(choice)
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            rb.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            radioGroup!!.addView(rb)
            allRb.add(rb)
            rb.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean -> collect_data() }
        }
        if (q_data!!.required) {
            if (at_leaset_one_checked) {
                button_continue!!.visibility = View.VISIBLE
            } else {
                button_continue!!.visibility = View.GONE
            }
        }

        this.addView(root)
    }

    private fun collect_data() {
        //----- collection & validation for is_required
        var the_choice = ""
        at_leaset_one_checked = false
        for (rb in allRb) {
            if (rb.isChecked) {
                at_leaset_one_checked = true
                the_choice = rb.text.toString()
            }
        }
        if (the_choice.length > 0) {
            Answers.getInstance().put_answer(textview_q_title!!.text.toString(), the_choice)
        }
        if (q_data!!.required) {
            if (at_leaset_one_checked) {
                button_continue!!.visibility = View.VISIBLE
            } else {
                button_continue!!.visibility = View.GONE
            }
        }
    }

}