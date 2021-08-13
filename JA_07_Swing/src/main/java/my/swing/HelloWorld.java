package my.swing;

import javax.swing.*;
import java.awt.*;

public class HelloWorld extends JFrame {

    JButton button = new JButton();
    JTextField text1 = new JTextField();

    public HelloWorld() {
        //this.setLayout(new GridLayout(2,1));
        this.setLayout(null);
        button.setText("Click me!");
        button.setBounds(5,40,100,40);
        button.addActionListener(e -> {
//            button.setText("Hello, world!");
            button.setText("Hello, " + text1.getText());
        });

        text1.setBounds(5,5,100,20);
        this.add(text1);
        this.add(button);
        this.setBounds(20,20,140,120);
    }

    public static void main(String[] args) {
        HelloWorld frame = new HelloWorld();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
