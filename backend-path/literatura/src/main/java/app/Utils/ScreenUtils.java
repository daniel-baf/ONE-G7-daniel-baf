package app.Utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ScreenUtils {
    public static void cleanScreen(String message) {
        return;
        // try {
        //     // Check the operating system
        //     if (System.getProperty("os.name").contains("Windows")) {
        //         new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //     } else {
        //         System.out.print("\033[H\033[2J");
        //         System.out.flush();
        //     }
        // } catch (Exception e) {
        //     log.error("Error al limpiar la pantalla: {}", e.getMessage());
        // }

        // if (message != null && !message.isEmpty()) {
        //     log.info("\n-------------{}-------------", message);
        // }
    }
}
