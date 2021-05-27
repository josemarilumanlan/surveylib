package com.androidadvance.androidsurvey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.androidadvance.androidsurvey.R;

public class MineAdapter extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MineAdapter(Context context, int layoutID) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        //Fill Data directly from Repository
//        return CenterRepository.getSingletonInstance().getListOfLavels().size();
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View itemView = mLayoutInflater.inflate(R.layout.carousel_page, container,
                false);

        // Unlock Button
        itemView.findViewById(R.id.unlock_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (CenterRepository.getSingletonInstance().getCurrentUser().getGold() >=
//                        CenterRepository.getSingletonInstance().getListOfLavels().get(position).getUnlockCost()) {
//
//                    //If User has more gold than cost to unlock hide lock image and buy it
//
//                    CenterRepository.getSingletonInstance().getCurrentUser().setGold(
//                            CenterRepository.getSingletonInstance().getCurrentUser().getGold()
//                                    - CenterRepository.getSingletonInstance().getListOfLavels().get(position).getUnlockCost()); // Update user's gold
//
//                } else {
//                    // Not enough money
//                    Toast.makeText(mContext, "Not enough money to purchase", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}