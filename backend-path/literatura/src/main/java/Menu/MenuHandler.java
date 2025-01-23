package Menu;

/**
 * The MenuHandler interface provides a mechanism for handling menu options
 * and linking menu handlers to create a chain of responsibility.
 * Implementations of this interface can handle specific menu options
 * and delegate unhandled options to the next handler in the chain.
 */
public interface MenuHandler {
    void handleOption(int option);
    void setNext(MenuHandler next);
}