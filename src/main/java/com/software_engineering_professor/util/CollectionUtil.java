package com.software_engineering_professor.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class CollectionUtil {
    public static <T extends Cloneable<T>> Collection<T> deepCopy(Collection<T> in) {
        Collection<T> out = new ArrayList<>();

        for(T t : in) {
            out.add(t.clone());
        }

        return out;
    }
}
