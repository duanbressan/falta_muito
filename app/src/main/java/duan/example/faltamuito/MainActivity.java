package duan.example.faltamuito;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import duan.example.faltamuito.DAOs.DAOCategory;
import duan.example.faltamuito.DAOs.DAOSubject;
import duan.example.faltamuito.adapters.SubjectsInformationAdapter;
import duan.example.faltamuito.models.Subject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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

        setRealm();
        insertSubjectInRealmBD();
        setFullInformation();
        setFullSubjectInformation();
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

    private void setRealm(){

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    private void insertSubjectInRealmBD(){

        DAOSubject daoSubject = new DAOSubject(this);
        List<Subject> subjectList = daoSubject.findAllSubjects();

        if(subjectList == null || subjectList.size() == 0){

            /**HARDWARE**/
            daoSubject.insertSubject("Circuítos Digitais", "Hardware", "1º", "1º", false);
            daoSubject.insertSubject("Sistemas Digitais", "Hardware", "2º", "2º", false);
            daoSubject.insertSubject("organização de Computadores", "Hardware", "3º", "4º", false);
            daoSubject.insertSubject("Sistemas Operacionais", "Hardware", "5º", "7º", false);
            daoSubject.insertSubject("Redes de Computadores", "Hardware", "6º", "8º", false);
            daoSubject.insertSubject("Computação Distribuída", "Hardware", "7º", "9º", false);

            /**MATEMÁTICA**/
            daoSubject.insertSubject("Matemática Instrumental", "Matemática", "1º", "1º", false);
            daoSubject.insertSubject("Cálculo 1", "Matemática", "2º", "3º", false);
            daoSubject.insertSubject("Cálculo 2", "Matemática", "3º", "4º", false);
            daoSubject.insertSubject("Cálculo Numérico", "Matemática", "4º", "6º", false);

            /**GEOMETRIA**/
            daoSubject.insertSubject("Geometria Analítica", "Geometria", "1º", "2º", false);
            daoSubject.insertSubject("Álgebra Linear", "Geometria", "2º", "3º", false);
            daoSubject.insertSubject("Computação Gráfica", "Geometria", "6º", "7º", false);

            /**ESTATÍTICA**/
            daoSubject.insertSubject("Estatística Básica", "Estatística", "2º", "2º", false);
            daoSubject.insertSubject("probabiliadade e Estatística", "Estatística", "3º", "4º", false);

            /**ALGORÍTOMOS**/
            daoSubject.insertSubject("Algorítimos e Programação", "Algorítimos", "1º", "1º", false);
            daoSubject.insertSubject("Estrutura de Dados 1", "Algorítimos", "2º", "2º", false);
            daoSubject.insertSubject("estrutura de dados 2", "Algorítimos", "3º", "3º", false);

            /**PROGRAMAÇÃO**/
            daoSubject.insertSubject("Programação 1", "Programação", "3º", "3º", false);
            daoSubject.insertSubject("programação 2", "Programação", "4º", "4º", false);
            daoSubject.insertSubject("Engenharia de Software 1", "Programação", "5º", "6º", false);
            daoSubject.insertSubject("Engenharia de Software 2", "Programação", "6º", "6º", false);
            daoSubject.insertSubject("Planejamento e gestão de Projetos", "Programação", "6º", "8º", false);

            /**TEORIA**/
            daoSubject.insertSubject("Teoria da Computação", "Teoria", "4º", "5º", false);
            daoSubject.insertSubject("Linguagens formais e Autônomas", "Teoria", "5º", "6º", false);
            daoSubject.insertSubject("Construção de Compiladores", "Teoria", "6º", "7º", false);

            /**GRAFOS**/
            daoSubject.insertSubject("Grafos", "Grafos", "4º", "5º", false);
            daoSubject.insertSubject("Inteligencia Artificial", "Grafos", "7º", "7º", false);

            /**BANCO DE DADOS**/
            daoSubject.insertSubject("Banco de Dados 1", "Banco de Dados", "4º", "4º", false);
            daoSubject.insertSubject("Banco de Dados 2", "Banco de Dados", "5º", "5º", false);

            /**TCC**/
            daoSubject.insertSubject("Trabalho de Conclusão de Curso 1", "TCC", "7º", "9º", false);
            daoSubject.insertSubject("Trabalho de Conclusão de Curso 2", "TCC", "8º", "10º", false);

            /**SEM**/
            daoSubject.insertSubject("Introdução a Informática", "Sem Pré Requisitos (Específico)", "1º", "1º", false);
            daoSubject.insertSubject("Matemática Discreta", "Sem Pré Requisitos (Específico)", "3º", "3º", false);
            daoSubject.insertSubject("Iniciação a Pratica Científica", "Sem Pré Requisitos (Específico)", "5º", "5º", false);
            daoSubject.insertSubject("Segurança e Auditoria de Sistema", "Sem Pré Requisitos (Específico)", "8º", "9º", false);

            /**SEM**/
            daoSubject.insertSubject("Introdução ao Pensamento Social", "Sem Pré Requisitos (Comum)", "4º", "8º", false);
            daoSubject.insertSubject("Direitos e Cidadania", "Sem Pré Requisitos (Comum)", "4º", "6º", false);
            daoSubject.insertSubject("Meio Ambiente Economia e Sociedade", "Sem Pré Requisitos (Comum)", "8º", "8º", false);
            daoSubject.insertSubject("Historia da Fronteira Sul", "Sem Pré Requisitos (Comum)", "6º", "6º", false);
            daoSubject.insertSubject("Fundamentos da Critica Social", "Sem Pré Requisitos (Comum)", "7º", "7º", false);

            /**OPITATIVAS**/
            daoSubject.insertSubject("Optativa 1", "Optativas", "7º", "8º", false);
            daoSubject.insertSubject("Optativa 2", "Optativas", "7º", "9º", false);
            daoSubject.insertSubject("Optativa 3", "Optativas", "8º", "10º", false);
            daoSubject.insertSubject("Optativa 4", "Optativas", "8º", "10º", false);
        }
    }

    @Override
    protected void onResume() {
        setFullInformation();
        setFullSubjectInformation();
        super.onResume();
    }

    private void setFullInformation(){
        TextView textViewPercentage = (TextView) findViewById(R.id.textViewPercentage);
        TextView textViewInformation = (TextView) findViewById(R.id.textViewInformation);

        DAOSubject daoSubject = new DAOSubject(this);
        long subjects = daoSubject.getAllSubjectCount();
        long subjects_done = daoSubject.getAllSubjectDoneCount();

        int total = (int) ((subjects_done * 100) / subjects);

        textViewPercentage.setText(total + getResources().getString(R.string.percent));
        textViewInformation.setText(getResources().getString(R.string.cursada) + " " + subjects_done + " de " + subjects + " " + getResources().getString(R.string.materia));
    }

    private void setFullSubjectInformation(){

        ListView listView = (ListView) findViewById(R.id.listViewSubject);

        DAOCategory daoCategory = new DAOCategory(this);
        SubjectsInformationAdapter subjectsAdapter = new SubjectsInformationAdapter(this, daoCategory.findAllCategory());

        listView.setAdapter(subjectsAdapter);
    }
}
