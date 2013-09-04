/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;



public class TriquiGame implements ITriquiGame{
    boolean turn;
    
    String X = "[X]";
    String O = "[O]";
    String E = "[ ]";
    
    String[] board = new String[9];
    
    public void Start() {
        int i = 1;
        for (int x=0;x<9;x++) {
            board[x] = E; 
            }
        }
    
    public boolean Play(int pos) {
        boolean result = true;
        if (pos < 9 && !board[pos].equals(X) && !board[pos].equals(O)) {
            if (turn) {
                board[pos] = X;
                turn=!turn;
                
            } else {
                board[pos] = O;
                turn=!turn;
            }
        } else
            result = false;
        return result;
    }
    
    public String Player() {
        String result = "";
            if (turn) {
                result = X;
                
            } else {
                result = O;
            }
        return result;
    }
    
    public String Board() {
        StringBuffer sb = new StringBuffer();
        int pos = 0;
        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++) {
                sb.append(board[pos++]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public String TestWinner() {
        String result ="N";
        
        if (board[0].equals(X) && board[1].equals(X) && board[2].equals(X))
            result = X;
        
        if (board[3].equals(X) && board[4].equals(X) && board[5].equals(X))
            result = X;
        
        if (board[6].equals(X) && board[7].equals(X) && board[8].equals(X))
            result = X;
        
        
        
        if (board[0].equals(X) && board[3].equals(X) && board[6].equals(X))
            result = X;
        
        if (board[1].equals(X) && board[4].equals(X) && board[7].equals(X))
            result = X;
        
        if (board[2].equals(X) && board[5].equals(X) && board[8].equals(X))
            result = X;
        
        
        if (board[0].equals(X) && board[4].equals(X) && board[8].equals(X))
            result = X;
        
        if (board[2].equals(X) && board[4].equals(X) && board[6].equals(X))
            result = X;
        
        
        if (board[0].equals(O) && board[1].equals(O) && board[2].equals(O))
            result = O;
        
        if (board[3].equals(O) && board[4].equals(O) && board[5].equals(O))
            result = O;
        
        if (board[6].equals(O) && board[7].equals(O) && board[8].equals(O))
            result = O;
        
        if (board[0].equals(O) && board[3].equals(O) && board[6].equals(O))
            result = O;
        
        if (board[1].equals(O) && board[4].equals(O) && board[7].equals(O))
            result = O;
        
        if (board[2].equals(O) && board[5].equals(O) && board[8].equals(O))
            result = O;
        
        
        if (board[0].equals(O) && board[4].equals(O) && board[8].equals(O))
            result = O;
        
        if (board[2].equals(O) && board[4].equals(O) && board[6].equals(O))
            result = O;
        
        return result;
    }    
}