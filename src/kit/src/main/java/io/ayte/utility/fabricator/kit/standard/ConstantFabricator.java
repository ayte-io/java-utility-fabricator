package io.ayte.utility.fabricator.kit.standard;

import io.ayte.utility.fabricator.api.Fabricator;
import io.ayte.utility.fabricator.api.StableFabricator;
import io.ayte.utility.fabricator.api.ThreadSafeFabricator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantFabricator<T, E extends Throwable> implements StableFabricator<T, E>, ThreadSafeFabricator<T, E> {
    private final T value;

    @Override
    public T fabricate() {
        return value;
    }

    public static <T, E extends Throwable> Fabricator<T, E> create(T value) {
        return new ConstantFabricator<>(value);
    }
}
