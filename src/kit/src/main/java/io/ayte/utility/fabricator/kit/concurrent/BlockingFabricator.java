package io.ayte.utility.fabricator.kit.concurrent;

import io.ayte.utility.fabricator.api.Fabricator;
import io.ayte.utility.fabricator.api.ThreadSafeFabricator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockingFabricator<T, E extends Throwable> implements ThreadSafeFabricator<T, E> {
    private final Fabricator<T, E> delegate;

    @Override
    public synchronized T fabricate() throws E {
        return delegate.fabricate();
    }

    public static <T, E extends Throwable> ThreadSafeFabricator<T, E> create(Fabricator<T, E> delegate) {
        if (delegate instanceof ThreadSafeFabricator) {
            return ((ThreadSafeFabricator<T, E>) delegate);
        }
        return new BlockingFabricator<>(delegate);
    }
}
