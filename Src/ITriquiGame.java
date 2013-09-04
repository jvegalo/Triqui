/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Src;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public interface ITriquiGame {
    /*
     * Inicia un juego
     */
    public void     Start();
    
    /*
     * Realiza una jugada y alterna el turno.
     * retorna verdadero si la jugada es válida
     * retorna falso si la jugada es inválida
     * 
     * Esta son las posiciones que se utilizarán.
     * 
     * [1] [2] [3]
     * [4] [5] [6]
     * [7] [8] [9]
     */
    public boolean  Play(int pos);
    
    
    
    
    
    /*
     * Devuelve el simbolo del jugador actual (X o O)
     */
    public String   Player();
    
    
    
    
    
    
    /*
     * Devuelve una versión texto del tablero del triqui
     */
    public String   Board();
    
    
    
    
    
    
    
    /*
     * verifica si hubo ganador, retorna el Simbolo del ganador, o "no hubo"
     */
    public String   TestWinner();
}
