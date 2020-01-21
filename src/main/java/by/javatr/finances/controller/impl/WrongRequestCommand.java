package by.javatr.finances.controller.impl;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class WrongRequestCommand extends AbstractCommandExecutor {
    private final static String SUCCESS = "Wrong request. Please, Try again";

    @Override
    protected String executeCommand(String id, String[] attributes, String[] sessionInfoSplit) {
        return SUCCESS + PARAMETER_DELIMITER + id;
    }
}
