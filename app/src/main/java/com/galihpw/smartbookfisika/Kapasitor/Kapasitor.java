package com.galihpw.smartbookfisika.Kapasitor;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.galihpw.smartbookfisika.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Kapasitor extends AppCompatActivity {

    @BindView(R.id.tekska1)
    TextView tekska1;

    @BindView(R.id.imgka1)
    ImageView imgka1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kapasitor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.imgka1).into(imgka1);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Neat_Chalk.ttf");
        tekska1.setTypeface(custom_font);
    }
}
