
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.*;
public class myQQ
{  
public static void main(String[] args) throws IOException
{  
   QQFrame frame = new QQFrame();
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
}
}

/**
A frame that contains a message panel
*/
class QQFrame extends JFrame
{
public QQFrame() throws IOException
{
   setTitle("我的QQ");
   setBounds(500,200,DEFAULT_WIDTH, DEFAULT_HEIGHT);
   JMenuBar menuBar = new JMenuBar();
   setJMenuBar(menuBar);
   JMenu caidan=new JMenu("菜单");
   menuBar.add(caidan);
   JMenuItem shezhi=new JMenuItem("设置");
   JMenuItem quit=new JMenuItem("退出");
   caidan.add(shezhi);
   caidan.add(quit);
   caidan.addSeparator();
   shezhi.addActionListener(new
	         ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	               dialog.setVisible(true); // pop up dialog
	            }
	         });
   quit.addActionListener(new
	         ActionListener()
	         {
	            public void actionPerformed(ActionEvent event)
	            {
	            	System.exit(0);
	            }
	         });
   final QQPanel panel= new QQPanel();
   
   add(panel);
   update.getpanel(panel);
   update.getmessage(panel.sendja());
   update.getdlg(dlg);
   dialog.getupdate(update);
   
}

public static final int DEFAULT_WIDTH = 500;
public static final int DEFAULT_HEIGHT = 570;  
private SetDialog dialog= new SetDialog(QQFrame.this);
private FileDialog dlg= new FileDialog(QQFrame.this);
private Update update=new Update();
}
class FileDialog extends JDialog
{
	public FileDialog(JFrame owner) throws IOException
	   {  
	      super(owner, "您有新文件!", true);
	      setBounds(500,300,170,80);
	      panel.add(Label);
	      panel.add(ok);
	      add(panel);
	      ok.addActionListener(new 
	         ActionListener() 
	         {  
	            @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent event) 
	            { 
	            	setVisible(false); 
	            } 
	         });
	      
	   }
	JButton ok = new JButton("确定");
	JLabel Label=new JLabel("您有文件需要接收！");
	JPanel panel = new JPanel();
}
class SetDialog extends JDialog
{  
   private static final int DEFAULT_WIDTH = 500;
   private static final int DEFAULT_HEIGHT = 240;
public SetDialog(JFrame owner) throws IOException
   {  
      super(owner, "设置", true);         
      ok.addActionListener(new 
         ActionListener() 
         {  
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent event) 
            { 
            	try {
					FileOutputStream f=new FileOutputStream("confirm.txt");
					PrintWriter pw = new PrintWriter(f);
					pw.println("true");
					pw.flush();
					pw.close();
					f.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	FileOutputStream fos;
				try {
					fos = new FileOutputStream("Record.txt");
					PrintWriter pw = new PrintWriter(fos);
					pw.println(TextFiedl1.getText());
					pw.println(TextFiedl2.getText());
					pw.println(TextFiedl3.getText());
					pw.println(TextFiedl4.getText());
					pw.println(TextFiedl5.getText());
					pw.flush();
					pw.close();
					fos.close();
					try {
						update.receive(TextFiedl1.getText());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SocketException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//confirm=true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	setVisible(false); 
            } 
         });
      cancel.addActionListener(new 
         ActionListener() 
         {  
            public void actionPerformed(ActionEvent event) 
            { 
            	setVisible(false); 
            } 
         });
      BufferedReader read = null;
      try {
    	  read = new BufferedReader(new FileReader("Record.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			FileOutputStream fos = new FileOutputStream("Record.txt");
	    	  PrintWriter pw = new PrintWriter(fos);
			e.printStackTrace();
		}
      finally
      {
    	  read = new BufferedReader(new FileReader("Record.txt"));
      }
      TextFiedl1.setText(read.readLine());
      TextFiedl2.setText(read.readLine());
      TextFiedl3.setText(read.readLine());
      TextFiedl4.setText(read.readLine());
      TextFiedl5.setText(read.readLine());
      read.close();
      JPanel panel = new JPanel();
      panel.add(Label1);
      panel.add(TextFiedl1);
      panel.add(Label2);
      panel.add(TextFiedl2);
      panel.add(Label3);
      panel.add(TextFiedl3);
      panel.add(Label4);
      panel.add(TextFiedl4);
      panel.add(Label5);
      panel.add(TextFiedl5);
      panel.add(ok);
      panel.add(cancel);
      add(panel);
      setBounds(500,300,DEFAULT_WIDTH, DEFAULT_HEIGHT);
   }
	JButton ok = new JButton("确定");
	JButton cancel = new JButton("取消");
	JLabel Label1=new JLabel("文本接收端口");
	JLabel Label2=new JLabel("文件接收端口");
	JLabel Label3=new JLabel("目标接收地址");
	JLabel Label4=new JLabel("目标文本端口");
	JLabel Label5=new JLabel("目标文件端口");
	JTextField TextFiedl1 = new JTextField(30);
	JTextField TextFiedl2 = new JTextField(30);
	JTextField TextFiedl3 = new JTextField(30);
	JTextField TextFiedl4 = new JTextField(30);
	JTextField TextFiedl5 = new JTextField(30);
	boolean confirm=false;
	Update update;
	
	public void getupdate(Update ud)
	{
		update=ud;
	}
	public boolean getconfirm()
	{
		return confirm;
	}
	
}
class Update
{
	public void receive(String port) throws NumberFormatException, SocketException
	{
		ds1 = new DatagramSocket(Integer.parseInt(port));
		thread = new Thread(new Runnable()
		{
			public void run()
			{
				byte buf[] = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf,buf.length);
				while(true)
				{
					try
					{
						ds1.receive(dp);
						String message=new String(buf,0,dp.getLength());
						if(message.charAt(0)=='$')
						{
							StringTokenizer x=new StringTokenizer(message,"$");
							filename=x.nextToken();
							filesize=x.nextToken();
							panel.setfileinfo(filename, filesize);
							dlg.setVisible(true);
						}
						else
						showmessage.setText(showmessage.getText() + new String(buf,0,dp.getLength()) + "\r\n");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	);
	thread.start();
	}
	public void getmessage(JTextArea msg)
	{
		showmessage=msg;
	}
	public String getfilename()
	{
		return filename;
	}
	public String getfilesize()
	{
		return filesize;
	}
	public void getpanel(QQPanel p)
	{
		panel=p;
	}
	public void getdlg(FileDialog d)
	{
		dlg=d;
	}
	private DatagramSocket ds1;
	private Thread thread;
	private JTextArea showmessage;
	private String filename;
	private String filesize;
	private QQPanel panel;
	private FileDialog dlg;
	//private confirm=true;
}
class QQPanel extends JPanel implements ActionListener
{
	public QQPanel() throws IOException
	{
		sendfile.addActionListener(this);
		receivefile.addActionListener(this);
		sendtext.addActionListener(this);
		add(showscro);
		add(NameLabel);
		add(NameText);
		add(editscro);
		add(sendfile);
		add(receivefile);
		add(sendtext);
		init();
		//receive();
		
	}
	public JTextArea sendja()
	{
		return showmessage;
	}
	public void init() throws IOException
	{
		BufferedReader record;
		try {
			record = new BufferedReader(new FileReader("Record.txt"));
			for(int i=0;i<5;i++)
			{
				data[i]=record.readLine();
			}
			record.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			FileOutputStream fos = new FileOutputStream("Record.txt");
			PrintWriter pw = new PrintWriter(fos);
			e.printStackTrace();
		}
		finally
		{
			record = new BufferedReader(new FileReader("Record.txt"));
			for(int i=0;i<5;i++)
			{
				data[i]=record.readLine();
			}
			record.close();
		}
	}
	private JTextArea showmessage=new JTextArea(15,40);
	private JTextArea editmessage=new JTextArea(10,40);
	private JScrollPane showscro = new JScrollPane(showmessage,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JScrollPane editscro = new JScrollPane(editmessage,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JLabel NameLabel=new JLabel("昵称");
	private JTextField NameText = new JTextField(30);
	private JButton sendfile = new JButton("发送文件");  
	private JButton receivefile = new JButton("接收文件");  
	private JButton sendtext = new JButton("发送");
	private String[] data=new String[5];
	private DatagramSocket ds1;
	private DatagramSocket ds2;
	private boolean confirm=false;
	private JFileChooser chooser=new JFileChooser();
	private String SourceFile;
	private String TargetFile;
	private long FileSize=1;
	private ServerSocket server;
	private Socket client;
	private byte[] buf = new byte[(int) FileSize];
	private String filename;
	private String filesize;
	public void setfileinfo(String name,String size)
	{
		filename=name;
		filesize=size;
	}
	public void setconfirm(boolean ifconfirm)
	{
		confirm=ifconfirm;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String B = e.getActionCommand();
		if(B.equals("发送文件"))
		{
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setCurrentDirectory(new File("/Users/macbookpro/Desktop"));
			int result = chooser.showDialog(sendfile, "选择");
			if(result==JFileChooser.APPROVE_OPTION){
				
				SourceFile=chooser.getSelectedFile().getPath();
				FileSize = chooser.getSelectedFile().length();
				buf=new byte[(int) FileSize];
			}
			try {
				server=new ServerSocket(Integer.parseInt(data[4]));
				BufferedReader confm = new BufferedReader(new FileReader("confirm.txt"));
				confirm=Boolean.parseBoolean(confm.readLine());
				confm.close();
				if(confirm==true)
				{
					BufferedReader record = new BufferedReader(new FileReader("Record.txt"));
					for(int i=0;i<5;i++)
					{
						data[i]=record.readLine();
					}
					record.close();
				}
				ds2 = new DatagramSocket();
				StringTokenizer x=new StringTokenizer(SourceFile,"/");
				String y=null;
				while(x.hasMoreElements())
				{
					y=x.nextToken();
				}
				String filemessage="$"+y+"$"+FileSize;
				byte[] m=filemessage.getBytes();
				DatagramPacket dp = new DatagramPacket(m,m.length,InetAddress.getByName(data[2]),Integer.parseInt(data[3]));
				ds2.send(dp);
				confirm=false;
				try {
					FileOutputStream f=new FileOutputStream("confirm.txt");
					PrintWriter pw = new PrintWriter(f);
					pw.println(confirm);
					pw.flush();
					pw.close();
					f.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				client=server.accept();
				byte[] bu = new byte[(int) FileSize];
				DataInputStream dis = new DataInputStream(new FileInputStream(new File(SourceFile)));
				DataOutputStream dos = new DataOutputStream(client.getOutputStream());
				//int blocknum=(int) (FileSize/2048+1);
				dis.read(bu);
				dos.write(bu);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				client.close();
				server.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try
			{
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		if(B.equals("接收文件"))
		{
			chooser.setCurrentDirectory(new File("/Users/macbookpro/Desktop"));
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = chooser.showDialog(receivefile, "选择");
			if(result==JFileChooser.APPROVE_OPTION){
				TargetFile=chooser.getSelectedFile().getPath();
			}
			try {
				client=new Socket(InetAddress.getByName(data[2]),Integer.parseInt(data[1]));
				DataInputStream dis = new DataInputStream(client.getInputStream());
				byte []file = new byte [Integer.parseInt(filesize)]; 
				//int blocknum=(int)(Long.parseLong(filesize)/2048+1);
				TargetFile+="/"+filename;
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(TargetFile)));
				dis.read(file);
				dos.write(file);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(B.equals("发送"))
		{
			try
			{
				BufferedReader confm = new BufferedReader(new FileReader("confirm.txt"));
				confirm=Boolean.parseBoolean(confm.readLine());
				confm.close();
				if(confirm==true)
				{
					BufferedReader record = new BufferedReader(new FileReader("Record.txt"));
					for(int i=0;i<5;i++)
					{
						data[i]=record.readLine();
					}
					record.close();
				}
				ds2 = new DatagramSocket();
				Date now = new Date();
				DateFormat d=DateFormat.getDateTimeInstance();
				byte[] buf = (NameText.getText()+"("+d.format(now)+")"+":\n"+editmessage.getText()).getBytes();
				DatagramPacket dp = new DatagramPacket(buf,buf.length,InetAddress.getByName(data[2]),Integer.parseInt(data[3]));
				ds2.send(dp);
				editmessage.setText("");
				editmessage.requestFocus(true);
				showmessage.setText(showmessage.getText()+new String(buf,0,dp.getLength()) + "\r\n");
				confirm=false;
				try {
					FileOutputStream f=new FileOutputStream("confirm.txt");
					PrintWriter pw = new PrintWriter(f);
					pw.println(confirm);
					pw.flush();
					pw.close();
					f.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
