package io.ayte.utility.fabricator.kit.standard;

import io.ayte.utility.fabricator.api.Fabricator;
import io.ayte.utility.fabricator.api.StableFabricator;
import io.ayte.utility.fabricator.api.ThreadSafeFabricator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmptyFabricator<T, E extends Throwable> implements StableFabricator<T, E>, ThreadSafeFabricator<T, E> {
    private static final EmptyFabricator INSTANCE = new EmptyFabricator<>();

    @Override
    public T fabricate() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T, E extends Throwable> Fabricator<T, E> create() {
        return INSTANCE;
    }
}
