package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;
import ru.netology.repository.IssueRepository;
import java.util.List;
import java.util.Set;

public class IssueManager {
    private final IssueRepository repository = new IssueRepository();

    public IssueManager() {
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> findOpened() {
        return repository.findOpened();

    }

    public List<Issue> findClosed() {
        return repository.findClosed();
    }

    public List<Issue> findByAuthor(String author) {
        List<Issue> list = repository.findByAuthor(author);
        if (list.size() == 0) {
            throw new NotFoundException("Element with: author: " + author + " not found"
            );
        }
        return list;
    }

    public List<Issue> findByLabel(Set<String> label) {
        List<Issue> list = repository.findByLabel(label);
        if (list.size() == 0) {
            throw new NotFoundException("Element with: label: " + label + " not found"
            );
        }
        return list;
    }

    public List<Issue> findByAssignee(Set<String> assignee) {
        List<Issue> list = repository.findByAssignee(assignee);
        if (list.size() == 0) {
            throw new NotFoundException("Element with: assignee: " + assignee + " not found"
            );
        }
        return list;
    }

    public Issue getById(int id) {
        Issue issue = repository.getById(id);
        if (issue == null) {
            throw new NotFoundException("Issue not found");
        }

        return issue;
    }

    public void openById(int id) {
        repository.openById(id);
    }

    public void closeById(int id) {
        repository.closeById(id);
    }


}
