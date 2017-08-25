package patrickcom.patto.task;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import patrickcom.patto.task.fragments.AllArticles;
import patrickcom.patto.task.fragments.TechArticles;
import patrickcom.patto.task.fragments.TechSources;
import patrickcom.patto.task.fragments.ViewMore1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String title,image,description,id,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentTransaction f=getSupportFragmentManager().beginTransaction();
        f.replace(R.id.main, new AllArticles(), getString(R.string.app_name));
       // f.isAddToBackStackAllowed();
        f.commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.allArticles) {

            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.main, new AllArticles(), getString(R.string.app_name));
           //f.addToBackStack(null);
            f.commit();

        } else if (id == R.id.techSources) {
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            f.replace(R.id.main, new TechSources(), getString(R.string.app_name));
           // f.addToBackStack(null);
            f.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //view more
    public void goToViewMore(String titlee,String imagee, String descriptionn,String url){
        title=titlee;
        image=imagee;
        description=descriptionn;
        link=url;

        FragmentTransaction f=getSupportFragmentManager().beginTransaction();
        f.replace(R.id.main, new ViewMore1(), getString(R.string.app_name));
      //  f.isAddToBackStackAllowed();
        f.commit();
    }

//go to tech articles
    public void goToTechArticles(String idd){
        id=idd;
        FragmentTransaction f=getSupportFragmentManager().beginTransaction();
        f.replace(R.id.main, new TechArticles(), getString(R.string.app_name));
        f.isAddToBackStackAllowed();
        f.commit();
    }
}
