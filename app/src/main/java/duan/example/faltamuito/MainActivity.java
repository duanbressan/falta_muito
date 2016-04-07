package duan.example.faltamuito;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import duan.example.faltamuito.DAOs.DAOSubject;
import duan.example.faltamuito.models.Subject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(duan.example.faltamuito.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(duan.example.faltamuito.R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(duan.example.faltamuito.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, duan.example.faltamuito.R.string.navigation_drawer_open, duan.example.faltamuito.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(duan.example.faltamuito.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(duan.example.faltamuito.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(duan.example.faltamuito.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == duan.example.faltamuito.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_materials) {
            loadMaterialsActivity();
        } else if (id == R.id.nav_statics) {

        } else if (id == R.id.nav_nexts) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(duan.example.faltamuito.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadMaterialsActivity(){
        Intent intent = new Intent(MainActivity.this, MaterialsActivity.class);
        startActivity(intent);
    }

    private void insertSubjectInRealmBD(){

        DAOSubject daoSubject = new DAOSubject(this);
        List<Subject> subjectList = daoSubject.findAllLogUse();

        if(subjectList == null || subjectList.size() == 0){

            /**MATEMÁTICA**/
            daoSubject.insertSubject("Matemática Instrumental", "Matemática", "1º", "1º", false);
            daoSubject.insertSubject("Cálculo 1", "Matemática", "3º", "2º", false);
            daoSubject.insertSubject("Cálculo 2", "Matemática", "5º", "3º", false);
            daoSubject.insertSubject("Cálculo Numérico", "Matemática", "6º", "5º", false);

            /**PROGRAMAÇÃO**/
            daoSubject.insertSubject("Algorítimos e Programação", "Programação", "1º", "1º", false);
            daoSubject.insertSubject("Estrutura de Dados 1", "Programação", "3º", "2º", false);
            daoSubject.insertSubject("estrutura de dados 2", "Programação", "5º", "3º", false);
            daoSubject.insertSubject("Grafos", "Programação", "6º", "5º", false);

            /**HARDWARE**/
            daoSubject.insertSubject("Circuítos Digitais", "Hardware", "1º", "1º", false);
            daoSubject.insertSubject("Sistemas Digitais", "Hardware", "3º", "2º", false);
            daoSubject.insertSubject("organização de Computadores", "Hardware", "5º", "3º", false);
            daoSubject.insertSubject("Sistemas Operacionais", "Hardware", "6º", "5º", false);

        }
    }
}
