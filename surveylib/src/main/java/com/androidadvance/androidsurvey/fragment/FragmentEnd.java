package com.androidadvance.androidsurvey.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.androidadvance.androidsurvey.Answers;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyView;
import com.androidadvance.androidsurvey.models.SurveyProperties;

public class FragmentEnd extends Fragment {

    private SurveyView surveyView;
    private FragmentActivity mContext;
    private TextView textView_end;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_end, container, false);
        Button button_finish = rootView.findViewById(R.id.button_finish);
        textView_end = rootView.findViewById(R.id.textView_end);
        button_finish.setOnClickListener(v -> surveyView.event_survey_completed(Answers.getInstance()));
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survery_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");

        assert survery_properties != null;
        textView_end.setText(Html.fromHtml(survery_properties.getEndMessage()));

    }

    public static final FragmentEnd newInstance(SurveyView surveyView){
        FragmentEnd f = new FragmentEnd();
        f.surveyView = surveyView;
        return f;
    }

}