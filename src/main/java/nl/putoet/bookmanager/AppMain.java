package nl.putoet.bookmanager;

import nl.putoet.ebooks.EBookTitleList;

import java.nio.file.Paths;

public class AppMain {
    public static void main(String[] args) {
        System.out.println("[EBooks version 0.2]\n");

        final AppCommandLine commandLine = AppCommandLine.getInstance(args);
        final String commands[] = commandLine.getCommands();

        if (commands.length > 0) {
            switch (commands[0].toUpperCase()) {
                case "LIST":
                    final App app = new App(commandLine);
                    final EBookTitleList list = app.list(Paths.get(commands[1]));
                    app.print(list);
                    break;

                default:
                    System.out.println("Unknown command '" + commands[0] + "'");
            }
        }
    }
}
