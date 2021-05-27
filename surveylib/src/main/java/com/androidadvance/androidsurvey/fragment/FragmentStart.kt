package com.androidadvance.androidsurvey.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.androidadvance.androidsurvey.R
import com.androidadvance.androidsurvey.SurveyView
import com.androidadvance.androidsurvey.models.SurveyProperties

class FragmentStart : Fragment() {
    private var textView_start: TextView? = null
    private var surveyView: SurveyView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(
                R.layout.fragment_start, container, false) as ViewGroup
        textView_start = rootView.findViewById<View>(R.id.textView_start) as TextView
        val button_continue = rootView.findViewById<View>(R.id.button_continue) as Button
        button_continue.setOnClickListener { v: View? -> surveyView!!.go_to_next() }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val survery_properties = (requireArguments().getSerializable("survery_properties") as SurveyProperties?)!!
        textView_start!!.text = Html.fromHtml(survery_properties.introMessage)
    }

    companion object {
        fun newInstance(surveyView: SurveyView?): FragmentStart {
            val f = FragmentStart()
            f.surveyView = surveyView
            return f
        }
    }
}