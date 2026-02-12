package util;

import model.MediaContent;

import java.util.List;
import java.util.stream.Collectors;

public class SortUtil {
    // filter
    public static <T extends MediaContent> List<T> filterByYear(List<T> list, int year) {
        return list.stream().filter(m -> m.getReleaseYear() >= year).collect(Collectors.toList());
    }

    // sort
    public static <T extends MediaContent> void sortByTitle(List<T> list) {
        list.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }
}
