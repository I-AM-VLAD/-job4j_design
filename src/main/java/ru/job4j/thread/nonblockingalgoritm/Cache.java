package ru.job4j.thread.nonblockingalgoritm;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) throws OptimisticException {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public void update(Base model) throws OptimisticException {
        memory.computeIfPresent(
                model.getId(),
                (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                            throw new OptimisticException("Versions don't match. Data hasn't changed."
                        );
                    }
                    model.setVersion(model.getVersion() + 1);
                    return model;
                }
        );
    }

    public void delete(int id) {
        memory.remove(id);
    }

    public Optional<Base> findById(int id) {
        return Stream.of(memory.get(id))
                .filter(Objects::nonNull)
                .findFirst();
    }
}

