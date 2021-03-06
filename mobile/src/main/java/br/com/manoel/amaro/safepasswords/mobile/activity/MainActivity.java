package br.com.manoel.amaro.safepasswords.mobile.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.manoel.amaro.safepasswords.mobile.R;
import br.com.manoel.amaro.safepasswords.mobile.activity.fragment.PasswordListFragment;
import br.com.manoel.amaro.safepasswords.mobile.domain.Password;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AbstractActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence originalTitle;

    @InjectView(R.id.my_awesome_toolbar)
    Toolbar mainToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


        setSupportActionBar(mainToolBar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        originalTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        switch (position) {
            case 0:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PasswordListFragment())
                        .commit();
                break;
            case 1:
                Password password = new Password();
                password.setTitle("Testing");
                password.setPassword("TESTE");
                Password.Dao dao = new Password.Dao();
                dao.persist(password);
                break;
        }

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(originalTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
