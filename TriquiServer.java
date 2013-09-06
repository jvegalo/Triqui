/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;

import java.rmi.Naming;

/**
 *
 * @author juanj24
 */
public class TriquiServer {
    public static void main(String[] args) {
		IUserManager manager = null;
		try {
			manager = new UserManager();
			Naming.bind("triqui", manager);
			System.out.println("Server ready.");
		} catch(Exception e) {
			System.out.println("Failed start the server.");
		}
	}
}
