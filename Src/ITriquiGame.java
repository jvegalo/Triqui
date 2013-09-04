/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;

import java.rmi.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public interface ITriquiGame extends Remote {
    /*
     * Inicia un juego
     */
    public void     Start() throws RemoteException;
    
}
