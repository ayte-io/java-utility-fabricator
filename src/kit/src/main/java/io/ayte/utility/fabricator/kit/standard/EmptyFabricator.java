package io.ayte.utility.fabricator.kit.standard;

import io.ayte.utility.fabricator.api.Fabricator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmptyFabricator<T, E extends Throwable> implements Fabricator<T, E> {
    private static final EmptyFabricator INSTANCE = new EmptyFabricator<>();

    @Override
    public T fabricate() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T, E extends Throwable> EmptyFabricator<T, E> create() {
        return INSTANCE;
    }
}
