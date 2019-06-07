package io.ayte.utility.fabricator.kit.delegate;

import io.ayte.utility.fabricator.api.Fabricator;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("unchecked")
class OneOffFabricatorTest {
    @Test
    public void cachesValue() throws Throwable {
        val delegate = mock(Fabricator.class);
        when(delegate.fabricate()).thenReturn(1, 2);
        val sut = OneOffFabricator.create(delegate);
        val values = Stream.generate(() -> null)
                .limit(2)
                .map(any -> {
                    try {
                        return sut.fabricate();
                    } catch (Throwable e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
        assertThat(values, not(hasItem(nullValue())));
        assertThat(values, hasSize(2));
        assertThat(values.get(0), is(values.get(1)));
        verify(delegate, times(1)).fabricate();
    }

    @Test
    public void cachesError() throws Throwable {
        val delegate = mock(Fabricator.class);
        when(delegate.fabricate()).then(any -> {
            throw new RuntimeException();
        });
        val sut = OneOffFabricator.create(delegate);
        val errors = Stream.generate(() -> null)
                .limit(2)
                .map(any -> {
                    try {
                        sut.fabricate();
                        return null;
                    } catch (Throwable e) {
                        return e;
                    }
                })
                .collect(Collectors.toList());
        assertThat(errors, not(hasItem(nullValue())));
        assertThat(errors, hasSize(2));
        assertThat(errors.get(0), is(errors.get(1)));
        verify(delegate, times(1)).fabricate();
    }
}
