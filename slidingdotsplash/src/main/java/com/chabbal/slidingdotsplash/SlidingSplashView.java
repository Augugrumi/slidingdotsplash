package com.chabbal.slidingdotsplash;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by Johny on 28/01/2017.
 */

public class SlidingSplashView extends FrameLayout {


    private ViewPager mViewPager;
    private TextViewPagerAdapter mViewPagerAdapter;
    private OnSetTextListener mOnSetTextListener;
    ViewPager.OnPageChangeListener mOnPageChangeListener;


    public SlidingSplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public SlidingSplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.sliding_splash_view,this);
        mViewPager = (ViewPager) findViewById(R.id.pager_splash);
        mViewPagerAdapter = new TextViewPagerAdapter(context, mOnSetTextListener);
        if(!isInEditMode())
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingSplashView, 0,
                    0);
            int id = typedArray.getResourceId(R.styleable.SlidingSplashView_imageResources,0);
            if(id != 0){
                TypedArray typed = context.getResources().obtainTypedArray(id);
                int[] drawables = new int[typed.length()];
                for(int i = 0 ; i < drawables.length ; ++i){
                    drawables[i] = typed.getResourceId(i,0);
                }
                typed.recycle();
                mViewPagerAdapter.setTextResources(drawables);
            }
            typedArray.recycle();
        }
        mViewPager.setAdapter(mViewPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager,true);
    }

    public void setTextResources(@NonNull @ArrayRes @Size(min = 2) int[] textResources){
        mViewPagerAdapter.setTextResources(textResources);
    }



    public void setOnShowTextListener(OnSetTextListener onShowTextListener){
        mOnSetTextListener = onShowTextListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mViewPagerAdapter.setOnPagerItemClick(onItemClickListener);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener){
        mViewPager.addOnPageChangeListener(mOnPageChangeListener = onPageChangeListener);
    }

    public void removeOnPageChangeListener(){
        mViewPager.removeOnPageChangeListener(mOnPageChangeListener);
    }

}
