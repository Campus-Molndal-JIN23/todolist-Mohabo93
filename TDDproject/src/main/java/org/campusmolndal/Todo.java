package org.campusmolndal;

import java.util.Objects;
import java.util.UUID;

public class Todo {
    private String id;
    private String text;
    private boolean done;
    private String assignedTo;

    public Todo() {
        //Generera ett slumpmässigt ID
        this.id = UUID.randomUUID().toString();

    }
    public Todo (String text, boolean done, String assignedTo) {
        this();
        this.text = text;
        this.done = done;
        this.assignedTo = assignedTo;
    }

    //Getters och Setters för dem olika attributen
    public String getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //toString-metod för att ge en strängrepresentation av Todo-objektet
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id + '\'' +
                ", text='" + text + '\'' +
                ", done=" + done +
                ", assignedTo='" + assignedTo + '\'' +
                '}';
    }

    //equals-metod för att jämföra två Todo-objekt för likhet baserat på deras attribut
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return done == todo.done &&
                Objects.equals(id, todo.id) &&
                Objects.equals(text, todo.text) &&
                Objects.equals(assignedTo, todo.assignedTo);
    }

    //hashCode-metod för att generera en hashkod för Todo-objektet
    @Override
    public int hashCode(){
        return Objects.hash(id, text, done, assignedTo);
    }
}
