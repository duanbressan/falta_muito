package duan.example.faltamuito.DAOs;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import duan.example.faltamuito.models.Category;
import duan.example.faltamuito.models.Subject;
import io.realm.Realm;
import io.realm.RealmResults;

public class DAOSubject {

    private Realm realm;
    private Context context;

    public DAOSubject(Context context){
        realm = Realm.getDefaultInstance();

        this.context = context;
    }

    public DAOSubject(){
        realm = Realm.getDefaultInstance();
    }

    public void insertSubject(String name, String category_name, String half_night, String half_integral, boolean done){
        try {
            realm.beginTransaction();

            Subject subject = realm.createObject(Subject.class);

            subject.setName(name);

            DAOCategory daoCategory = new DAOCategory(context);
            Category category = daoCategory.findCategory(category_name);

            if(category == null){
                category = realm.createObject(Category.class);
                category.setName(category_name);
            }

            subject.setCategory(category);

            subject.setHalf_integral(half_integral);
            subject.setHalf_night(half_night);
            subject.setDone(done);

            realm.commitTransaction();

        } catch (Exception e) {
            realm.cancelTransaction();
            e.printStackTrace();
        }
    }

    public List<Subject> findAllSubjects() {
        List<Subject> logSubject = new ArrayList<>();
        try{
            RealmResults realmResults = realm.where(Subject.class).findAll();
            realmResults.sort("half_integral", RealmResults.SORT_ORDER_ASCENDING);

            logSubject.addAll(realmResults);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return logSubject;
    }

    public long getAllSubjectCount() {
        long count = 0;
        try{
            count = realm.where(Subject.class).count();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public long getAllSubjectDoneCount() {
        long count = 0;
        try{
            count = realm.where(Subject.class).equalTo("done", true).count();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public void deleteAllSubjects() {
        try {
            realm.beginTransaction();
            realm.where(Subject.class).findAll().clear();
            realm.commitTransaction();
        }
        catch (Exception e){
            realm.cancelTransaction();
            e.printStackTrace();
        }
    }

    public void udpadeSubject(Subject subject, boolean done) {
        try {
            realm.beginTransaction();

            subject.setDone(done);
            realm.copyToRealmOrUpdate(subject);
            realm.commitTransaction();
        }
        catch (Exception e){
            realm.cancelTransaction();
            e.printStackTrace();
        }
    }
}
