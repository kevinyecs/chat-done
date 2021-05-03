package hu.alkfelj.model;

import javafx.beans.property.*;

public class ChatRoom {
    private StringProperty name = new SimpleStringProperty(this,"nev");
    private StringProperty category = new SimpleStringProperty(this,"kategoria");
    private IntegerProperty id = new SimpleIntegerProperty(this,"id");
    private StringProperty rule = new SimpleStringProperty(this, "szabaly");



    public String getRule() {
        return rule.get();
    }

    public StringProperty ruleProperty() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule.set(rule);
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

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

}
