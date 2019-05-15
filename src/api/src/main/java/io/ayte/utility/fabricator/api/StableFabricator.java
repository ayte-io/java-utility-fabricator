package io.ayte.utility.fabricator.api;

/**
 * Denotes a fabricator that always returns same value / throws equal
 * exceptions, even though that value  / exception may not be known
 * before first call.
 *
 * <p>
 * Use {@link ThreadSafeFabricator} if you need to be sure about thread
 * safety.
 * </p>
 * @param <T> Fabricator return type.
 * @param <E> Fabricator exception type.
 */
public interface StableFabricator<T, E extends Throwable> extends Fabricator<T, E> {}
