package br.com.manoel.amaro.safepasswords.mobile.domain;

import br.com.manoel.amaro.safepasswords.mobile.annotation.Column;
import br.com.manoel.amaro.safepasswords.mobile.annotation.Entity;

/**
 * Created by manoel on 26/09/14.
 */
@Entity
public class Password implements PersistableEntity {

    public static final String[] COLUMN_NAMES = {"id", "title", "description", "password"};

    @Column
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String password;

    public Password() {
    }

    public Password(Integer id, String title, String description, String password) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Dao extends AbstractDao<Password> {

        public Dao() {
            super(Password.class);
        }
    }
}
