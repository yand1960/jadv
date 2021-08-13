package my.supercalc;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

public class CalcFrame extends JFrame {

    JTextField text1 = new JTextField();
    JTextField text2 = new JTextField();
    JTextField text3 = new JTextField();
    JButton button = new JButton();
    JProgressBar bar = new JProgressBar();

    public CalcFrame() {
        this.setBounds(5,5, 90, 190);
        this.setLayout(null);
        text1.setBounds(5,5,90,20);
        text2.setBounds(5,25,90,20);
        button.setBounds(5,50,90,40);
        button.setText("+");
        text3.setBounds(5,95,90,20);
        bar.setBounds(5,120,90,15);

        this.add(text1);
        this.add(text2);
        this.add(text3);
        this.add(button);
        this.add(bar);

        button.addActionListener(e -> {
            int x = Integer.parseInt(text1.getText());
            int y = Integer.parseInt(text2.getText());
            SlowCalc slow = new SlowCalc();
            Thread thread = new Thread( () -> {
                //Так просто, но так нелья:
                //UI элементы можно обновлять только
                // из потока, в котором они созданы
//                slow.addProgressListener( percent -> {
//                    bar.setValue(percent);
//                });
//                int z = slow.plus(x, y);
//                text3.setText(String.valueOf(z));

                slow.addProgressListener( percent -> {
                    EventQueue.invokeLater(() -> {
                        bar.setValue(percent);
                    });
                });
                int z = slow.plus(x, y);
                EventQueue.invokeLater(() -> {
                    text3.setText(String.valueOf(z));
                });

            });
            thread.start();
        });
    }

    public static void main(String[] args) {
        CalcFrame frame = new CalcFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
