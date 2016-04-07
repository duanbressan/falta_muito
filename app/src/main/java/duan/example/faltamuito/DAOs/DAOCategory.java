package duan.example.faltamuito.DAOs;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import duan.example.faltamuito.models.Category;
import duan.example.faltamuito.models.Subject;
import io.realm.Realm;
import io.realm.RealmResults;

public class DAOCategory {

    private Realm realm;
    private Context context;

    public DAOCategory(Context context){
        realm = Realm.getDefaultInstance();

        this.context = context;
    }

    public DAOCategory(){
        realm = Realm.getDefaultInstance();
    }

    public List<Category> findAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        try{
            RealmResults realmResults = realm.where(Category.class).findAll();
            realmResults.sort("name", RealmResults.SORT_ORDER_DESCENDING);

            categoryList.addAll(realmResults);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return categoryList;
    }

    public Category findCategory(String category_name){

        try{
            Category category = realm.where(Category.class).equalTo("name", category_name).findFirst();
            return category;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
