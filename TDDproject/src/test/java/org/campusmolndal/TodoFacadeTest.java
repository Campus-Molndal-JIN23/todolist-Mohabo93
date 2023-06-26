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
    private TodoDb todoDb;
    private TodoFacade todoFacade;

    @BeforeEach
    void setUp() {
        todoDb = Mockito.mock(TodoDb.class);
       todoFacade = new TodoFacade(todoDb);
    }

    @Test
    void addTodo_shouldAddTodoToRepository() {
        // Arrange
        TodoDb todoDb = mock(TodoDb.class);
        TodoFacade todoFacade = new TodoFacade(todoDb);
        todoFacade.setTodoDb(todoDb);
        Todo todo = new Todo();

        doNothing().when(todoDb).addTodo(Mockito.any(Todo.class));

        // Act
        todoFacade.addTodo(todo);

        // Assert
        verify(todoDb, times(1)).addTodo(todo);
    }

    @Test
    void getAllTodos_shouldReturnAllTodos() {
        //Arrange
        List<Todo> todos = List.of(
                new Todo("1","Todo 1", false, "User 1"),
                new Todo("2","Todo 2", true, "User 2")
        );
        when(todoDb.getAllTodos()).thenReturn(todos);

        //Act
        List<Todo> result = todoFacade.getAllTodos();

        //Assert
        assertEquals(todos, result);
        verify(todoDb, times(1)).getAllTodos();
    }

    @Test
    void getTodoById_shouldReturnCorrectTodo() {
        //Arrange
        String id = "1";
        Todo todo = new Todo("1","Todo 1", false, "User 1");
        when(todoDb.getTodoById(id)).thenReturn(todo);

        //Act
        Todo result = todoFacade.getTodoById(id);

        //Assert
        assertEquals(todo, result);
        verify(todoDb, times(1)).getTodoById(id);
    }

    @Test
    void updateTodoText_shouldUpdateTodoText() {
        //Arrange
        String id = "1";
        String newText = "Updated Todo";

        //Act
        todoFacade.updateTodoText(id, newText);

        //Assert
        verify(todoDb,times(1)).updateTodoText(id, newText);

    }

    @Test
    void deleteTodoById_shouldDeleteTodo() {
        //Arrange
        String id = "1";
        Todo todo = new Todo("1","Todo 1", false, "User 1");
        when(todoDb.getTodoById(id)).thenReturn(todo);

        //Act
        todoFacade.deleteTodoById(id);

        //Assert
        verify(todoDb, times(1)).deleteTodoById(id);
    }

    @Test
    void updateTodoDoneStatus_shouldUpdateTodoDoneStatus() {
        //Arrange
        String id = "1";
        boolean done = true;

        TodoDb todoDb = mock(TodoDb.class);
        TodoFacade todoFacade = new TodoFacade(todoDb);

        //Act
        todoFacade.updateTodoDoneStatus(id, done);

        //Assert
        verify(todoDb, times(1)).updateTodoDoneStatus(id, done);
    }
}