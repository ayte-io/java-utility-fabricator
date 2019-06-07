package io.ayte.utility.fabricator.kit;

import io.ayte.utility.fabricator.api.Fabricator;
import io.ayte.utility.fabricator.api.ThreadSafeFabricator;
import io.ayte.utility.fabricator.kit.concurrent.BlockingFabricator;
import io.ayte.utility.fabricator.kit.delegate.OneOffFabricator;
import io.ayte.utility.fabricator.kit.delegate.SupplierFabricator;
import io.ayte.utility.fabricator.kit.standard.ConstantFabricator;
import io.ayte.utility.fabricator.kit.standard.EmptyFabricator;
import io.ayte.utility.fabricator.kit.standard.RoundRobinFabricator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Some of static methods return {@code Fabricator<T, E>} where
 * {@code Fabricator<T, RuntimeException>} could be safely used. This is
 * done because otherwise such fabricators couldn't serve as
 * placeholders for {@code Fabricator<T, IOException>}, for example.
 *
 * @since 0.1.0
 */
@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Fabricators {
    /**
     * @param <T> Type of returned value, which will always be null.
     * @param <E> Type of exception, which will never be thrown.
     * @return Fabricator that returns nulls.
     * @since 0.1.0
     */
    public static <T, E extends Throwable> Fabricator<T, E> empty() {
        return EmptyFabricator.create();
    }

    /**
     * Converts supplier into fabricator.
     *
     * @param supplier Supplier to convert
     * @param <T> Supplier/fabricator return type.
     * @param <E> Custom exception type, which is never thrown.
     * @return Wrapped supplier.
     * @since 0.1.0
     */
    public static <T, E extends Throwable> Fabricator<T, E> supplier(@NonNull Supplier<? extends T> supplier) {
        return SupplierFabricator.create(supplier);
    }

    /**
     * @param value Value to return.
     * @param <T> Value type.
     * @param <E> Custom exception type.
     * @return Fabricator that returns same value over and over again
     */
    public static <T, E extends Throwable> Fabricator<T, E> constant(T value) {
        return ConstantFabricator.create(value);
    }

    /**
     * Creates fabricator that returns values from provided collection
     * in round-robin fashion.
     *
     * <p>
     * Returned fabricator may be of any type, e.g.
     * {@link EmptyFabricator} for zero-sized collection.
     * </p>
     *
     * <p>
     * It is implied that collection is immutable. Fabricator is not
     * synchronized.
     * </p>
     *
     * @param source Collection to use as source
     * @param <T> Collection element type.
     * @param <E> Exception type (will never be thrown).
     * @return Created fabricator.
     */
    public static <T, E extends Throwable> Fabricator<T, E> roundRobin(@NonNull Collection<? extends T> source) {
        return RoundRobinFabricator.create(source);
    }

    /**
     * Creates fabricator that returns values from provided iterable
     * in round-robin fashion.
     *
     * @param source Collection to use as source
     * @param <T> Collection element type.
     * @param <E> Exception type (will never be thrown).
     * @return Created fabricator.
     */
    public static <T, E extends Throwable> Fabricator<T, E> roundRobin(@NonNull Iterable<? extends T> source) {
        return RoundRobinFabricator.create(source);
    }

    /**
     * Wraps fabricator in a thread-safe fabricator with blocking
     * access.
     *
     * @param fabricator Fabricator to process.
     * @param <T> Fabricator return type.
     * @param <E> Fabricator exception type.
     * @return Wrapped fabricator.
     */
    public static <T, E extends Throwable> ThreadSafeFabricator<T, E> blocking(@NonNull Fabricator<T, E> fabricator) {
        return BlockingFabricator.create(fabricator);
    }

    /**
     * Converts fabricator into thread safe fabricator, if it is not
     * already thread safe.
     *
     * @param fabricator Fabricator to convert
     * @param <T> Fabricator return type.
     * @param <E> Fabricator exception type.
     * @return Wrapped or original fabricator.
     */
    public static <T, E extends Throwable> ThreadSafeFabricator<T, E> threadSafe(@NonNull Fabricator<T, E> fabricator) {
        if (fabricator instanceof ThreadSafeFabricator) {
            return (ThreadSafeFabricator<T, E>) fabricator;
        }
        return blocking(fabricator);
    }

    /**
     * Creates fabricator wrapper that calls underlying fabricator only
     * once and caches the result. Please note that in case of error
     * stack trace will also be cached, thus such fabricator should not
     * be called from different places to prevent debug hell.
     *
     * @param fabricator Fabricator to wrap.
     * @param <T> Fabricator return type.
     * @param <E> Fabricator error type.
     * @return Wrapped fabricator.
     * @since 0.1.1
     */
    public static <T, E extends Throwable> ThreadSafeFabricator<T, E> oneOff(@NonNull Fabricator<T, E> fabricator) {
        return OneOffFabricator.create(fabricator);
    }
}
