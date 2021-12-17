package Character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, action;
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(c == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(c == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(c == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if(c == KeyEvent.VK_E){
            action = true;
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(c == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(c == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(c == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(c == KeyEvent.VK_E){
            action = false;
        }
    }
}
