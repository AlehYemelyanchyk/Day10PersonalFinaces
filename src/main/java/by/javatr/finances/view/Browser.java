package by.javatr.finances.view;

import by.javatr.finances.scanner.DataScanner;

public class Browser {
    private DataScanner scanner = new DataScanner();
    private static final String PARAMETER_DELIMITER = ";";
    private static final String BACK_QUIT_COMMAND = "0";
    private static final String NO_SESSION = "";
    private String sessionId = NO_SESSION;


    public void run() {
        boolean quit = false;
        while (!quit) {
            if (sessionId.equals(NO_SESSION)) {
                showEnterMenu();
                int menuChoice = scanner.readInteger();
                EnterMenuCommandProvider enterMenu = new EnterMenuCommandProvider();
                Requester requestCommand = enterMenu.getRepository().get(menuChoice);
                if (requestCommand != null) {
                    String commandResponse = requestCommand.processRequest(sessionId);
                    processResponse(commandResponse);
                }
            } else {
                showMenu();
                int menuChoice = scanner.readInteger();
                MenuCommandProvider menu = new MenuCommandProvider();
                Requester requestCommand = menu.getRepository().get(menuChoice);
                if (requestCommand != null) {
                    String commandResponse = requestCommand.processRequest(sessionId);
                    processResponse(commandResponse);
                }
            }
            quit = sessionId.equals(BACK_QUIT_COMMAND);
        }
    }

    private void showEnterMenu() {
        System.out.println("1. Sign in.\n" +
                "2. Log in.\n" +
                "0. Quit.");
    }

    private void showMenu() {
        System.out.println("1. Add money account.\n" +
                "2. Add income.\n" +
                "3. Add expense.\n" +
                "4. Remove money account.\n" +
                "5. Deposit money.\n" +
                "6. Withdrawal money.\n" +
                "7. Show balance.\n" +
                "8. Remove user.\n" +
                "0. Quit.");
    }

    private void processResponse(String commandResponse) {
        if (!commandResponse.contains(PARAMETER_DELIMITER)) {
            System.out.println(commandResponse);
        } else {
            String[] commandResponseSplit = commandResponse.split(PARAMETER_DELIMITER);
            String response = commandResponseSplit[0];
            sessionId = commandResponseSplit[1];
            System.out.println(response);
        }
    }
}
