/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.Naming;

/**
 *
 * @author juanj24
 */
public class TriquiServer {
    public static void main(String[] args) {
		IUserManager gestor = null;
		try {
			gestor = new UserManager();
			Naming.bind("triqui", gestor);
			System.out.println("Servidor listo.");
		} catch(Exception e) {
			System.out.println("No se pudo iniciar el servidor.");
		}
	}
}
