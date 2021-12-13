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
        issues.removeIf(el -> !Objects.equals(el.getAuthor(), author));
        return issues;
    }

    public List<Issue> findByLabel(Set<String> label) {
        issues.removeIf(el -> !Objects.equals(el.getLabel(), label));
        return issues;
    }

    public List<Issue> findByAssignee(Set<String> assignee) {
        issues.removeIf(el -> !Objects.equals(el.getAssignee(), assignee));
        return issues;
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