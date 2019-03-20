package io.ayte.utility.fabricator.kit.standard;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoundRobinFabricatorTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    public void throwsOnNull() {
        assertThrows(NullPointerException.class, () -> RoundRobinFabricator.create(null));
    }

    @Test
    public void acceptsEmptyCollection() {
        val sut = RoundRobinFabricator.<Object, RuntimeException>create(Collections.emptySet());
        assertThat(sut.fabricate(), nullValue());
        assertThat(sut.fabricate(), nullValue());
    }

    public static Object[][] dataProvider() {
        return new Object[][] {
                {Collections.singleton(1)},
                {Arrays.asList(1, 2, 3)}
        };
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void loops(Iterable<Integer> source) {
        val sut = RoundRobinFabricator.<Integer, RuntimeException>create(source);
        for (int i = 0; i < 2; i++) {
            for (val element : source) {
                assertThat(sut.fabricate(), equalTo(element));
            }
        }
    }
}
