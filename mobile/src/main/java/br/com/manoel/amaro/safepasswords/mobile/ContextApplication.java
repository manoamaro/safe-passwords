package br.com.manoel.amaro.safepasswords.mobile;

import android.app.Application;

/**
 * Created by manoel on 26/09/14.
 */
public class ContextApplication extends Application {

    private DatabaseHelper databaseHelper;

    public DatabaseHelper getDatabaseHelper() {
        if (this.databaseHelper == null)
            this.databaseHelper = new DatabaseHelper(this);
        return this.databaseHelper;
    }

}
