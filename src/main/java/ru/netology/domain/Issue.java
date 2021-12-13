package ru.netology.domain;

import java.util.Objects;
import java.util.Set;

public class Issue {
    private int id;
    private boolean isOpened;
    private String author;
    private Set<String> assignee;
    private Set<String> label;

    public Issue(int id, boolean isOpened, String author, Set<String> assignee, Set<String> label) {
        this.id = id;
        this.isOpened = isOpened;
        this.author = author;
        this.assignee = assignee;
        this.label = label;
    }

    public Issue() {
    }

    public int getId() {
        return id;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public String getAuthor() {
        return author;
    }

    public Set<String> getAssignee() {
        return assignee;
    }

    public Set<String> getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                '}';
    }

}
