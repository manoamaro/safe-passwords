package br.com.manoel.amaro.safepasswords.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.manoel.amaro.safepasswords.R;
import br.com.manoel.amaro.safepasswords.domain.Password;

public class DetailActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Password password = (Password) getIntent().getExtras().getSerializable("PASSWORD");

        TextView textView = (TextView) findViewById(R.id.password_title);
        textView.setText(password.getTitle());

    }

}
