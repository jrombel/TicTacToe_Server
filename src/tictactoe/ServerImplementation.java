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
    // 5 - dead-heat
    int playersCount = 0;
    public static int idPlayer1 = -1;
    public static int idPlayer2 = -1;
    public static Board board = Board.getInstance();

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
            board = new Board();
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
        if (status < 1 || status == 5) {
            return status;
        } else if (id == idPlayer1) {
            return status;
        } else if (id == idPlayer2) {
            if (status == 1) {
                return 2;
            } else if (status == 2) {
                return 1;
            } else if (status == 3) {
                return 4;
            } else {
                return 3;
            }
        } else {
            return -1;
        }
    }

    @Override
    public Boolean selectField(int id, int number) throws RemoteException {
        if (board.fields[number] == 0) {
            if (status == 1 && id == idPlayer1) {
                board.fields[number] = 1;
                status = 2;
            } else if (status == 2 && id == idPlayer2) {
                board.fields[number] = 2;
                status = 1;
            }

            //checking if there is anybody winning and setting the game status
            if (board.fields[0] == board.fields[1] && board.fields[1] == board.fields[2]) {
                if (board.fields[0] == 1) {
                    status = 3;
                } else if (board.fields[0] == 2) {
                    status = 4;
                }
            } else if (board.fields[3] == board.fields[4] && board.fields[4] == board.fields[5]) {
                if (board.fields[3] == 1) {
                    status = 3;
                } else if (board.fields[3] == 2) {
                    status = 4;
                }
            } else if (board.fields[6] == board.fields[7] && board.fields[7] == board.fields[8]) {
                if (board.fields[6] == 1) {
                    status = 3;
                } else if (board.fields[6] == 2) {
                    status = 4;
                }
            } else if (board.fields[0] == board.fields[3] && board.fields[3] == board.fields[6]) {
                if (board.fields[0] == 1) {
                    status = 3;
                } else if (board.fields[0] == 2) {
                    status = 4;
                }
            } else if (board.fields[1] == board.fields[4] && board.fields[4] == board.fields[7]) {
                if (board.fields[1] == 1) {
                    status = 3;
                } else if (board.fields[1] == 2) {
                    status = 4;
                }
            } else if (board.fields[2] == board.fields[5] && board.fields[5] == board.fields[8]) {
                if (board.fields[2] == 1) {
                    status = 3;
                } else if (board.fields[2] == 2) {
                    status = 4;
                }
            } else if (board.fields[0] == board.fields[4] && board.fields[4] == board.fields[8]) {
                if (board.fields[0] == 1) {
                    status = 3;
                } else if (board.fields[0] == 2) {
                    status = 4;
                }
            } else if (board.fields[2] == board.fields[4] && board.fields[4] == board.fields[6]) {
                if (board.fields[2] == 1) {
                    status = 3;
                } else if (board.fields[2] == 2) {
                    status = 4;
                }
            } else {
                Boolean noEmpty = true;
                for (int i = 0; i < 9; i++) {
                    if (board.fields[i] == 0) {
                        noEmpty = false;
                    }
                }
                if (noEmpty == true) {
                    status = 5;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Board getBoard() throws RemoteException {

        return board;
    }
}
