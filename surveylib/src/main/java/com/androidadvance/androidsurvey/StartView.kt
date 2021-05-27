package com.androidadvance.androidsurvey


import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.androidadvance.androidsurvey.models.SurveyProperties

class StartView(val surveyView: SurveyView, val surveyProperties: SurveyProperties) : FrameLayout(surveyView.context) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root  = inflater.inflate(R.layout.fragment_start, null)
        val textView_start = root.findViewById(R.id.textView_start) as TextView
        val button_continue = root.findViewById(R.id.button_continue) as Button

        textView_start.text = Html.fromHtml(surveyProperties.introMessage)

        button_continue.setOnClickListener{ surveyView.go_to_next() }
        this.addView(root)
    }


}