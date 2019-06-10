package nl.putoet.bookmanager;

import org.apache.commons.cli.*;

public class AppCommandLine {
    private static final Option SYSTEM = new Option("s", "system", false,"Check hidden folders");
    private static final Option ALL = new Option("a", "all", false,"All titles formats");
    private static final Option DUPLICATES = new Option("d", "duplicate", false,"Titles with duplicates");
    private static final Option MISSING = new Option("m", "missing", false,"Titles with missing formats");
    private static final Option VERBOSE = new Option("v", "verbose", false,"Verbose logging");
    private static final Option RECURSIVE = new Option("r", "recursive", false,"Search sub folders");
    private static final Option HELP = new Option("h", "help", false,"Command details");

    private final Options options;
    private final CommandLine commandLine;

    private AppCommandLine(final Options options, final CommandLine commandLine) {
        this.options = options;
        this.commandLine = commandLine;
    }

    String[] getCommands() {
        return commandLine.getArgs();
    }

    boolean searchSystemFolders() {
        return commandLine.hasOption(SYSTEM.getOpt());
    }

    boolean allFormats() {
        return commandLine.hasOption(ALL.getOpt());
    }

    boolean duplicateFormats() {
        return commandLine.hasOption(DUPLICATES.getOpt());
    }

    boolean missingFormats() {
        return commandLine.hasOption(MISSING.getOpt());
    }

    boolean verbose() {
        return commandLine.hasOption(VERBOSE.getOpt());
    }

    boolean recursive() {
        return commandLine.hasOption(RECURSIVE.getOpt());
    }

    boolean help() {
        return commandLine.hasOption(HELP.getOpt());
    }

    void showHelp() {
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
        options.addOption(ALL);
        options.addOption(MISSING);
        options.addOption(DUPLICATES);
        options.addOption(VERBOSE);
        options.addOption(RECURSIVE);
        options.addOption(HELP);

        return options;
    }

    public static void help(final Options options) {
        final String header = "Analyse ebook files in a folder structure ...\n\n";
        final String footer = "\nPlease report issues at email z669016@gmail.nl";

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ebooks {list <folder>}", header, options, footer, true);
    }

}
