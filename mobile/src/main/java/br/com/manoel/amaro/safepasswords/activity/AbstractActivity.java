package br.com.manoel.amaro.safepasswords.activity;

import android.app.Activity;
import android.os.Bundle;

import br.com.manoel.amaro.safepasswords.ContextApplication;

/**
 * Created by manoel on 26/09/14.
 */
public class AbstractActivity extends Activity {
    protected ContextApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = (ContextApplication) getApplication();
    }
}
