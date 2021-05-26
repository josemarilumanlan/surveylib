package com.androidadvance.androidsurvey

import android.content.Context
import android.widget.Button
import android.widget.FrameLayout

class SurveyView constructor(context: Context) : FrameLayout(context) {


    lateinit var button: Button

//    <com.androidadvance.androidsurvey.widgets.NoSwipeViewPager
//    xmlns:android="http://schemas.android.com/apk/res/android"
//    android:id="@+id/pager"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent" />

    init {
        val frameLayoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        button = Button(context.applicationContext).apply { this.layoutParams = frameLayoutParams }
        this.addView(button, frameLayoutParams)
    }

}