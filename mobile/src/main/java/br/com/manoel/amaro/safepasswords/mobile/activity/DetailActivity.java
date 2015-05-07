package br.com.manoel.amaro.safepasswords.mobile.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.manoel.amaro.safepasswords.mobile.R;
import br.com.manoel.amaro.safepasswords.mobile.domain.Password;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailActivity extends AbstractActivity {

    @InjectView(R.id.password_detail_toolbar)
    Toolbar mainToolbar;
    @InjectView(R.id.password_detail_image)
    View passwordImage;
    @InjectView(R.id.password_detail_title)
    TextView passwordTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        Password password = (Password) getIntent().getExtras().getSerializable("PASSWORD");

        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        passwordTitle.setText(password.getTitle());
        TypedArray categories = getResources().obtainTypedArray(R.array.categories_list);

        passwordImage.setBackground(categories.getDrawable(1));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
