/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.IUserManager;
import java.rmi.Naming;
/**
 *
 * @author juanj24
 */
public class TriquiClient {
    public static void imprimirUso() {
		System.out.println("\nUso:");
		System.out.println("  java TriquiClient host [nick]");
		System.out.println("\nhost => localizacion del TriquiServer.");
		System.out.println("nick => nick para usar en el juego.");
		System.out.println("\nSi no se indica el nick, se pedira al comienzo del juego.\n");
	}

	public static void main(String[] args) {
		String host = null, nick = null;
		if (args.length >= 1) {
			String opcion = args[0];
			if (opcion.equals("-h")) {
				imprimirUso();
				System.exit(0);
			}
			host = opcion;
			if (args.length >= 2)
				nick = args[1];
		} else {
			imprimirUso();
			System.exit(0);
		}
		          IUserManager gestor = null;
		ITriquiPlayer jug = null;
		try {
			gestor = (IUserManager)Naming.lookup("rmi://" + host + "/triqui");			
			if (nick == null) {
				nick = Reader.getInstance().read();
				System.out.println("Ingresa tu nick:");
			}
			jug = new TriquiPlayer(nick, gestor);
		} catch(Exception e) {
			System.out.println("No se pudo inicializar el cliente");
			System.out.println(e.getMessage());
		}
	}
}
