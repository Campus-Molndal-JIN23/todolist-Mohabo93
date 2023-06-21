package org.campusmolndal;


public class Application {
    public static void main(String[] args) {
        TodoFacade todoFacade = new TodoFacade();
        Meny meny = new Meny(todoFacade);
        meny.start();
    }
}
