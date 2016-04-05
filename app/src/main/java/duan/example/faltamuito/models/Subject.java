package duan.example.faltamuito.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Subject extends RealmObject{

    @PrimaryKey
    private String name;
    private String category;
    private String half_night;
    private String half_integral;
    private boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHalf_night() {
        return half_night;
    }

    public void setHalf_night(String half_night) {
        this.half_night = half_night;
    }

    public String getHalf_integral() {
        return half_integral;
    }

    public void setHalf_integral(String half_integral) {
        this.half_integral = half_integral;
    }
}
