import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;

    // Rectangle object represents a rectangle
    private Rectangle button;

    public DrawPanel() {
        button = new Rectangle(155, 260, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 130;
        int y = 10;

        int index = 0;
        for (int row = 0; row < 3; row++) {
            Card c = hand.get(index);
            for (int col = 0; col < 3; col++) {
                c = hand.get(index);
                if (c.getHighlight()) {
                    // draw the border rectangle around the card
                    g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
                }

                // establish the location of the rectangle "hitbox"
                c.setRectangleLocation(x, y);

                g.drawImage(c.getImage(), x, y, null);
                x = x + c.getImage().getWidth() + 10;
                index++;
            }
            x = 130;
            y = y + c.getImage().getHeight() + 20;
        }

        // drawing the bottom button
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 158, 279);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        // left click
        if (e.getButton() == 1) {
            // if clicked is inside the button rectangle
            // aka --> did you click the button?
            if (button.contains(clicked)) {
                hand = Card.buildHand();
            }

            // go through each card
            // check if any of them were clicked on
            // if it was clicked, flip the card
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        // right click
        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}