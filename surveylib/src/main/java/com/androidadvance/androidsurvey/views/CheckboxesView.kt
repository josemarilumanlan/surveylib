package com.androidadvance.androidsurvey.views

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.androidadvance.androidsurvey.Answers
import com.androidadvance.androidsurvey.R
import com.androidadvance.androidsurvey.SurveyView
import com.androidadvance.androidsurvey.models.Question
import com.androidadvance.androidsurvey.models.SurveyProperties
import java.util.*

class CheckboxesView(val surveyView: SurveyView, val q_data: Question) : FrameLayout(surveyView.context) {

    private var button_continue: Button? = null
    private var textview_q_title: TextView? = null
    private var linearLayout_checkboxes: LinearLayout? = null
    private val allCb = ArrayList<CheckBox>()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.fragment_checkboxes, null)
        button_continue = root.findViewById<View>(R.id.button_continue) as Button
        textview_q_title = root.findViewById<View>(R.id.textview_q_title) as TextView
        linearLayout_checkboxes = root.findViewById<View>(R.id.linearLayout_checkboxes) as LinearLayout
        button_continue!!.setOnClickListener { surveyView!!.go_to_next() }

        textview_q_title!!.text = if (q_data != null) q_data!!.questionTitle else ""
        if (q_data!!.required) {
            button_continue!!.visibility = View.GONE
        }
        val qq_data = q_data!!.choices
        if (q_data!!.randomChoices) {
            Collections.shuffle(qq_data)
        }
        for (choice in qq_data) {
            val cb = CheckBox(context)
            cb.text = Html.fromHtml(choice)
            cb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            cb.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            linearLayout_checkboxes!!.addView(cb)
            allCb.add(cb)
            cb.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean -> collect_data() }
        }
        this.addView(root)
    }



    private fun collect_data() {

        //----- collection & validation for is_required
        var the_choices = ""
        var at_leaset_one_checked = false
        for (cb in allCb) {
            if (cb.isChecked) {
                at_leaset_one_checked = true
                the_choices = the_choices + cb.text.toString() + ", "
            }
        }
        if (the_choices.length > 2) {
            the_choices = the_choices.substring(0, the_choices.length - 2)
            Answers.getInstance().put_answer(textview_q_title!!.text.toString(), the_choices)
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