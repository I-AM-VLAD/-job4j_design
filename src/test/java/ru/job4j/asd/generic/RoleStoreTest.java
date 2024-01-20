package ru.job4j.asd.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRolenameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("programmer");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("programmer");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        boolean result = store.replace("1", new Role("1", "designer"));
        assertThat(result).isTrue();
    }
}