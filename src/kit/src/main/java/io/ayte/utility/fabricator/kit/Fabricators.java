package io.ayte.utility.fabricator.kit;

import io.ayte.utility.fabricator.api.Fabricator;
import io.ayte.utility.fabricator.kit.concurrent.BlockingFabricator;
import io.ayte.utility.fabricator.kit.delegate.SupplierFabricator;
import io.ayte.utility.fabricator.kit.standard.ConstantFabricator;
import io.ayte.utility.fabricator.kit.standard.EmptyFabricator;
import io.ayte.utility.fabricator.kit.standard.RoundRobinFabricator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Fabricators {
    /**
     * @param <T> Type of returned value, which will always be null.
     * @param <E> Type of exception, which will never be thrown.
     * @return Fabricator that returns nulls.
     */
    public static <T, E extends Throwable> Fabricator<T, E> empty() {
        return EmptyFabricator.create();
    }

    public static <T, E extends Throwable> Fabricator<T, E> supplier(@NonNull Supplier<? extends T> supplier) {
        return SupplierFabricator.create(supplier);
    }

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
     * <p>
     * If fabricator is already thread safe, returns it as is.
     * </p>
     * @param fabricator Fabricator to process.
     * @param <T> Fabricator return type.
     * @param <E> Fabricator exception type.
     * @return Wrapped or original fabricator.
     */
    public static <T, E extends Throwable> Fabricator<T, E> blocking(@NonNull Fabricator<T, E> fabricator) {
        return BlockingFabricator.create(fabricator);
    }
}
