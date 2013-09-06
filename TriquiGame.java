/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;

import java.rmi.*;
import java.rmi.server.*;

@SuppressWarnings("serial")
public class TriquiGame extends UnicastRemoteObject implements ITriquiGame {

    public static final int N = 0;
    public static final int X = 1;
    public static final int O = 2;
    private ITriquiPlayer[] players;
    private int turn;
    private int plays;
    private int[] board;

    public TriquiGame(ITriquiPlayer player1, ITriquiPlayer player2) throws RemoteException {
        players = new ITriquiPlayer[2];
        players[0] = player1;
        players[1] = player2;
        turn = 0;
        plays = 0;
        board = new int[9];
    }

    @Override
    public void start() throws RemoteException {
        players[0].startGame(players[1].getName(), this);
        players[1].startGame(players[0].getName(), this);
        play();
    }

    public void play() {
        boolean winner = false;
        try {
            while (plays < 9 && !winner) {
                board();
                int move = -1;
                while ((move < 0 || move > 8) || board[move] != N) {
                    move = players[turn].playTurn();
                }
                board[move] = ++turn;
                turn %= 2;
                plays++;
                winner = testWinner();
            }
            board();
            if (winner) {
                players[turn].endGame(false);
                players[(turn + 1) % 2].endGame(true);
            } else {
                players[turn].endGame(false);
                players[(turn + 1) % 2].endGame(false);
            }
        } catch (RemoteException re) {
        }
    }
    
    
    

    public void board() {
        try {
            for (ITriquiPlayer player : players) {
                player.updateBoard(board);
            }
        } catch (RemoteException re) {
            System.out.println("Failed update the boards.");
        }
    }

    public boolean testWinner() {
        return ((board[0] == board[1] && board[1] == board[2] && board[0] != N)
                || (board[3] == board[4] && board[4] == board[5] && board[3] != N)
                || (board[6] == board[7] && board[7] == board[7] && board[6] != N)
                || (board[0] == board[3] && board[3] == board[6] && board[0] != N)
                || (board[1] == board[4] && board[4] == board[7] && board[1] != N)
                || (board[2] == board[5] && board[5] == board[8] && board[2] != N)
                || (board[0] == board[4] && board[4] == board[8] && board[0] != N)
                || (board[2] == board[4] && board[4] == board[6] && board[2] != N));
    }
}
