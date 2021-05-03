package hu.alkfelj.model;

import javafx.beans.property.*;

public class User {

    private StringProperty name = new SimpleStringProperty(this,"name");
    private StringProperty email = new SimpleStringProperty(this,"email");
    private StringProperty age = new SimpleStringProperty(this,"age");
    private StringProperty sex = new SimpleStringProperty(this,"sex");
    private StringProperty password = new SimpleStringProperty(this,"password");
    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private StringProperty intrest = new SimpleStringProperty(this, "intrest");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }


    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getIntrest() {
        return intrest.get();
    }

    public StringProperty intrestProperty() {
        return intrest;
    }

    public void setIntrest(String intrest) {
        this.intrest.set(intrest);
    }


}
