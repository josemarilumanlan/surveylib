package com.androidadvance.androidsurvey.views

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.androidadvance.androidsurvey.Answers
import com.androidadvance.androidsurvey.R
import com.androidadvance.androidsurvey.SurveyView
import com.androidadvance.androidsurvey.models.SurveyProperties

class EndView(val surveyView: SurveyView, val surveyProperties: SurveyProperties) : FrameLayout(surveyView.context) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.fragment_end, null)

        val button_finish = root.findViewById(R.id.button_finish) as Button
        val textView_end = root.findViewById(R.id.textView_end) as TextView

        textView_end!!.text = Html.fromHtml(surveyProperties.endMessage)
        button_finish.setOnClickListener { surveyView!!.event_survey_completed(Answers.getInstance()) }

        this.addView(root)
    }

}