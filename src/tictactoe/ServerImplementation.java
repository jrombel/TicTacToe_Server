package tictactoe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {

    protected ServerImplementation() throws RemoteException {

        super();

    }

    @Override

    public Boolean checkWhoseMove() throws RemoteException {

        return false;

    }

    @Override

    public Board getBoard() throws RemoteException {

        Board a = new Board();

        return a;
    }

    @Override

    public Boolean makeMove(Board board) throws RemoteException {

        return false;
    }
    
    @Override
    
    public String test(String testString) throws RemoteException {
        
        System.out.println("The server is working ...");
        
        return "Connection was obtained, test text: " + testString;
    }
}
