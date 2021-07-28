import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyFrame extends JFrame implements ActionListener {
    JPanel panel;
    JButton[] buttons=new JButton[25];
    JTextField guess;
    JTextField message;
    JLabel up,down;
    int g=5;
    int num;
    Random r;
    JButton reset;
    MyFrame(){
        reset=new JButton("reset");
        reset.setFocusable(false);
        reset.addActionListener(this);

        message=new JTextField();
        message.setBounds(50,50,300,50);
        message.setBorder(null);
        message.setFont(new Font(null,Font.BOLD,30));
        message.setForeground(new Color(0x11AA21));
        message.setBackground(new Color(0x000000));
        message.setEditable(false);

        up=new JLabel(new ImageIcon("uparrow.png"));
        down=new JLabel(new ImageIcon("downarrow.png"));
        up.setBounds(40,120,300,400);
        down.setBounds(350,120,300,400);
        up.setText("Try Going Higher");
        down.setText("Try Going Lower");
        up.setForeground(new Color(0x21AA2A));
        up.setIconTextGap(5);
        up.setHorizontalTextPosition(SwingConstants.CENTER);
        up.setVerticalTextPosition(SwingConstants.BOTTOM);
        up.setFont(new Font(null,Font.PLAIN,20));
        down.setForeground(new Color(0x21AA2A));
        down.setIconTextGap(5);
        down.setHorizontalTextPosition(SwingConstants.CENTER);
        down.setVerticalTextPosition(SwingConstants.BOTTOM);
        down.setFont(new Font(null,Font.PLAIN,20));

        guess=new JTextField();
        guess.setBackground(new Color(0x323232));
        guess.setBounds(450,50,250,50);
        //guess.setText("Guesses left:"+g);
        guess.setForeground(new Color(0x11AA21));
        guess.setFont(new Font("MS",Font.PLAIN,35));
        guess.setHorizontalAlignment(SwingConstants.CENTER);
        guess.setBorder(null);

        panel=new JPanel();
        panel.setBounds(10,510,690,150);
        panel.setLayout(new GridLayout(3,10,6,6));
        panel.setBackground(new Color(0x212121));

        for(int i=0;i<25;i++){
            buttons[i]=new JButton(String.valueOf(i+1));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(0x1AAAA2));
            buttons[i].setFont(new Font("Comic Sans",Font.BOLD,30));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
            panel.setBorder(null);
        }
        panel.add(reset);
       this.setSize(720,720);
       this.setTitle("Guess the Number");
       this.getContentPane().setBackground(new Color(0x000000));
       this.setLocationRelativeTo(null);
       this.setLayout(null);
       this.setResizable(false);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.add(panel);
       this.add(up);
       this.add(down);
       this.add(guess);
       this.add(message);
       start();
       this.setVisible(true);
    }
    public void start(){
        g=5;
        message.setText("");
        guess.setText("Guesses left:"+g);
        up.setVisible(false);
        down.setVisible(false);
        r=new Random();
        num=r.nextInt(25)+1;
        for(int i=0;i<25;i++)
            buttons[i].setEnabled(true);
    }
    public void GameOver(){
        for(int i=0;i<25;i++)
            buttons[i].setEnabled(false);
        message.setText("Number was:"+num);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reset)
            start();

        for(int i=0;i<25;i++){
            if(e.getSource()==buttons[i]){
                if(num==(i+1)){
                    message.setText("Congratulations!!");
                    for(int j=0;j<25;j++)
                        buttons[j].setEnabled(false);
                }
                if(num>(i+1)){
                    g--;
                    if(g==0) GameOver();
                    guess.setText("Guesses left:"+g);
                    up.setVisible(true);
                    down.setVisible(false);
                    buttons[i].setEnabled(false);
                }
                if(num<(i+1)){
                    g--;
                    if(g==0) GameOver();
                    guess.setText("Guesses left:"+g);
                    up.setVisible(false);
                    down.setVisible(true);
                    buttons[i].setEnabled(false);
                }
            }
        }

    }
}
