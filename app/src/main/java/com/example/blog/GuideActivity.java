package com.example.blog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {

    private ArrayList guideViews = new ArrayList<>();
    private ViewPager mypage;
    private ImageView point_1;
    private ImageView point_2;
    private ImageView point_3;
    private Button btn_Start;
    private  Adapter adapter = new Adapter(guideViews);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        inView();
        mypage.setAdapter(adapter);

        point_1 = (ImageView) findViewById(R.id.ponint_1);
        point_2 = (ImageView) findViewById(R.id.ponint_2);
        point_3 = (ImageView) findViewById(R.id.ponint_3);
        btn_Start = (Button) findViewById(R.id.btn_start);

        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(start);
                finish();
            }
        });

        //小圆点的实现
        mypage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        point_1.setImageResource(R.drawable.point_press);
                        point_2.setImageResource(R.drawable.point_normal);
                        point_3.setImageResource(R.drawable.point_normal);
                        btn_Start.setVisibility(View.GONE);
                        break;

                    case 1:
                        point_1.setImageResource(R.drawable.point_normal);
                        point_2.setImageResource(R.drawable.point_press);
                        point_3.setImageResource(R.drawable.point_normal);
                        btn_Start.setVisibility(View.GONE);
                        break;

                    case 2:
                        point_1.setImageResource(R.drawable.point_normal);
                        point_2.setImageResource(R.drawable.point_normal);
                        point_3.setImageResource(R.drawable.point_press);
                        btn_Start.setVisibility(View.VISIBLE);
                        break;

                    default:break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    public void inView() {
        mypage = (ViewPager) findViewById(R.id.guide);
        LayoutInflater lf = getLayoutInflater().from(GuideActivity.this);
        View view1 = lf.inflate(R.layout.guidepage_1, null);
        View view2 = lf.inflate(R.layout.guidepage_2, null);
        View view3 = lf.inflate(R.layout.guidepage_3, null);
        guideViews.add(view1);
        guideViews.add(view2);
        guideViews.add(view3);

    }



    public class  Adapter extends PagerAdapter {
        private ArrayList guideViews = new ArrayList<>();
        public Adapter(ArrayList views) {
            this.guideViews = views;//接收传入的图像
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ((ViewPager) container).addView((View) guideViews.get(position));
            return guideViews.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView((View) guideViews.get(position));
        }

        @Override
        public int getCount() {
            if (guideViews != null) {
                return guideViews.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
