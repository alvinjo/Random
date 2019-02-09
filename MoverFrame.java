package CursorMover;

import javax.swing.*;

public class MoverFrame extends JFrame {

    private JButton start;
    private JButton stop;
    private Mover mover;

    public static void main(String[] args) {
        MoverFrame mFrame = new MoverFrame();
        mFrame.setVisible(true);
    }

    public MoverFrame(){
        JPanel panel = new JPanel();
        start = new JButton("START");
        stop = new JButton("STOP");
        setSize(250,90);
        panel.add(start);
        panel.add(stop);
        add(panel);
        setTitle("Cursor Mover");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        start.addActionListener(a -> mover.startMoving());
        stop.addActionListener(a -> mover.stopMoving());

        mover = new Mover();
    }

}