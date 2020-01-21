package by.javatr.finances.controller.impl;

/**
 * @author Aleh Yemelyanchyk on 1/7/2020.
 */
public class BackCommand extends AbstractCommandExecutor {

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) {
        return EMPTY_ATTRIBUTES + PARAMETER_DELIMITER + id;
    }
}
