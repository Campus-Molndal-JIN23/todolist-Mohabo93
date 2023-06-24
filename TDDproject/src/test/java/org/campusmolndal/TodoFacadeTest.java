package org.campusmolndal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TodoFacadeTest {
    @Mock
    private TodoRepository todoRepository;
    private TodoFacade todoFacade;

    @BeforeEach
    void setUp() {
       todoRepository = Mockito.mock(TodoRepository.class);
       todoFacade = new TodoFacade(todoRepository);
    }

    @Test
    void addTodo_shouldAddTodoToRepository() {
        // Arrange
        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoFacade todoFacade = new TodoFacade(todoRepository);
        todoFacade.setTodoRepository(todoRepository);
        Todo todo = new Todo();

        doNothing().when(todoRepository).addTodo(Mockito.any(Todo.class));

        // Act
        todoFacade.addTodo(todo);

        // Assert
        verify(todoRepository, times(1)).addTodo(todo);
    }

    @Test
    void getAllTodos_shouldReturnAllTodos() {
        //Arrange
        List<Todo> todos = List.of(
                new Todo("Todo 1", false, "User 1"),
                new Todo("Todo 2", true, "User 2")
        );
        when(todoRepository.getAllTodos()).thenReturn(todos);

        //Act
        List<Todo> result = todoFacade.getAllTodos();

        //Assert
        assertEquals(todos, result);
        verify(todoRepository, times(1)).getAllTodos();
    }

    @Test
    void getTodoById_shouldReturnCorrectTodo() {
        //Arrange
        String id = "1";
        Todo todo = new Todo("Todo 1", false, "User 1");
        when(todoRepository.getTodoById(id)).thenReturn(todo);

        //Act
        Todo result = todoFacade.getTodoById(id);

        //Assert
        assertEquals(todo, result);
        verify(todoRepository, times(1)).getTodoById(id);
    }

    @Test
    void updateTodoText_shouldUpdateTodoText() {
        //Arrange
        String id = "1";
        String newText = "Updated Todo";
        TodoFacade todoFacade = new TodoFacade(todoRepository);

        //Act
        todoFacade.updateTodoText(id, newText);

    }

    @Test
    void deleteTodoById_shouldDeleteTodo() {
        //Arrange
        String id = "1";
        Todo todo = new Todo("Todo 1", false, "User 1");
        when(todoRepository.getTodoById(id)).thenReturn(todo);

        //Act
        todoFacade.deleteTodoById(id);

        //Assert
        verify(todoRepository, times(1)).deleteTodoById(id);
    }

    @Test
    void updateTodoDoneStatus_shouldUpdateTodoDoneStatus() {
        //Arrange
        String id = "1";
        boolean done = true;

        TodoRepository todoRepository = mock(TodoRepository.class);
        TodoFacade todoFacade = new TodoFacade(todoRepository);

        //Act
        todoFacade.updateTodoDoneStatus(id, done);

        //Assert
        verify(todoRepository, times(1)).updateTodoDoneStatus(id, done);
    }
}