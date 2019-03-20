package io.ayte.utility.fabricator.kit;

import io.ayte.utility.fabricator.api.Fabricator;
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
     * @param source Collection to use as source
     * @param <T> Collection element type.
     * @param <E> Exception type (will never be thrown).
     * @return Created fabricator.
     */
    public static <T, E extends Throwable> Fabricator<T, E> roundRobin(@NonNull Collection<? extends T> source) {
        return RoundRobinFabricator.create(source);
    }
}
