package by.javatr.finances.controller;

import by.javatr.finances.controller.exception.ControllerException;
import by.javatr.finances.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public class Controller {
    private final static String COMMAND_DELIMITER = " ";
    private CommandProvider commandProvider = new CommandProvider();
    private static Logger logger = LoggerFactory.getLogger(Controller.class.getName());

    public String executeTask(String request) throws ControllerException {

        String[] splitRequest = request.split(COMMAND_DELIMITER);
        String sessionId = splitRequest[0];
        String attributes = splitRequest[1];
        CommandName commandName = getCommandName(splitRequest[2]);

        Command command = commandProvider.getRepository().get(commandName);


        return completeTask(sessionId, attributes, command);
    }

    private String completeTask(String sessionId, String attributes, Command command) throws ControllerException {
        String response;
        try {
            response = command.execute(sessionId, attributes);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            throw new ControllerException(e);
        }
        return response;
    }

    private CommandName getCommandName(String name) {
        for (CommandName value : CommandName.values()) {
            if (value.toString().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
