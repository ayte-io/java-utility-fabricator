package io.ayte.utility.fabricator.api;

/**
 * Denotes a fabricator that can be safely called in concurrent
 * environment.
 *
 * @param <T> Fabricator return type.
 * @param <E> Fabricator exception type.
 */
public interface ThreadSafeFabricator<T, E extends Throwable> extends Fabricator<T, E> {}
