package com.androidadvance.androidsurvey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager.widget.ViewPager
import com.androidadvance.androidsurvey.adapters.MainPagerAdapter
import com.androidadvance.androidsurvey.models.SurveyPojo
import com.androidadvance.androidsurvey.views.*
import com.google.gson.Gson


class SurveyView constructor(context: Context, bundle: Bundle) : FrameLayout(context) {

    var mSurveyPojo: SurveyPojo
    var mPager: ViewPager
    var pagerAdapter: MainPagerAdapter
    var style_string: String? = null


    init {
        val frameLayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        mSurveyPojo = Gson().fromJson(bundle.getString("json_survey"), SurveyPojo::class.java)
        if (bundle.containsKey("style")) {
            style_string = bundle.getString("style")
        }

        Log.i("json Object = ", mSurveyPojo.questions.toString())

        mPager = ViewPager(context)
        pagerAdapter = MainPagerAdapter()
        mPager.adapter = pagerAdapter

        this.addView(mPager, frameLayoutParams)


        //- START -
        if (!mSurveyPojo.surveyProperties.skipIntro)
            addToPager(StartView(this, mSurveyPojo.surveyProperties))

        for (mQuestion in mSurveyPojo.questions) {

            when (mQuestion.questionType) {

                "String" -> addToPager(TextSimpleView(this, mQuestion))

                "Checkboxes" -> addToPager(CheckboxesView(this, mQuestion))

                "Radioboxes" -> addToPager(RadioBoxesView(this, mQuestion))

                "Number" -> addToPager(NumberView(this, mQuestion))

                "StringMultiline" -> addToPager(MultilineView(this, mQuestion))
            }

        }
        //- END -
        addToPager(EndView(this, mSurveyPojo.surveyProperties))


    }

    private fun addToPager(view: View) {
        pagerAdapter.addView(view)
        pagerAdapter.notifyDataSetChanged()
    }

    fun go_to_next() {
        mPager.currentItem = mPager.currentItem + 1
    }

    fun event_survey_completed(instance: Answers) {
        val returnIntent = Intent()
        returnIntent.putExtra("answers", instance._json_object)
        Log.d(LOG_TAG, "event_survey_completed: ${instance._json_object}")
        Toast.makeText(context, "${instance._json_object}", Toast.LENGTH_SHORT).show()
        startActivity(context, Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME),null)
    }


    //USAGE COLLECTION

    //-----------------------------------------------------------------------------
    // Here's what the app should do to add a view to the ViewPager.
    override fun addView(newPage: View?) {
        val pageIndex: Int = pagerAdapter.addView(newPage)
        // You might want to make "newPage" the currently displayed page:
        mPager.setCurrentItem(pageIndex, true)
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to remove a view from the ViewPager.
    override fun removeView(defunctPage: View?) {
        var pageIndex: Int = pagerAdapter.removeView(mPager, defunctPage)
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount()) pageIndex--
        mPager.setCurrentItem(pageIndex)
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to get the currently displayed page.
    fun getCurrentPage(): View? {
        return pagerAdapter.getView(mPager.getCurrentItem())
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to set the currently displayed page.  "pageToShow" must
    // currently be in the adapter, or this will crash.
    fun setCurrentPage(pageToShow: View) {
        mPager.setCurrentItem(pagerAdapter.getItemPosition(pageToShow), true)
    }

    companion object {
        const val LOG_TAG = "SurveyView"

    }

}