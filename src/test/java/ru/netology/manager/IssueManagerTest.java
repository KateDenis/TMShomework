package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    IssueManager manager = new IssueManager();
    Issue issue1 = new Issue(1, true, "Lena", Set.of("Vasya"), Set.of("Kotlin"));
    Issue issue2 = new Issue(2, true, "Lena", Set.of("Vasya"), Set.of("Groovy"));
    Issue issue3 = new Issue(3, false, "Vasya", Set.of("Misha"), Set.of("Status:new"));
    Issue issue4 = new Issue(4, false, "Vadim", Set.of("Lena"), Set.of("Status:new"));
    Issue issue5 = new Issue(5, true, "Vadim", Set.of("Vasya"), Set.of("Jupiter"));
    Issue issue6 = new Issue(6, false, "Vadim", Set.of("Misha"), Set.of("Status:new"));

    @BeforeEach
    public void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        manager.add(issue6);
    }

    @Test
    void shouldFindOpened() {
        assertEquals(List.of(issue1, issue2, issue5), manager.findOpened());
    }

    @Test
    void shouldFindClosed() {
        assertEquals(List.of(issue3, issue4, issue6), manager.findClosed());
    }

    @Test
    void shouldFindByAuthorMultipleIssues() {
        List<Issue> expected = List.of(issue4, issue5, issue6);
        List<Issue> actual = manager.findByAuthor("Vadim");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAuthorOneIssue() {
        List<Issue> expected = List.of(issue3);
        List<Issue> actual = manager.findByAuthor("Vasya");
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAuthorNotFound() {
        assertThrows(NotFoundException.class, () -> {
            manager.findByAuthor("Olya");
        });
    }

    @Test
    void shouldFindByLabelOneIssue() {
        List<Issue> expected = List.of(issue5);
        List<Issue> actual = manager.findByLabel(Set.of("Jupiter"));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByLabelMultipleIssues() {
        List<Issue> expected = List.of(issue3, issue4, issue6);
        List<Issue> actual = manager.findByLabel(Set.of("Status:new"));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByLabelNotFoundIssues() {
        assertThrows(NotFoundException.class, () -> {
            manager.findByLabel(Set.of("Status:blocked"));
        });
    }

    @Test
    void shouldFindByAssigneeOneIssue() {
        List<Issue> expected = List.of(issue4);
        List<Issue> actual = manager.findByAssignee(Set.of("Lena"));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAssigneeMultipleIssues() {
        List<Issue> expected = List.of(issue1, issue2, issue5);
        List<Issue> actual = manager.findByAssignee(Set.of("Vasya"));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByAssigneeNotFoundIssues() {
        assertThrows(NotFoundException.class, () -> {
            manager.findByAssignee(Set.of("Olya"));
        });
    }

    @Test
    void shouldOpenById() {
        manager.openById(3);
        assertTrue(manager.getById(3).isOpened());
    }

    @Test
    void shouldCloseById() {
        manager.closeById(2);
        assertFalse(manager.getById(2).isOpened());
    }

    @Test
    void shouldGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            manager.getById(9);
        });
    }
}