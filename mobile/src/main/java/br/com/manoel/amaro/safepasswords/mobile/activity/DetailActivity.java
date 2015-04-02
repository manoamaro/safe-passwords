package br.com.manoel.amaro.safepasswords.mobile.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.com.manoel.amaro.safepasswords.mobile.R;
import br.com.manoel.amaro.safepasswords.mobile.domain.Password;

public class DetailActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.my_awesome_toolbar));

        Password password = (Password) getIntent().getExtras().getSerializable("PASSWORD");

        TextView textView = (TextView) findViewById(R.id.password_title);
        textView.setText(password.getTitle());

    }

}
