package start;

import controller.SearchController;

/*
 * Entry point - just initializes and hands control to the menu.
 * Clean and simple, same as ATM Main.java.
 */
public class Main {
    public static void main(String[] args) {
        SearchController controller = new SearchController();
        controller.initialize("documents");
        controller.showMenu();
    }
}
