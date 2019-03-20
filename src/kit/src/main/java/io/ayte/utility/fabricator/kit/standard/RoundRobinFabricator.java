package io.ayte.utility.fabricator.kit.standard;

import io.ayte.utility.fabricator.api.Fabricator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Iterator;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class RoundRobinFabricator<T, E extends Throwable> implements Fabricator<T, E> {
    private final Collection<? extends T> source;

    private Iterator<? extends T> iterator;

    @Override
    public T fabricate() {
        if (iterator == null || !iterator.hasNext()) {
            iterator = source.iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }

    public static <T, E extends Throwable> Fabricator<T, E> create(@NonNull Collection<? extends T> source) {
        switch (source.size()) {
            case 0:
                return EmptyFabricator.create();
            case 1:
                return ConstantFabricator.create(source.iterator().next());
            default:
                return new RoundRobinFabricator<>(source);
        }
    }
}
