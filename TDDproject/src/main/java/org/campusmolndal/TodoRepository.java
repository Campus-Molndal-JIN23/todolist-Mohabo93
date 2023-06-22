package org.campusmolndal;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private MongoCollection<Document> todoCollection;

    public TodoRepository(MongoCollection<Document> todoCollection){
        this.todoCollection = todoCollection;
    }

    //Lägger till en Todo i samlingen - Skapar ett dokument från Todo-objektet - Infogar dokumentet i samlingen
    public void addTodo(Todo todo) {

        Document todoDocument = new Document("id",todo.getId())
                .append("text", todo.getText())
                .append("done", todo.isDone())
                .append("assignedTo", todo.getAssignedTo());

        todoCollection.insertOne(todoDocument);
    }

    //Hämtar alla Todos från samlingen
    public List<Todo>  getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        for (Document document : todoCollection.find()) {
            Todo todo = createTodoFromDocument(document);
            todos.add(todo);
        }
        return todos;
    }

    //Hämtar en Todo med en specifik id från samlingen
    public Todo getTodoById(String id) {
        Document document = todoCollection.find(Filters.eq("id", id)).first();
        if (document != null) {
            return createTodoFromDocument(document);
        }
        return null;
    }

    //Uppdaterar texten för en Todo med en specifik id
    public void updateTodoText(String id, String newText) {
        UpdateResult result = todoCollection.updateOne(Filters.eq("id", id), Updates.set("text", newText));
        if (result.getModifiedCount() == 0) {
            throw new IllegalArgumentException("Todo med ID " + id + " existerar inte.");
        }
    }

    //Tar bort Todo med specifik id från databasen
    public void deleteTodoById(String id) {
        DeleteResult result = todoCollection.deleteOne(Filters.eq("id", id));
        if (result.getDeletedCount() == 0) {
            throw new IllegalArgumentException("Todo med ID " + id + " existerar inte.");
        }
        System.out.println("Todo med ID " + id + " har tagits bort.");
    }

    //Uppdatera statusen (done) för en Todo med en specifik id
    public void updateTodoDoneStatus(String id, boolean done) {
        todoCollection.updateOne(Filters.eq("id", id), Updates.set("done", done));
    }

    //Hjälpmetod för att skapa Todo-objekt från dokument
    private Todo createTodoFromDocument(Document document) {
        return new Todo(
                document.getString("text"),
                document.getBoolean("done"),
                document.getString("assignedTo"));
    }
}
