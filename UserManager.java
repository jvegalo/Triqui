/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 *
 * @author juanj24
 */
public class UserManager extends UnicastRemoteObject implements IUserManager{
    
	private Hashtable<String,ITriquiPlayer> players;
	
	public UserManager() throws RemoteException{
		players = new Hashtable<String,ITriquiPlayer>();
	}
	
	@Override
	public String[] listReadyPlayers() throws RemoteException {
		String[] content = new String[players.size()];
		Enumeration<String> values = players.keys();
		for (int i = 0; values.hasMoreElements(); i++) {
			content[i] = values.nextElement();
		}
		return content;
	}

	@Override
	public void requestGame(String petitioner, String requested) throws RemoteException, Exception {
		System.out.println(petitioner + " wants to play with " + requested);
		ITriquiPlayer player = players.get(requested);
		if (player == null)
			throw new Exception("The player does not exist.");
		ITriquiGame triqui = null;
		if(player.acceptGame(petitioner)){
			System.out.println("Game accepted.");
			triqui = new TriquiGame(players.get(petitioner), players.get(requested));
			exit(requested); exit(petitioner);
			triqui.start();
		} else {
			throw new Exception("The application was rejected.");
		}
	}

	@Override
	public void registerPlayer(ITriquiPlayer player, String name) throws RemoteException {
		players.put(name, player);	
		System.out.println("has been register " + name + ".");
	}

	@Override
	public void exit(String name) throws RemoteException {
		players.remove(name);
		System.out.println("Has exited " + name + ".");
		
	}

}
