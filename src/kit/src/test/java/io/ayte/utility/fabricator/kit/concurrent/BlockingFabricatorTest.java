package io.ayte.utility.fabricator.kit.concurrent;

import io.ayte.utility.fabricator.api.ThreadSafeFabricator;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;

class BlockingFabricatorTest {
    @Test
    public void wrapsRegularFabricator() {
        val sut = BlockingFabricator.create(() -> null);
        assertThat(sut, instanceOf(BlockingFabricator.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void wrapsThreadSafeFabricatorThrough() {
        val mock = mock(ThreadSafeFabricator.class);
        val sut = BlockingFabricator.create(mock);
        assertThat(sut, instanceOf(BlockingFabricator.class));
    }
}
