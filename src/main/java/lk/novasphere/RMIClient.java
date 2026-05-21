package lk.novasphere;

import lk.novasphere.client.UserService;
import lk.novasphere.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class RMIClient {
    public static void main(String[] args) {

        try {

//            Registry registry = LocateRegistry.getRegistry("localhost", 6666);
//            String[] list = registry.list();
//            for (String s : list) {
//                System.out.println(s);
//            }


            // UserService userService = (UserService) Naming.lookup("rmi://127.0.0.1:6666/user_service");
            // can use "//127.0.0.1:6666/user_service"

            Properties prop = new Properties();
            prop.put(Context.PROVIDER_URL, "rmi://127.0.0.1:6666");
            prop.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");

            InitialContext ic = new InitialContext(prop);
            UserService userService = (UserService) ic.lookup("user_service");

            // Message message = (Message) registry.lookup("message_service");
            /// String msg = message.hello();

            /// System.out.println(msg);
            /// Data data = message.getData();

            //UserService userService = (UserService) registry.lookup("user_service");
            userService.addUser(1, new User(1, "Maleesha", "Anuradhapura", "maleesha@example.com"));

            userService.getAllUsers().forEach(user -> {
                System.out.println(user.getName());
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
