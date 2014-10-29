package br.com.manoel.amaro.safepasswords.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.manoel.amaro.safepasswords.ContextApplication;

/**
 * Created by manoel on 26/09/14.
 */
public class AbstractActivity extends ActionBarActivity {
    protected ContextApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = (ContextApplication) getApplication();
    }
}
