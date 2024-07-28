package ru.job4j.thread;

import java.util.HashMap;
import java.util.Optional;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.getId(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.getId(), getById(account.getId()).get(), account);
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> optAccountFrom = getById(fromId);
        Optional<Account> optAccountTo = getById(toId);
        boolean result = false;
        if (optAccountFrom.isPresent() && optAccountTo.isPresent()) {
            if (amount <= optAccountFrom.get().getAmount()) {
                optAccountFrom.get().setAmount(optAccountFrom.get().getAmount() - amount);
                optAccountTo.get().setAmount(optAccountTo.get().getAmount() + amount);
                result = true;
            }
        }
        return result;
    }
}
