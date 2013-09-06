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
public class TriquiClient {
    
    public static void showUse() {
		System.out.println("\nUse:");
		System.out.println("  java TriquiClient host [name]");
		System.out.println("\nhost => location of TriquiServer.");
		System.out.println("name => name for use in the game.");
		System.out.println("\nIf name is not indicated, it will ask to start the game.\n");
	}

	public static void main(String[] args) {
		String host = null, name = null;
		if (args.length >= 1) {
			String option = args[0];
			if (option.equals("-h")) {
				showUse();
				System.exit(0);
			}
			host = option;
			if (args.length >= 2)
				name = args[1];
		} else {
			showUse();
			System.exit(0);
		}
		          IUserManager manager = null;
		ITriquiPlayer player = null;
		try {
			manager = (IUserManager)Naming.lookup("rmi://" + host + "/triqui");			
			if (name == null) {
				name = Reader.getInstance().read();
				System.out.println("Enter your name:");
			}
			player = new TriquiPlayer(name, manager);
		} catch(Exception e) {
			System.out.println("Failed to initialize the client");
			System.out.println(e.getMessage());
		}
	}
}
