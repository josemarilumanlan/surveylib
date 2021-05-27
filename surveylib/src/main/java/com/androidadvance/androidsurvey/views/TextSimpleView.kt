package com.androidadvance.androidsurvey.views

import android.app.Service
import android.content.Context
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.androidadvance.androidsurvey.Answers
import com.androidadvance.androidsurvey.R
import com.androidadvance.androidsurvey.SurveyView
import com.androidadvance.androidsurvey.models.Question

class TextSimpleView(val surveyView: SurveyView, val q_data: Question) : FrameLayout(surveyView.context) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root  = inflater.inflate(R.layout.fragment_text_simple, null)

        val button_continue = root.findViewById<View>(R.id.button_continue) as Button
        val textview_q_title = root.findViewById<View>(R.id.textview_q_title) as TextView
        val editText_answer = root.findViewById<View>(R.id.editText_answer) as EditText
        button_continue!!.setOnClickListener {
            Answers.getInstance().put_answer(textview_q_title!!.text.toString(), editText_answer!!.text.toString().trim { it <= ' ' })
            surveyView!!.go_to_next()
        }

        if (q_data!!.required) {
            button_continue!!.visibility = View.GONE
            editText_answer!!.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (s.length > 3) {
                        button_continue!!.visibility = View.VISIBLE
                    } else {
                        button_continue!!.visibility = View.GONE
                    }
                }
            })
        }
        textview_q_title!!.text = Html.fromHtml(q_data.questionTitle)
        editText_answer!!.requestFocus()
        val imm = context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText_answer, 0)

        this.addView(root)
    }


}