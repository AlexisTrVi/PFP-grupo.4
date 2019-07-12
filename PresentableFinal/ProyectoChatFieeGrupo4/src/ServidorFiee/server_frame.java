package ServidorFiee;

import java.io.*;



import java.net.*;
import java.util.*;


public class server_frame extends javax.swing.JFrame 
{
	   ArrayList clientOutputStreams;
	   ArrayList<String> users;

	   public class ClientHandler implements Runnable	
	   {
	       BufferedReader reader;
	       Socket sock;
	       PrintWriter client;

	       public ClientHandler(Socket clientSocket, PrintWriter user) 
	       {
	            client = user;
	            try 
	            {
	                sock = clientSocket;
	                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
	                reader = new BufferedReader(isReader);
	            }
	            catch (Exception ex) 
	            {
	                ta_chat.append("error... \n");
	            }

	       }

	       @Override
	       public void run() 
	       {
	            String message, connect = "Conectado", disconnect = "Desconectado", chat = "Chat" ;
	            String[] data;

	            try 
	            {
	                while ((message = reader.readLine()) != null) 
	                {
	                    ta_chat.append("Recibido: " + message + "\n");
	                    data = message.split(":");
	                    
	                    for (String token:data) 
	                    {
	                        ta_chat.append(token + "\n");
	                    }

	                    if (data[2].equals(connect)) 
	                    {
	                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
	                        userAdd(data[0]);
	                    } 
	                    else if (data[2].equals(disconnect)) 
	                    {
	                        tellEveryone((data[0] + ":te has desconectado." + ":" + chat));
	                        userRemove(data[0]);
	                    } 
	                    else if (data[2].equals(chat)) 
	                    {
	                        tellEveryone(message);
	                    } 
	                    else 
	                    {
	                        ta_chat.append("No se cumplieron las condiciones. \n");
	                    }
	                } 
	             } 
	             catch (Exception ex) 
	             {
	                ta_chat.append("Perdió una conexión. \n");
	                ex.printStackTrace();
	                clientOutputStreams.remove(client);
	             } 
		} 
	    }

	    public server_frame() 
	    {
	        initComponents();
	    }

	    @SuppressWarnings("unchecked")
	    
	    private void initComponents() {

	        jScrollPane1 = new javax.swing.JScrollPane();
	        ta_chat = new javax.swing.JTextArea();
	        b_start = new javax.swing.JButton();
	        b_end = new javax.swing.JButton();
	        b_users = new javax.swing.JButton();
	        b_clear = new javax.swing.JButton();
	        lb_name = new javax.swing.JLabel();

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setTitle("Chat - Server's frame");
	        setName("server"); // NOI18N
	        setResizable(false);

	        ta_chat.setColumns(20);
	        ta_chat.setRows(5);
	        jScrollPane1.setViewportView(ta_chat);

	        b_start.setText("INICIAR :)");
	        b_start.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                b_startActionPerformed(evt);
	            }
	        });

	        b_end.setText("FINALIZAR :C");
	        b_end.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                b_endActionPerformed(evt);
	            }
	        });

	        b_users.setText("Usuarios en l\u00EDnea :)");
	        b_users.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                b_usersActionPerformed(evt);
	            }
	        });

	        b_clear.setText("Limpiar historial");
	        b_clear.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                b_clearActionPerformed(evt);
	            }
	        });

	        lb_name.setText("Grupo 4 POO FIEE");
	        lb_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jScrollPane1)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(b_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(b_start, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
	                            .addComponent(b_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(b_users, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
	                .addContainerGap())
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(lb_name)
	                .addGap(209, 209, 209))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(b_start)
	                    .addComponent(b_users))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(b_clear)
	                    .addComponent(b_end))
	                .addGap(4, 4, 4)
	                .addComponent(lb_name))
	        );

	        pack();
	    }

	    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {
	        try 
	        {
	            Thread.sleep(5000);                
	        } 
	        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
	        
	        tellEveryone("Servidor: se está deteniendo y todos los usuarios serán desconectados.\n:Chat");
	        ta_chat.append("Server detenido... \n");
	        
	        ta_chat.setText("");
	    }

	    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {
	        Thread starter = new Thread(new ServerStart());
	        starter.start();
	        
	        ta_chat.append("Servidor conectado\n");
	    }

	    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {
	        ta_chat.append("\n Usuario en linea : \n");
	        for (String current_user : users)
	        {
	            ta_chat.append(current_user);
	            ta_chat.append("\n");
	        }    
	        
	    }

	    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {
	        ta_chat.setText("");
	    }

	    public static void main(String args[]) 
	    {
	        java.awt.EventQueue.invokeLater(new Runnable() 
	        {
	            @Override
	            public void run() {
	                new server_frame().setVisible(true);
	            }
	        });
	    }
	    
	    public class ServerStart implements Runnable 
	    {
	        @Override
	        public void run() 
	        {
	            clientOutputStreams = new ArrayList();
	            users = new ArrayList();  

	            try 
	            {
	                ServerSocket serverSock = new ServerSocket(2222);

	                while (true) 
	                {
					Socket clientSock = serverSock.accept();
					PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
					clientOutputStreams.add(writer);

					Thread listener = new Thread(new ClientHandler(clientSock, writer));
					listener.start();
					ta_chat.append("Connectado. \n");
	                }
	            }
	            catch (Exception ex)
	            {
	                ta_chat.append("Error conexion. \n");
	            }
	        }
	    }
	    
	    public void userAdd (String data) 
	    {
	        String message, add = ": :Connect", done = "Server: :Done", name = data;
	        ta_chat.append("antes de " + name + "adicional. \n");
	        users.add(name);
	        ta_chat.append("desoues de " + name + " adicional. \n");
	        String[] tempList = new String[(users.size())];
	        users.toArray(tempList);

	        for (String token:tempList) 
	        {
	            message = (token + add);
	            tellEveryone(message);
	        }
	        tellEveryone(done);
	    }
	    
	    public void userRemove (String data) 
	    {
	        String message, add = ": :Conectado", done = "Servidor: :Realizado", name = data;
	        users.remove(name);
	        String[] tempList = new String[(users.size())];
	        users.toArray(tempList);

	        for (String token:tempList) 
	        {
	            message = (token + add);
	            tellEveryone(message);
	        }
	        tellEveryone(done);
	    }
	    
	    public void tellEveryone(String message) 
	    {
		Iterator it = clientOutputStreams.iterator();

	        while (it.hasNext()) 
	        {
	            try 
	            {
	                PrintWriter writer = (PrintWriter) it.next();
			writer.println(message);
			ta_chat.append("Enviando: " + message + "\n");
	                writer.flush();
	                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

	            } 
	            catch (Exception ex) 
	            {
			ta_chat.append("Error al decirle a todos. \n");
	            }
	        } 
	    }
	    
	    
	    private javax.swing.JButton b_clear;
	    private javax.swing.JButton b_end;
	    private javax.swing.JButton b_start;
	    private javax.swing.JButton b_users;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JLabel lb_name;
	    private javax.swing.JTextArea ta_chat;
	    
	}
