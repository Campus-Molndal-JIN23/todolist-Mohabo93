package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


public class TodoFacadeTest {
    @Mock
    private TodoRepository todoRepository;
    private TodoFacade todoFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        todoFacade = new TodoFacade(todoRepository);
    }

    @Test
    void addTodo_shouldAddTodoToRepository() {
        // Arrange
        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoFacade todoFacade = new TodoFacade(todoRepository);
        todoFacade.setTodoRepository(todoRepository);
        Todo todo = new Todo();

        // Mocka beteendet för todoRepository.addTodo()
        doNothing().when(todoRepository).addTodo(Mockito.any(Todo.class));

        // Act
        todoFacade.addTodo(todo);

        // Assert
        verify(todoRepository, times(1)).addTodo(todo);
    }

    @Test
    void GetAllTodos() {
    }

    @Test
    void getTodoById() {
    }

    @Test
    void updateTodoText() {
    }

    @Test
    void deleteTodoById() {
    }

    @Test
    void updateTodoDoneStatus() {
    }
}