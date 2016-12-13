package pe.comercio.materialsearchview.model;

/**
 * Created by Carlos Vargas on 11/25/16.
 * CarlitosDroid
 */

public class UserEntity {

    private String name;
    private String time;

    public UserEntity(String name) {
        this.name = name;
    }

    public UserEntity(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
