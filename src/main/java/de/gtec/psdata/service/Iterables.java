package de.gtec.psdata.service;

import de.gtec.psdata.storage.entity.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Iterables {

    public static <T> List<T> toList(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        if (!it.hasNext())
            return Collections.emptyList();

        return Stream.iterate(it.next(), hasNext -> it.hasNext(), next -> it.next())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Frame[] toFrameArray(Iterable<Frame> iterable) {
        return toList(iterable).toArray(Frame[]::new);
    }

}
