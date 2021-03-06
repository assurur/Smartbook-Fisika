package com.galihpw.smartbookfisika;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.galihpw.smartbookfisika.Adapter.DaftarMenu;
import com.galihpw.smartbookfisika.Adapter.GridAdapter;
import com.galihpw.smartbookfisika.Adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements GridAdapter.ItemClickListener {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    public View v;
    private LinearLayout mLinearLayout;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    GridAdapter mAdapter;
    Timer timer;
    List<DaftarMenu> mItems;
    NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inisiasi data

        mItems = new ArrayList<DaftarMenu>();
        DaftarMenu nama = new DaftarMenu();
        nama.setName("Kompetensi Dasar dan Indikator Pembelajaran");
        nama.setThumbnail(R.drawable.logomini);
        mItems.add(nama);

        nama = new DaftarMenu();
        nama.setName("Materi Listrik Statis");
        nama.setThumbnail(R.drawable.logomini);
        mItems.add(nama);

        nama = new DaftarMenu();
        nama.setName("Latihan Soal");
        nama.setThumbnail(R.drawable.logomini);
        mItems.add(nama);

        nama = new DaftarMenu();
        nama.setName("Tentang Aplikasi");
        nama.setThumbnail(R.drawable.logomini);
        mItems.add(nama);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager  = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GridAdapter(this,mItems);
        mAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 4000, 4000);

    }
    private final void focusOnView(){
        nestedScrollView.post(new Runnable() {
            @Override
            public void run() {
                nestedScrollView.scrollTo(0, nestedScrollView.getBottom());
            }
        });
    }
    @Override
    public void onItemClick(View view, int position) {
        if(position==1){
            Intent i = new Intent(MainActivity.this, MenuMateri.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        else if(position==2){
            Intent i = new Intent(MainActivity.this, MenuLatihan.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                    else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                    else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                    else if(viewPager.getCurrentItem()==3){
                        viewPager.setCurrentItem(4);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                    else if(viewPager.getCurrentItem()==4){
                        viewPager.setCurrentItem(5);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }
                    else if(viewPager.getCurrentItem()==5){
                        viewPager.setCurrentItem(0);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    }

                }
            });
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if(timer != null){
            timer.cancel();
            //cancel timer task and assign null
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
