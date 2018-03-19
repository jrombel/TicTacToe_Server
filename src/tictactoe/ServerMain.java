package tictactoe;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerMain {

    public static void main(String[] args) {

        try {

            System.setProperty("java.security.policy", "security.policy");

            if (System.getSecurityManager() == null) {

                System.setSecurityManager(new SecurityManager());

            }

            System.setProperty("java.rmi.server.codebase", "file:/home/laptop/NetBeansProjects/TicTacToe_Server/build/classes/");
            System.out.println("Codebase: " + System.getProperty("java.rmi.server.codebase"));
            System.setProperty("java.rmi.server.hostname", "192.168.43.233");

            //linijka poniżej do odpalania RMIRegistry służy!
            LocateRegistry.createRegistry(1099);
            ServerImplementation obj1 = new ServerImplementation();

            //Naming.rebind("//192.168.43.233/ABC", obj1);
            Naming.rebind("//localhost/ABC", obj1);

            System.out.println("The server is working ...");

        } catch (RemoteException | MalformedURLException e) {

            e.printStackTrace();

        }
    }
}
