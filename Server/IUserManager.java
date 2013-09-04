/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.ITriquiPlayer;
import java.rmi.*;
/**
 *
 * @author juanj24
 */
public interface IUserManager extends Remote {
    public void registrar(ITriquiPlayer j, String nick) throws RemoteException;
    public void salir(String nick) throws RemoteException;
    public String[] listar() throws RemoteException;
    public void pedirJuego(String pedidor, String pedido) throws RemoteException, Exception;
}
