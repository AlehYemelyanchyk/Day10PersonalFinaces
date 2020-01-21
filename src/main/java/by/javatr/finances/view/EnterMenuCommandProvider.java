package by.javatr.finances.view;

import by.javatr.finances.view.impl.LogInRequest;
import by.javatr.finances.view.impl.LogOutRequest;
import by.javatr.finances.view.impl.SignInRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleh Yemelyanchyk on 1/4/2020.
 */
public class EnterMenuCommandProvider {
    private final Map<Integer, Requester> repository = new HashMap<>();

    EnterMenuCommandProvider() {
        repository.put(1, new SignInRequest());
        repository.put(2, new LogInRequest());
        repository.put(0, new LogOutRequest());
    }

    public Map<Integer, Requester> getRepository() {
        return repository;
    }
}
