package io.ayte.utility.fabricator.api;

import java.util.function.Supplier;

/**
 * Fabricator is {@link Supplier} dark brother with ability to throw
 * specific exception on call.
 *
 * Fabricator name was preferred over Producer, Factory and other names
 * because of collision possibility.
 *
 * @param <T> Type of returned value.
 * @param <E> Type of possibly thrown exception.
 *
 * @since 0.1.0
 */
@FunctionalInterface
public interface Fabricator<T, E extends Throwable> {
    T fabricate() throws E;
}
