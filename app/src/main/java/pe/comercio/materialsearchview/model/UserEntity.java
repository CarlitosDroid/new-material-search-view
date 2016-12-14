package pe.comercio.materialsearchview.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Carlos Vargas on 11/25/16.
 * CarlitosDroid
 */

public class UserEntity implements Comparable<UserEntity>{

    private String name;
    private String dateTime;

    public UserEntity(){

    }

    public UserEntity(String name, String dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int compareTo(UserEntity perro) {
        return this.getDateTime().compareTo(perro.getDateTime());
    }

//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    @Override
//    public int compareTo(UserEntity perro) {
//        try {
//            return sdf.parse(this.getDateTime()).compareTo(sdf.parse(perro.getDateTime()));
//        } catch (ParseException e) {
//            throw new IllegalArgumentException(e);
//        }
//    }
}
