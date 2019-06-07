package io.ayte.utility.fabricator.kit.delegate;

import io.ayte.utility.fabricator.api.Fabricator;
import io.ayte.utility.fabricator.api.StableFabricator;
import io.ayte.utility.fabricator.api.ThreadSafeFabricator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @param <T> Return type.
 * @param <E> Error type.
 *
 * @since 0.1.1
 */
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OneOffFabricator<T, E extends Throwable> implements StableFabricator<T, E>, ThreadSafeFabricator<T, E> {
    private final Fabricator<T, E> delegate;

    private State<T, E> state;

    @SuppressWarnings({"unchecked", "squid:S1181"})
    @Override
    public T fabricate() throws E {
        if (state != null) {
            return state.retrieve();
        }
        synchronized (this) {
            if (state != null) {
                return state.retrieve();
            }
            try {
                state = new State<>(delegate.fabricate(), null);
            } catch (Throwable exception) {
                state = new State<>(null, (E) exception);
            }
        }
        return state.retrieve();
    }

    @RequiredArgsConstructor
    private static class State<T, E extends Throwable> {
        private final T value;
        private final E error;

        public T retrieve() throws E {
            if (error != null) {
                throw error;
            }
            return value;
        }
    }

    public static <T, E extends Throwable> ThreadSafeFabricator<T, E> create(@NonNull Fabricator<T, E> delegate) {
        return new OneOffFabricator<>(delegate);
    }
}
