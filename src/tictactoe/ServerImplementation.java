package tictactoe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {

    int id = 0;
    int status = -1;
    //-1 - no players
    // 0 - one player, waiting for the second player
    // 1 - player one move
    // 2 - player two move
    // 3 - player one won
    // 4 - player two won
    int playersCount = 0;
    public static int idPlayer1 = -1;
    public static int idPlayer2 = -1;

    protected ServerImplementation() throws RemoteException {

        super();

    }

    @Override
    public int connect() throws RemoteException {

        int idTmp = id++;

        if (playersCount == 0) {
            idPlayer1 = idTmp;
            playersCount++;
            status = 0;
        } else if (playersCount == 1) {
            idPlayer2 = idTmp;
            playersCount++;
            status = 1;
        } else {
            idTmp = -1;
        }
        System.out.println("Player with id: " + idTmp + " join to game, game status: " + status);
        return idTmp;
    }

    @Override
    public void disconnect(int id) throws RemoteException {

        if (id == idPlayer1) {
            idPlayer1 = -1;
            playersCount--;
        } else if (id == idPlayer2) {
            idPlayer2 = -1;
            playersCount--;
        }
        if (playersCount == 1) {
            status = 0;
        } else if (playersCount == 0) {
            status = -1;
        }
        System.out.println("Player with id: " + id + " left to game, game status: " + status);
    }

    @Override
    public int checkPlayerStatus(int id) throws RemoteException {
        if (status < 1) {
            return status;
        } else if (id == idPlayer1) {
            return status;
        } else {
            return status + 1;
        }
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
}
