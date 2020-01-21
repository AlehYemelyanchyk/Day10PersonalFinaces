package by.javatr.finances.view;

import by.javatr.finances.view.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class MenuCommandProvider {
    private final Map<Integer, Requester> repository = new HashMap<>();

    MenuCommandProvider() {
        repository.put(1, new AddMoneyAccountRequest());
        repository.put(2, new AddIncomeRequest());
        repository.put(3, new AddExpenseRequest());
        repository.put(4, new RemoveMoneyAccountRequest());
        repository.put(5, new DepositMoneyRequest());
        repository.put(6, new WithdrawalMoneyRequest());
        repository.put(7, new ShowBalanceRequest());
        repository.put(8, new RemoveUserRequest());
        repository.put(0, new LogOutRequest());
    }

    public Map<Integer, Requester> getRepository() {
        return repository;
    }
}
