package nl.putoet.bookmanager;

import nl.putoet.ebooks.EBookTitleList;

import java.nio.file.Paths;

public class AppMain {
    private static final int OK = 0;
    private static final int HELP = 1;
    private static final int ERROR = 2;

    public static void main(String[] args) {
        System.out.println("[EBooks version 0.3]\n");

        final AppCommandLine commandLine = AppCommandLine.getInstance(args);
        final String commands[] = commandLine.getCommands();

        if (commandLine.help()) {
            commandLine.showHelp();
            System.exit(HELP);
        }

        if (commands.length > 0) {
            switch (commands[0].toUpperCase()) {
                case "LIST":
                    final App app = new App(commandLine);
                    final EBookTitleList list = app.list(Paths.get(commands[1]));
                    app.print(list);
                    System.exit(OK);

                default:
                    System.out.println("Unknown command '" + commands[0] + "'");
            }
        }

        System.exit(ERROR);
    }
}
