import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LabelPlay {

private JFrame frame;
private JLabel label;
private Random rand;



public LabelPlay() {
    initialize();
}

private void initialize() {
  

    label = new JLabel("YEEEHAH!");
    label.setBounds(101, 62, 54, 21);
   

    JButton btnAction = new JButton("Action!");
    rand = new Random();
    btnAction.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            int a = rand.nextInt(90)+10;
            int b = rand.nextInt(90)+10;
            int c = rand.nextInt(640)+10;
            int d = rand.nextInt(500)+10;
            label.setBounds(a, b, c, d);                
        }
    });
    btnAction.setBounds(524, 427, 89, 23);
    frame.getContentPane().add(btnAction);
}
}