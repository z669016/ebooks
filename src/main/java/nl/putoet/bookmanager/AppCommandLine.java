package nl.putoet.bookmanager;

import org.apache.commons.cli.*;

public class AppCommandLine {
    private static final Option SYSTEM = new Option("s", "system", false,"Check hidden folders");
    private static final Option MISSING = new Option("m", "missing", false,"Print missing formats");

    private final Options options;
    private final CommandLine commandLine;

    private AppCommandLine(final Options options, final CommandLine commandLine) {
        this.options = options;
        this.commandLine = commandLine;
    }

    public String[] getCommands() {
        return commandLine.getArgs();
    }

    public boolean searchSystemFolders() {
        return commandLine.hasOption(SYSTEM.getOpt());
    }

    public boolean missingFormats() {
        return commandLine.hasOption(MISSING.getOpt());
    }

    public void help() {
        help(options);
    }

    public static AppCommandLine getInstance(final String[] args) {
        final CommandLineParser parser = new DefaultParser();
        final Options options = setupOptions();

        try {
            return new AppCommandLine(options, parser.parse(options, args));
        } catch (ParseException exc) {
            System.out.println("Invalid arguments ... (" + exc.getMessage() + ")");
        }
        help(options);
        throw new IllegalArgumentException();
    }

    private static Options setupOptions() {
        final Options options = new Options();

        options.addOption(SYSTEM);
        options.addOption(MISSING);

        return options;
    }

    public static void help(final Options options) {
        final String header = "Analyse ebook files in a folder structure ...\n\n";
        final String footer = "\nPlease report issues at email rene@putoet.nl";

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ebooks {list <folder>}", header, options, footer, true);
    }

}
