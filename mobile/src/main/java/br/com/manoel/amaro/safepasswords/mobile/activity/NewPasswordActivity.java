package br.com.manoel.amaro.safepasswords.mobile.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import br.com.manoel.amaro.safepasswords.mobile.R;

public class NewPasswordActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        setSupportActionBar((Toolbar) findViewById(R.id.my_awesome_toolbar));
        getSupportActionBar().setTitle(R.string.title_activity_new_password);
    }
}
