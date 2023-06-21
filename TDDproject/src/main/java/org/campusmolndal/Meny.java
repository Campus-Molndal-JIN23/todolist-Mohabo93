package org.campusmolndal;

import java.util.List;
import java.util.Scanner;

public class Meny {
    private TodoFacade todoFacade;
    private Scanner scanner;

    public Meny(TodoFacade todoFacade){
        this.todoFacade = todoFacade;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choise;
        do {
            displayMeny();
            choise = getUserChoise();
            handleUserChoise(choise);
        } while (choise != 0);
    }

    //Huvudmeny
    private void displayMeny() {
        System.out.println("============ TODO MENY ============");
        System.out.println("1. Lägg till en Todo");
        System.out.println("2. Visa alla Todos");
        System.out.println("3. Visa en specifik Todo");
        System.out.println("4. Uppdatera en Todos text");
        System.out.println("5. Uppdatera en Todos status (done)");
        System.out.println("0. Avsluta");
        System.out.println("===================================");
    }

    //Läser användarens val från input
    private int getUserChoise() {
        System.out.println("Gör ett val: ");
        return scanner.nextInt();
    }

    //Hantera användarens val och anropar motsvarande metoder
    private void handleUserChoise(int choise){
        switch (choise) {
            case 1:
                addTodo();
                break;
            case 2:
                viewAllTodos();
                break;
            case 3:
                viewSpecificTodo();
                break;
            case 4:
                updateTodoText();
                break;
            case 5:
                updateTodoDoneStatus();
                break;
            case 0:
                System.out.println("Avslutar applikationen..");
                break;
            default:
                System.out.println("Ogiltigt val. Försök igen.");
        }
    }

    //Lägger till en ny Todo baserat på användarinmatning
    private void addTodo() {
        scanner.nextLine();
        System.out.println("Skriv in Todo-meddelandet");
        String text = scanner.nextLine();
        System.out.println("Skriv in tilldelad person: ");
        String assignedTo = scanner.nextLine();

        Todo todo = new Todo();
        todo.setText(text);
        todo.setAssignedTo(assignedTo);

        todoFacade.addTodo(todo);

        System.out.println("Todo tillagd!");
    }

    //Visar alla befintliga Todos
    private void viewAllTodos() {
        List<Todo> todos = todoFacade.getAllTodos();
        if (todos.isEmpty()) {
            System.out.println("Inga Todo hittades.");
        } else {
            System.out.println("======= ALLA TODOS =======");
            for (Todo todo : todos) {
                System.out.println(todo);
            }
            System.out.println("===========================");
            }
        }

        //Visar en specifik Todo baserat på användarinmatning av TodoID
        private void viewSpecificTodo() {
            scanner.nextLine();
            System.out.println("Skriv in TodoID: ");
            String id = scanner.nextLine();

            Todo todo = todoFacade.getTodoById(id);
            if (todo == null) {
                System.out.println("Todo saknas.");
            } else {
                System.out.println("====== TODO INFO ======");
                System.out.println(todo);
                System.out.println("=======================");
            }
        }

        //Uppdaterar en Todos text baserat på användarinmatning av TodoID och ny text
        private void updateTodoText() {
        scanner.nextLine();
            System.out.println("Skriv in Todo ID: ");
            String id = scanner.nextLine();
            System.out.println("Skriv ny Todo-text: ");
            String newText = scanner.nextLine();

            todoFacade.updateTodoText(id, newText);

            System.out.println("Ny Todo-text uppdaterad!");

        }

        //Uppdaterar en Todos status (done) baserat på användarinmatning av TodoID och ny status
        private void updateTodoDoneStatus() {
        scanner.nextLine();
            System.out.println("Skriv in Todo ID: ");
            String id = scanner.nextLine();
            System.out.println("Skriv in nytt done-status (true/false): ");
            boolean done = scanner.nextBoolean();

            todoFacade.updateTodoDoneStatus(id, done);

            System.out.println("Uppdaterad status för Todo-done!");
    }
}
