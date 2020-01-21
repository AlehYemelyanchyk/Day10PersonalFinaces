package by.javatr.finances.dao.bean;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class Account extends FinancesEntity {
    private int balance;
    private final Queue<String> transactions = new PriorityQueue<>();

    public Account() {
    }

    public Account(String name, String currency) {
        super(name, currency);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Queue<String> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }

        Account account = (Account) object;

        return getBalance() == account.getBalance();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getBalance();
        return result;
    }

    @Override
    public String toString() {
        return super.toString()
                + "balance: " + this.getBalance();
    }
}
