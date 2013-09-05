/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;
import java.rmi.*;
/**
 *
 * @author juanj24
 */
public interface ITriquiPlayer extends Remote {

    public boolean acceptGame(String name) throws RemoteException;

    public void startGame(String opponent, ITriquiGame triqui) throws RemoteException;

    public int playTurn() throws RemoteException;

    public void endGame(boolean winner) throws RemoteException;

    public String getName() throws RemoteException;

    public void updateBoard(int[] board) throws RemoteException;
}
