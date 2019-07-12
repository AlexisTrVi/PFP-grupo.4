package UsuarioFiee;

import java.net.*;



import java.io.*;
import java.util.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class client_frame extends javax.swing.JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean estaconectado = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
  
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    


    
    public void userAdd(String data) 
    {
         users.add(data);
    }


    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " ahora está desconectado.\n");
    }
    


    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             
         }
    }
    
   
    public void sendDisconnect() 
    {
        String bye = (username + ": :Desconectado");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("No se pudo enviar el mensaje de desconexión.\n");
        }
    }

    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Desconectado.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Falla al conectar. \n");
        }
        estaconectado = false;
        tf_username.setEditable(true);

    }
    
    public client_frame() 
    {
    	getContentPane().setBackground(Color.LIGHT_GRAY);
    	setBackground(Color.BLACK);
        initComponents();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    
    
    @SuppressWarnings("unchecked")
   
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        b_anonymous = new javax.swing.JButton();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); 
        setResizable(false);

        lb_address.setText("Dir. Ip:");

        tf_address.setText("Direcci\u00F3n local");
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });

        lb_username.setText("Nick:");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        b_connect.setText("\u00A1Conectarse!");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("\u00A1Desconectarse! :c");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        b_anonymous.setText("Entrar an\u00F3nimamente :P");
        b_anonymous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_anonymousActionPerformed(evt);
            }
        });

        b_send.setText("\u00A1Enviar Mensaje!");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });
        ta_chat = new javax.swing.JTextArea();
        ta_chat.setSelectedTextColor(SystemColor.text);
        
                ta_chat.setColumns(20);
                ta_chat.setRows(5);
        
        JLabel lblGrupoPoo = new JLabel("Grupo 4 Poo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(tf_chat, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
        							.addGap(29)
        							.addComponent(b_send, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        								.addComponent(lb_username, GroupLayout.PREFERRED_SIZE, 62, Short.MAX_VALUE)
        								.addComponent(lb_address, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        							.addGap(18)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(tf_address, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        								.addComponent(tf_username))
        							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        								.addGroup(layout.createSequentialGroup()
        									.addGap(12)
        									.addComponent(b_connect, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(b_disconnect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        								.addGroup(layout.createSequentialGroup()
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addComponent(b_anonymous, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
        							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
        						.addComponent(lblGrupoPoo)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(21)
        					.addComponent(ta_chat, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(16)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lb_address)
        				.addComponent(tf_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(b_anonymous))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(tf_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addComponent(b_disconnect)
        					.addComponent(b_connect))
        				.addComponent(lb_username))
        			.addGap(18)
        			.addComponent(ta_chat, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
        			.addGap(15)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(tf_chat, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        				.addComponent(b_send, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(lblGrupoPoo)
        			.addGap(5))
        );
        getContentPane().setLayout(layout);

        pack();
    }

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {
       
    }

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {
    	
    }

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {
        if (estaconectado == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":conectado.:Conectado");
                writer.flush(); 
                estaconectado = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Ya estas conectado :)  \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (estaconectado == true) 
        {
            ta_chat.append("Ya estás conectado. \n");
        }
    }

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {
        sendDisconnect();
        Disconnect();
    }

    private void b_anonymousActionPerformed(java.awt.event.ActionEvent evt) {
        tf_username.setText("");
        if (estaconectado == false) 
        {
            String anon="anon";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;
            
            tf_username.setText(anon);
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush(); 
                estaconectado = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (estaconectado == true) 
        {
            ta_chat.append("You are already connected. \n");
        }        
    }

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
               writer.flush(); 
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
   
    private javax.swing.JButton b_anonymous;
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_username;
}

