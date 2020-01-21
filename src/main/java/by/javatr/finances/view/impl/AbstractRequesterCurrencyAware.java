package by.javatr.finances.view.impl;

import by.javatr.finances.service.factory.ServiceFactory;

/**
 * @author Aleh Yemelyanchyk on 1/8/2020.
 */
public abstract class AbstractRequesterCurrencyAware extends AbstractRequester {
    private static final String DEFAULT_CURRENCY = "BYN";

    protected String getCurrency() {
        String currency = DEFAULT_CURRENCY;
        String currencies = ServiceFactory.getInstance().getEntityService().getCurrencies();
        if (currencies != null) {
            String[] splitCurrencies = currencies.split(ENUM_VALUES_DELIMITER);
            for (int i = 0; i < splitCurrencies.length; i++) {
                System.out.println((i + ZERO_TO_ONE_SHIFT) + ". " + splitCurrencies[i]);
            }
            System.out.println("Enter currency number:");
            int currencyChoice = getScanner().readIntegerInRange(FIRST_RANGE_NUMBER, splitCurrencies.length);
            currency = splitCurrencies[currencyChoice - ZERO_TO_ONE_SHIFT];
        }
        return currency;
    }
}
