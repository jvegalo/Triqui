/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class TriquiPlayer extends UnicastRemoteObject implements ITriquiPlayer{
    
    public static final int LIST = 1;
	public static final int PLAY = 2;
	public static final int WAIT = 3;
	public static final int EXIT = 0;
	
	private String name;
	private IUserManager manager;
	private boolean playing;
	
	public TriquiPlayer(String name, IUserManager manager) throws RemoteException {
		this.name = name;
		this.manager = manager;
		playing = false;
		selectedOption();
	}	
	
	public void printMenu() {
		System.out.println("\nSelect an option:");
		System.out.println("1. List Players.");
		System.out.println("2. Request Game.");
		System.out.println("3. Wait Game.");
		System.out.println("0. Exit.");
	}	
	
	public void selectedOption() {
		while (true) {
			int option = -1;
			while (option < 0 || option > 3) {
				printMenu();
				option = Integer.parseInt(Reader.getInstance().read().trim());
			}
			switch (option) {
				case (LIST):
					showPlayers();
					break;
				case (PLAY):
					requestGame();
					break;
				case(WAIT):
					try {
						manager.registerPlayer(this, name);
						System.out.println("Waiting game...");
						while(!playing) {
							try {
								Thread.sleep(500);
							} catch(InterruptedException ie) {
								ie.printStackTrace();
							}
							System.out.flush();
						}	
						while (playing) {
							try {
								Thread.sleep(500);
							} catch(InterruptedException ie) {
								ie.printStackTrace();
							}
							System.out.flush();
						}
					} catch(RemoteException re) {
						System.out.println("Failed register the server.");
					}
					break;
				case (EXIT):
					System.out.println("finalized.");
					System.exit(0);
			}
		}
	}
	
	public void showPlayers() {
		try {
			String[] players = manager.listReadyPlayers();
			System.out.println("\nPlayers:");
			for (String player : players) {
				System.out.println(player);
			}
			System.out.println();
		} catch(Exception e) {
			System.out.println("Failed list of players.");
		}
	}
	
	public void requestGame() {
		System.out.println("\nEnter the name of the player who you want to play:");
		String player = Reader.getInstance().read();
		System.out.println("\nWaiting response...");
		try {			
			manager.registerPlayer(this, this.name);
			manager.requestGame(this.name, player);
		} catch (RemoteException re) {
			System.out.println("Failed connect to the player.");
			System.out.println(re.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void printBoard(int[] board) {		
		System.out.println();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				switch(board[i*3+j]) {
					case(TriquiGame.X):
						System.out.print("X");
						break;
					case(TriquiGame.O):
						System.out.print("O");
						break;
					case(TriquiGame.N):
						System.out.print((i*3+j+1));
						break;
				}
				if (j < 2)
					System.out.print(" | ");
			}
			if (i < 2)
				System.out.println("\n----------");
		}
		System.out.println();
	}
	

	@Override
	public boolean acceptGame(String name) throws RemoteException {
		System.out.println(name + " want to play with you. Accept the challenge? (Y/N)");
		String response = Reader.getInstance().read();
		return response.trim().toUpperCase().equals("Y");
	}

	@Override
	public void startGame(String opponent, ITriquiGame triqui)
			throws RemoteException {
		System.out.println("\nHave you started the game against " + opponent);
		playing = true;
	}

	@Override
	public int playTurn() throws RemoteException {
		System.out.println("\nIt's your turn to play:");
		int move = Integer.parseInt(Reader.getInstance().read());	
		return move-1;
	}

	@Override
	public void endGame(boolean winner) throws RemoteException {
		if (winner) {
			System.out.println("\nYou won the game!");
		} else {
			System.out.println("\nYou lost the game.");
		}
		System.out.println("Game over.");
		manager.exit(this.name);
		playing = false;
	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}
	
	@Override
	public void updateBoard(int[] board) {
		printBoard(board);
	}
    
}