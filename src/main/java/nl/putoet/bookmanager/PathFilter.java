package nl.putoet.bookmanager;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PathFilter implements Predicate<Path> {
    protected List<Predicate<Path>> predicates = new ArrayList<>();

    public PathFilter add(final Predicate<Path> predicate) {
        predicates.add(predicate);
        return this;
    }

    @Override
    public boolean test(Path path) {
        for (Predicate<Path> predicate : predicates) {
            if (!predicate.test(path))
                return false;
        }
        return true;
    }
}
