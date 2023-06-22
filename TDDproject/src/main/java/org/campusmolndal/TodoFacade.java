package org.campusmolndal;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class TodoFacade {
    private TodoRepository todoRepository;

    public TodoFacade() {

        //Skapa en anslutning till MongoDB-databasen med standardinställningar
        String connectionString = "mongodb://localhost:27017";
        String databaseName = "mydatabase";
        String collectionName = "todo";

        //Skapa en anslutning till MongoDB
        MongoClient mongoClient = MongoClients.create(connectionString);

        //Hämta referens till databasen och samlingen
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> todoCollection = database.getCollection(collectionName);

        //Skapa en TodoRepository med samlingen
        todoRepository = new TodoRepository(todoCollection);
    }

    //Lägger till en Todo i databasen
    public void addTodo(Todo todo) {
        todoRepository.addTodo(todo);
    }

    //Hämtar alla Todos från databasen
    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    //Hämtar en Todo med en specifik id från databasen
    public Todo getTodoById(String id) {
        return todoRepository.getTodoById(id);
    }

    //Uppdaterar texten för en Todo med en specifik id
    public void updateTodoText(String id, String newText) {
        todoRepository.updateTodoText(id, newText);
    }

    //Tar bort en Todo med en specifik id från databasen
    public void deleteTodoById(String id) {
       todoRepository.deleteTodoById(id);
       }

    //Uppdaterar statusen (done)för en Todo med en specifik id
    public void updateTodoDoneStatus(String id, boolean done){
        todoRepository.updateTodoDoneStatus(id, done);
    }
}
