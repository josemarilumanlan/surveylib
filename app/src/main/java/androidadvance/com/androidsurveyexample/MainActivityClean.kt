package androidadvance.com.androidsurveyexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.androidadvance.androidsurvey.SurveyActivity
import java.io.IOException
import java.nio.charset.Charset

class MainActivityClean : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Nothing fancy here. Plain old simple buttons....
        val button_survey_example_1 = findViewById<View>(R.id.llButtonsContainer) as LinearLayout

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