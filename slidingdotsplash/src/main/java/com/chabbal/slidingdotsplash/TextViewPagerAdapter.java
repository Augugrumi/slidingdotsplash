package com.chabbal.slidingdotsplash;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Johny on 11/01/2017.
 */

public class TextViewPagerAdapter extends ViewPagerAdapter {

    private Context mContext;
    private OnItemClickListener mOnPagerItemClick;
    private OnSetTextListener mOnSetTextListener;
    private int mTextResources[];

    public TextViewPagerAdapter(Context context, @NonNull OnSetTextListener onSetTextListener ) {
        mContext = context;
        mOnSetTextListener = onSetTextListener;
        mTextResources = new int[]{};
    }

    @Override
    public View getItem(final int position) {
        TextView textView = (TextView)LayoutInflater.from(mContext).inflate(R.layout.item_view_pager_image,null);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnPagerItemClick != null){
                    mOnPagerItemClick.onPagerItemClick(v,position);
                }
            }
        });
        if(mOnSetTextListener != null){
            mOnSetTextListener.setText(textView,position);
        }
        else{

            textView.setText(mTextResources[position]);
        }
        return textView;
    }


    @Override
    public int getCount() {
        return mTextResources.length;
    }

    public void setTextResources(@NonNull @ArrayRes @Size(min = 2) int[] textResources){
        mTextResources = textResources;
        notifyDataSetChanged();
    }

    public void setOnPagerItemClick(@NonNull OnItemClickListener onPagerItemClickListener){
        mOnPagerItemClick = onPagerItemClickListener;
    }
}
