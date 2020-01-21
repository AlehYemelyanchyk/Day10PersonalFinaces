package by.javatr.finances.controller;

import by.javatr.finances.controller.impl.*;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    CommandProvider() {
        repository.put(CommandName.SIGN_IN_COMMAND, new SignInCommand());
        repository.put(CommandName.REMOVE_USER_COMMAND, new RemoveUserCommand());
        repository.put(CommandName.LOG_IN_COMMAND, new LogInCommand());
        repository.put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        repository.put(CommandName.ADD_MONEY_ACCOUNT_COMMAND, new AddMoneyAccountCommand());
        repository.put(CommandName.REMOVE_MONEY_ACCOUNT_COMMAND, new RemoveMoneyAccountCommand());
        repository.put(CommandName.SHOW_BALANCE_COMMAND, new ShowBalanceCommand());
        repository.put(CommandName.ADD_INCOME_COMMAND, new AddIncomeCommand());
        repository.put(CommandName.ADD_EXPENSE_COMMAND, new AddExpenseCommand());
        repository.put(CommandName.DEPOSIT_MONEY_COMMAND, new DepositMoneyCommand());
        repository.put(CommandName.WITHDRAWAL_MONEY_COMMAND, new WithdrawalMoneyCommand());
        repository.put(CommandName.GET_ACCOUNTS_COMMAND, new GetAccountsCommand());
        repository.put(CommandName.WRONG_REQUEST_COMMAND, new WrongRequestCommand());
        repository.put(CommandName.BACK_COMMAND, new BackCommand());
    }

    public Map<CommandName, Command> getRepository() {
        return repository;
    }
}
