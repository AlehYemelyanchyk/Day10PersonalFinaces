package by.javatr.finances.controller;

import by.javatr.finances.controller.exception.ControllerException;
import by.javatr.finances.service.exception.ServiceException;

/**
 * @author Aleh Yemelyanchyk on 12/28/2019.
 */
public interface Command {
    String execute(String sessionId, String attributes) throws ControllerException, ServiceException;
}
