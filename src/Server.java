/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Server implements ActionListener {
    
    JPanel p; //global because of scope
    JTextField t1;
    JButton b1;
    static JPanel a1;
    static ServerSocket skt;
    static Socket sk;
    static DataInputStream din;
    static DataOutputStream dout;
    
    static JFrame f1 = new JFrame();
    
    static Box vertical = Box.createVerticalBox();
    
    
    JLabel l4;
    Timer t;
    
    Boolean typing; //by default false
    
    Server()
    {
        p = new JPanel();
        p.setLayout(null);
        p.setBackground( new Color(7, 94, 84) );
        p.setBounds(0, 0, 450, 70);
        f1.add(p);
        //above code for inserting panel on frame
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("3.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); //image cant be sent directly..therefore converted to imageicon
        JLabel l1 = new JLabel(i3); // why label..images cant be shown directly
        l1.setBounds(5,17,30,30); //location length - breadth from up and down and then image size
        p.add(l1);
        //adding image on panel .. back button
        //adding mouse event on back button..close on click
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
         
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("Rachael.png"));
        Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5); //image cant be sent directly..therefore converted to imageicon
        JLabel l2 = new JLabel(i6); // why label..images cant be shown directly
        l2.setBounds(40,5,60,60); //location length - breadth from up and down and then image size
        p.add(l2);
        //adding dp on panel
        
        JLabel l3 = new JLabel("Rachel");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110,15,100,18);
        p.add(l3);
        
        l4 = new JLabel("Active now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110,35,100,20);
        p.add(l4);
        t = new Timer(1, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(!typing){
                    l4.setText("Active Now");
                    
                    
                }
            }
        });
        
        t.setInitialDelay(2000);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("video.png"));
        Image i8 = i7.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8); //image cant be sent directly..therefore converted to imageicon
        JLabel l5 = new JLabel(i9); // why label..images cant be shown directly
        l5.setBounds(290,20,35,30); //location length - breadth from up and down and then image size
        p.add(l5);
        //adding video call image on panel
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11); //image cant be sent directly..therefore converted to imageicon
        JLabel l6 = new JLabel(i12); // why label..images cant be shown directly
        l6.setBounds(350,20,35,30); //location length - breadth from up and down and then image size
        p.add(l6);
        //adding calling image on panel
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14); //image cant be sent directly..therefore converted to imageicon
        JLabel l7 = new JLabel(i15); // why label..images cant be shown directly
        l7.setBounds(410,20,13,25); //location length - breadth from up and down and then image size
        p.add(l7);
        //adding 3dots on panel
        
        //textarea..where all messages will be displayed
        a1 = new JPanel();
//        a1.setBounds(5,75,440,375);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
//        a1.setBackground(Color.lightGray);
//        f1.add(a1);
        
        JScrollPane sp = new JScrollPane(a1);
        sp.setBounds(5,75,440,375);
        sp.setBorder(BorderFactory.createEmptyBorder()); //to remove border of panel jisme border lga
      
        
        ScrollBarUI ui = new BasicScrollBarUI(){
            protected JButton createDecreaseButton(int orientation){
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(new Color(7, 94, 84));
                button.setForeground(Color.white);
                this.thumbColor = new Color(7, 94, 84);
                return button;
            }
            
            protected JButton createIncreaseButton(int orientation){
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(new Color(7, 94, 84));
                button.setForeground(Color.white);
                this.thumbColor = new Color(7, 94, 84);
                return button;
            }
        };
        
        sp.getVerticalScrollBar().setUI(ui);
          f1.add(sp);
        
        t1 = new JTextField();
        t1.setBounds(5,455,318,40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f1.add(t1);
        //textarea..where message will be written
        
        t1.addKeyListener(new KeyAdapter() {
               public void keyPressed(KeyEvent k){
                   
                   l4.setText("Typing...");
                   
                   t.stop();
                   typing = true;
               }
               
               public void keyReleased(KeyEvent k){
                   typing = false;
                   if(!t.isRunning()){
                       t.start();
                   }
               }
        });
        
        b1 = new JButton("Send");
        b1.setBounds(328, 455, 118, 40);
        b1.setBackground( new Color(7, 94, 84) );
        b1.setForeground(Color.white);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        f1.add(b1);
        //send button
        
        
        f1.getContentPane().setBackground(Color.white);
        f1.setLayout(null);
        f1.setSize(450,500);
        f1.setUndecorated(true);
        f1.setLocation(200,100); // on screen where frame will be visible
        f1.setVisible(true); //by default false
        
    }
    
    public void actionPerformed(ActionEvent ae){
        
        try {
        String out = t1.getText();
        JPanel p2 = formatLabel(out);
        
        a1.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        a1.add(vertical, BorderLayout.PAGE_START);
        
        
//        a1.add(p2);
        dout.writeUTF(out);
        t1.setText("");
        }
        catch(Exception ex){}
    }
    
    public static JPanel formatLabel(String out)
    {
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        JLabel l1 = new JLabel("<html><p style=\"width : 150px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahome", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15,15,15,50)); //first arguement padding
        
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        
        p3.add(l1);
        p3.add(l2);
        return p3;
        
    }
            
    
    public static void main(String args[])
    {
        new Server().f1.setVisible(true); //object made to call constructor
        
        String msgInput = "";
        try{
            skt = new ServerSocket(6001); //can pass any port as long as it is not occupied
            
            while(true)
            {
                sk = skt.accept();
                din = new DataInputStream(sk.getInputStream());
                dout = new DataOutputStream(sk.getOutputStream());
                
                while(true)
                {
                    msgInput = din.readUTF();
                    JPanel p2 = formatLabel(msgInput);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(p2, BorderLayout.LINE_START);
                    vertical.add(left);
                    f1.validate();
                    
                    
                }
                
            }
            
        }
        catch(Exception ex){
            
        }
    }
    
}
