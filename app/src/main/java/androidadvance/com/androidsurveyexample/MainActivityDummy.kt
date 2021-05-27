package androidadvance.com.androidsurveyexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import java.io.IOException
import java.nio.charset.Charset

class MainActivityDummy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

setContentView(R.layout.activity_main_dummy)
        val bundle = bundleOf(
                "json_survey" to """{
  "survey_properties": {
    "intro_message": "None...It will be skipped!",
    "end_message": "Thank you for having the time to take our survey.",
    "skip_intro": true
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
      "question_title": "With what technologies you work ?",
      "description": "",
      "required": false,
      "random_choices": true,
      "choices": [
        "Java",
        "C++",
        "PHP",
        "Javascript",
        "C#",
        "SQL",
        "Python",
        "Node.js"
      ]
    },
    {
      "question_type": "String",
      "question_title": "What is your desktop operating system ?",
      "description": "",
      "required": true
    },
    {
      "question_title": "How much are you paid ?",
      "description": "[per year]",
      "required": true,
      "question_type": "Number"
    },
    {
      "question_type": "StringMultiline",
      "question_title": "Why do you like your job as a developer ?",
      "description": "",
      "required": false,
      "number_of_lines": 2
    }
  ]
}""")
//        setContentView(surveyView)

//        SurveyView(view,bundle)
////        surveyView.getSurveyViewListener()
//        //Nothing fancy here. Plain old simple buttons....
////        val button_survey_example_1 = findViewById<View>(R.id.llButtonsContainer) as LinearLayout

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