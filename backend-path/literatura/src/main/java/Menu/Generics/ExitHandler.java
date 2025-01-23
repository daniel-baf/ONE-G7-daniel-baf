package Menu.Generics;

import Menu.MenuHandler;

/**
 * The ExitHandler class is responsible for handling an exit option in a menu-based application.
 * It implements the behavior to terminate the program when the specified option is selected.
 * If the current handler does not manage the given option, it delegates the processing to the next handler in the chain.
 */
public class ExitHandler implements MenuHandler {

    private MenuHandler next;

    /**
     * Handles a menu option. If the specified option matches the pre-defined exit option
     * (value 0), it terminates the application. If the option does not match and a next
     * handler exists in the chain, the option is delegated to the next handler.
     *
     * @param option The menu option to be processed.
     */
    @Override
    public void handleOption(int option) {
        if (option == 0) {
            System.out.println("¡Hasta luego!");
            System.exit(0);  // Terminar la ejecución del programa
        } else if (next != null) {
            next.handleOption(option);
        }
    }

    @Override
    public void setNext(MenuHandler next) {
        this.next = next;
    }
}