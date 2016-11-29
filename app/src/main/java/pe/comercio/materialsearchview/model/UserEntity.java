package pe.comercio.materialsearchview.model;

/**
 * Created by Carlos Vargas on 11/25/16.
 * CarlitosDroid
 */

public class UserEntity {

    private String name;

    public UserEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
