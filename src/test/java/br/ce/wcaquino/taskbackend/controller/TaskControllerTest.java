package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doNotSaveTaskWithoutDescription() {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        try {
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (Exception ex) {
            Assert.assertEquals("Fill the task description", ex.getMessage());
        }
    }

    @Test
    public void doNotSaveTaskWithoutDate() {
        Task todo = new Task();
        todo.setTask("Go to gym");
        try {
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (Exception ex) {
            Assert.assertEquals("Fill the due date", ex.getMessage());
        }
    }

    @Test
    public void doNotSavetaskWithoutPastDate() {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now().minusDays(1));
        todo.setTask("Go to gym");
        controller = new TaskController();
        try {
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto");
        } catch (Exception ex) {
            Assert.assertEquals("Due date must not be in past", ex.getMessage());
        }
    }

    @Test
    public void save() throws ValidationException {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        todo.setTask("Go to gym");
        controller.save(todo);

        Mockito.verify(taskRepo).save(todo);
    }
}