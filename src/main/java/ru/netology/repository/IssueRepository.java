package ru.netology.repository;

import ru.netology.domain.Issue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class IssueRepository {
    public List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> findOpened() {
        issues.removeIf(el -> !el.isOpened());
        return issues;
    }

    public List<Issue> findClosed() {
        issues.removeIf(Issue::isOpened);
        return issues;
    }

    public List<Issue> findByAuthor(String author) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : this.issues) {
            if (author.equals(issue.getAuthor())) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findByLabel(Set<String> label) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : this.issues) {
            if (label.equals(issue.getLabel())) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findByAssignee(Set<String> assignee) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : this.issues) {
            if (assignee.equals(issue.getAssignee())) {
                result.add(issue);
            }
        }
        return result;
    }

    public Issue getById(int id) {
        for (Issue issue : this.issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public void openById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setOpened(true);
            }
        }
    }

    public void closeById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setOpened(false);
            }
        }
    }
}