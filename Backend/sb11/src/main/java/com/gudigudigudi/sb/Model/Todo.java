package com.gudigudigudi.sb.Model;

public class Todo {
    private String id;
    private String desc;
    private boolean completed;

    public Todo(String id, String desc, boolean completed) {
        this.id = id;
        this.desc = desc;
        this.completed = completed;
    }

    public Todo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
