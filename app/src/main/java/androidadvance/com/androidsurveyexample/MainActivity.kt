package androidadvance.com.androidsurveyexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.androidadvance.androidsurvey.SurveyActivity
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Nothing fancy here. Plain old simple buttons....
        val button_survey_example_1 = findViewById<View>(R.id.button_survey_example_1) as Button
        val button_survey_example_2 = findViewById<View>(R.id.button_survey_example_2) as Button
        val button_survey_example_3 = findViewById<View>(R.id.button_survey_example_3) as Button
        button_survey_example_1.setOnClickListener {
            val i_survey = Intent(this@MainActivity, SurveyActivity::class.java)
            //you have to pass as an extra the json string.
                i_survey.putExtra("json_survey","""{
  "survey_properties": {
    "intro_message": "<strong>Your feedback helps us to build a better mobile product.</strong><br><br><br>   Hello, Feedback from our clients, friends and family is how we make key decisions on what the future holds for XYZ App.<br><br>By combining data and previous feedback we have introduced many new features e.g. x, y, z.<br><br>It will take less than 2 minutes to answer the feedback quiz.",
    "end_message": "Thank you for having the time to take our survey.",
    "skip_intro": false
  },
  "questions": [
    {
      "question_type": "Checkboxes",
      "question_title": "What were you hoping the XYZ mobile app would do?",
      "description": "(Select all that apply)",
      "required": false,
      "random_choices": false,
      "choices": [
        "thing #1",
        "thing #2",
        "thing #3",
        "thing #4"
      ]
    }
  ]
}""")
//            i_survey.putExtra("json_survey", loadSurveyJson("example_survey_1.json"))
            startActivityForResult(i_survey, SURVEY_REQUEST)
        }
        button_survey_example_2.setOnClickListener {
            val i_survey = Intent(this@MainActivity, SurveyActivity::class.java)
            i_survey.putExtra("json_survey", loadSurveyJson("example_survey_2.json"))
            startActivityForResult(i_survey, SURVEY_REQUEST)
        }
        button_survey_example_3.setOnClickListener {
            val i_survey = Intent(this@MainActivity, SurveyActivity::class.java)
            i_survey.putExtra("json_survey", loadSurveyJson("example_survey_3.json"))
            startActivityForResult(i_survey, SURVEY_REQUEST)
        }
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
            null
        }
    }

    companion object {
        private const val SURVEY_REQUEST = 1337
    }
}