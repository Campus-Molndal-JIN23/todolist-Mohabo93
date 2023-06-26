package org.campusmolndal;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class TodoFacade {
    private final TodoDb todoDb;

    public TodoFacade() {
        //Skapa en TodoDb med samlingen
        this.todoDb = new TodoDb();
    }

    public TodoFacade(TodoDb todoDb) {
        this.todoDb = todoDb;
    }

    //Lägger till en Todo i databasen
    public void addTodo(Todo todo) {
        todoDb.addTodo(todo);
    }

    //Hämtar alla Todos från databasen
    public List<Todo> getAllTodos() {
        return todoDb.getAllTodos();
    }

    //Hämtar en Todo med en specifik id från databasen
    public Todo getTodoById(String id) {
        return todoDb.getTodoById(id);
    }

    //Uppdaterar texten för en Todo med en specifik id
    public void updateTodoText(String id, String newText) {
        todoDb.updateTodoText(id, newText);
    }

    //Tar bort en Todo med en specifik id från databasen
    public void deleteTodoById(String id) {
        todoDb.deleteTodoById(id);
    }

    //Uppdaterar statusen (done)för en Todo med en specifik id
    public void updateTodoDoneStatus(String id, boolean done) {
        todoDb.updateTodoDoneStatus(id, done);
    }

    public void setTodoDb(TodoDb todoDb) {
    }
}
