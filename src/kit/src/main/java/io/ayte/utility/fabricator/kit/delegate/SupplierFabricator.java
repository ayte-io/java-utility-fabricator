package io.ayte.utility.fabricator.kit.delegate;

import io.ayte.utility.fabricator.api.Fabricator;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Supplier;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SupplierFabricator<T, E extends Throwable> implements Fabricator<T, E> {
    private final Supplier<? extends T> delegate;

    @Override
    public T fabricate() {
        return delegate.get();
    }

    public static <T, E extends Throwable> SupplierFabricator<T, E> create(@NonNull Supplier<? extends T> delegate) {
        return new SupplierFabricator<>(delegate);
    }
}
