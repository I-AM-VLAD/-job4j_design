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
        return accounts.putIfAbsent(account.getId(), account) != null;
    }

    public synchronized boolean update(Account account) {
        accounts.put(account.getId(), account);
        return true;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        Optional<Account> opt = Optional.ofNullable(accounts.get(id));
        return opt;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> optAccountFrom = getById(fromId);
        Optional<Account> optAccountTo = getById(toId);
        boolean result = false;
        if (optAccountFrom.isPresent() && optAccountTo.isPresent()) {
            Account accountFrom = optAccountFrom.get();
            Account accountTo = optAccountTo.get();
            if (amount <= accountFrom.getAmount()) {
                accountFrom.setAmount(accountFrom.getAmount() - amount);
                accountTo.setAmount(accountTo.getAmount() + amount);
                result = true;
            }
        }
        return result;
    }
}
