package by.javatr.finances.service.factory;

import by.javatr.finances.service.impl.ClientServiceImpl;
import by.javatr.finances.service.impl.EntityServiceImpl;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();

    private final EntityServiceImpl entityService = new EntityServiceImpl();
    private final ClientServiceImpl clientService = new ClientServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ClientServiceImpl getClientService() {
        return clientService;
    }

    public EntityServiceImpl getEntityService() {
        return entityService;
    }
}
