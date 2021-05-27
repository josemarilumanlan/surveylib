package com.androidadvance.androidsurvey

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.androidadvance.androidsurvey.adapters.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main_clean.*
import java.io.IOException
import java.nio.charset.Charset


class MainActivityClean : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_clean)

        val bundle = bundleOf(
                "json_survey" to """{
  "survey_properties": {
    "intro_message": "<strong>Your feedback helps us to builds a better and more personal ads for you.</strong><br><br><br>   Some intro messages etc etc etc",
    "end_message": "Thank you for having the time to take our survey.",
    "skip_intro": false
  },
  "questions": [
    {
      "question_type": "Radioboxes",
      "question_title": "What is your age group ?",
      "description": "",
      "required": true,
      "random_choices": false,
      "choices": [
        "less than 20",
        "20-30",
        "30-40",
        "40-50",
        "50+"
      ]
    },
    {
      "question_type": "Radioboxes",
      "question_title": "GENDER ?",
      "description": "",
      "required": true,
      "random_choices": true,
      "choices": [
        "male",
        "female"
      ]
    },
    {
      "question_type": "Checkboxes",
      "question_title": "Which of the following  promotions interest you?",
      "description": "(Select all that apply)",
      "required": false,
      "random_choices": false,
      "choices": [
        "Tech",
        "Lifestyle",
        "Ultra Cheap Deals",
        "Apparels"
      ]
    },
    {
      "question_type": "Radioboxes",
      "question_title": "which Perth sub-region are you in?",
      "description": "",
      "required": true,
      "random_choices": false,
      "choices": [
        "Inner Metro",
        "North East",
        "North West",
        "South East",
        "South West"
      ]
    },
    {
      "question_title": "How much you spend per coffee every day ?",
      "description": "",
      "required": false,
      "question_type": "Number"
    }
  ]
}""")
        llContainer.addView(SurveyView(this, bundle))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SURVEY_REQUEST) {
            if (resultCode == RESULT_OK) {
                val answers_json = data!!.extras!!.getString("answers")
                Log.d("****", "****************** WE HAVE ANSWERS ******************")
                Log.v("ANSWERS JSON", answers_json!!)
                Log.d("****", "*****************************************************")

                //do whatever you want with them...
            }
        }
        //not turned on by default
        super.onActivityResult(requestCode, resultCode, data)
    }

    //json stored in the assets folder. but you can get it from wherever you like.
    private fun loadSurveyJson(filename: String): String? {
        return try {
            val `is` = assets.open(filename)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            ""
        }
    }

    companion object {
        private const val SURVEY_REQUEST = 1337
    }
}