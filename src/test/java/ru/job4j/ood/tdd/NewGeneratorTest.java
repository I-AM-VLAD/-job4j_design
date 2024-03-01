package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class NewGeneratorTest {
    @Test
    public void whenTest1() {
        Generator generator = new NewGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThat(generator.produce(template, args)).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    public void whenTest2() {
        Generator generator = new NewGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String template = "I am a ${name}, Who are ${example}? ";
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTest3() {
        Generator generator = new NewGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("example", "one");
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}