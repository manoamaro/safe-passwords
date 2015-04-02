package br.com.manoel.amaro.safepasswords.mobile.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.android.ContextHolder;

import br.com.manoel.amaro.safepasswords.mobile.R;
import br.com.manoel.amaro.safepasswords.mobile.ContextApplication;
import br.com.manoel.amaro.safepasswords.mobile.DatabaseHelper;
import br.com.manoel.amaro.safepasswords.mobile.domain.AbstractDao;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AbstractActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    EditText pinField;
    Button verifyButton;
    Button resetButton;

    SharedPreferences preferences;

    private void flywaydbMigrate() {
        SQLiteDatabase db = openOrCreateDatabase(DatabaseHelper.DATABASE_NAME,
                Context.MODE_PRIVATE, null);
        ContextHolder.setContext(this);
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:sqlite:" + db.getPath(), "", "");
        flyway.migrate();
        db.close();

        AbstractDao.setHelper(this.application.getDatabaseHelper());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = (ContextApplication) getApplication();

        flywaydbMigrate();
        setContentView(R.layout.activity_login);

        this.pinField = (EditText) findViewById(R.id.pinField);
        this.pinField.setOnClickListener(this);

        this.verifyButton = (Button) findViewById(R.id.verifyButton);
        this.verifyButton.setOnClickListener(this);

        this.resetButton = (Button) findViewById(R.id.resetButton);
        this.resetButton.setOnClickListener(this);

        this.preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    private void tryLogin() {
        String digested = new String(Hex.encodeHex(
                DigestUtils.sha1(this.pinField.getText().toString())));
        String savedPassword = this.preferences.getString("password", "");
        if (!savedPassword.isEmpty() && savedPassword.equals(digested)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            this.preferences.edit().putString("password", digested).commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.verifyButton:
                tryLogin();
                break;
            case R.id.resetButton:
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE)
            this.tryLogin();
        return true;
    }
}



