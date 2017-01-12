package com.gwen.android_double_line_menu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle drawerToggle;
    private final Handler mDrawerActionHandler = new Handler();
    private static final long DRAWER_CLOSE_DELAY_MS = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar(R.string.activity_home_title);
        setNavDrawer();
    }

    /** Set la toolbar */
    private void setToolbar(@StringRes int activity_home_title){
        // Set a Toolbar to replace the ActionBar.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(activity_home_title);
        setSupportActionBar(mToolbar);
    }

    /** Met a jour l'affichage du header du menu */
    private void setNavDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        drawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(drawerToggle);

        // Populate the Navigation Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);

        setMenuDrawerItems();
    }

    /** Creation des items du drawer menu **/
    private void setMenuDrawerItems(){
        DrawerListAdapter adapter = new DrawerListAdapter(this);

        adapter.addItem(new NavItem("Home", "Back home", R.drawable.ic_home));

        adapter.addSectionHeaderItem(new NavItem(getResources().getString(R.string.nav_title_1)));
        adapter.addItem(new NavItem(getResources().getString(R.string.nav_1), getResources().getString(R.string.nav_st_1), R.drawable.ic_1));
        adapter.addItem(new NavItem(getResources().getString(R.string.nav_2), getResources().getString(R.string.nav_st_2), R.drawable.ic_2));

        adapter.addSectionHeaderItem(new NavItem(getResources().getString(R.string.nav_title_2)));
        adapter.addItem(new NavItem(getResources().getString(R.string.nav_3), getResources().getString(R.string.nav_st_3), R.drawable.ic_3));
        adapter.addItem(new NavItem(getResources().getString(R.string.nav_4), getResources().getString(R.string.nav_st_4), R.drawable.ic_4));

        final ListView mNavList = (ListView) findViewById(R.id.navList);
        mNavList.setAdapter(adapter);

        // Drawer Item click listeners
        mNavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(mNavList, position);
            }
        });
    }

    // Initialisation du l'ouverture / fermeture du menu
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    /*
    * Called when a particular item from the navigation drawer
    * is selected.
    * */
    private void selectItemFromDrawer(final ListView lv, final int position) {
        lv.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerPane);
        mDrawerActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(position);
            }
        }, DRAWER_CLOSE_DELAY_MS);
    }

    /** Navigation, Actions lors de la selection des items du drawer menu */
    private void navigate (int itemId){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (itemId) {

            case 0:
                finish();
                Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(homeIntent);
                break;

            case 1:
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                // Insert the fragment by replacing any existing fragment
                ft.replace(R.id.main_content, FragmentNew.newInstance(), "F_NEW_1").addToBackStack("backStack").commit();
                break;

            case 2:
                ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                // Insert the fragment by replacing any existing fragment
                ft.replace(R.id.main_content, FragmentNew.newInstance(), "F_NEW_2").addToBackStack("backStack").commit();
                break;

            case 3:
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    /** Action lors de l'interaction avec le menu de la toolbar **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Action à l'issue du chargement de l'activity **/
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    /** Action lors du changement de configuration **/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
