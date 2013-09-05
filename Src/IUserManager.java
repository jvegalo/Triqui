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
public interface IUserManager extends Remote {
    public void registerPlayer(ITriquiPlayer player, String name) throws RemoteException;
    public void exit(String name) throws RemoteException;
    public String[] listReadyPlayers() throws RemoteException;
    public void requestGame(String petitioner, String requested) throws RemoteException, Exception;
}
