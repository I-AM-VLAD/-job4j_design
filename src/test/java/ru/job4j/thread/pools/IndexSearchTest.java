package ru.job4j.thread.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class IndexSearchTest {
    @Test
    public void whenTestLinearSearch() {
        User[] source = {new User("1", "111"), new User("2", "222")};
        IndexSearch indexSearch = new IndexSearch(new User("2", "222"),
                source, 0, source.length - 1);
        assertThat(indexSearch.compute()).isEqualTo(1);
    }
    /*
    @Test
    public void whenRecursiveSearch() {
        Model[] models = new Model[100];
        for (int i = 0; i < models.length; i++) {
            models[i] = new Model(i);
        }
        IndexSearch indexSearch = new IndexSearch(new Model(20), models, 0, models.length - 1);
        assertThat(indexSearch.compute()).isEqualTo(20);
    }

     */
}

