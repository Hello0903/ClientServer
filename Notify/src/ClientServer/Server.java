package ClientServer;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
public class Server {
	private int port;
	public static ArrayList<Socket> listUser ;
	public static Map<String,ArrayList<Socket>> mapuser = new HashMap<String,ArrayList<Socket>>();
	public Server(int port) {
		this.port = port;
	}
	
	private void execute() throws IOException {
		ServerSocket server = new ServerSocket(port);
		//WriteServer write = new WriteServer();
		//write.start();
		System.out.println("Server openning ... ");
		while(true) {
			Socket socket = server.accept();
			Server.listUser.add(socket);
			ReadServer read = new ReadServer(socket);
			read.start();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Server.listUser = new ArrayList<Socket>();
		Server server = new Server(9999);
		server.execute();
	}
//	public static void main (String[] args) throws IOException {
//		
//			ServerSocket ss = new ServerSocket(5000);
//			Socket s = ss.accept();
//			List<Socket> listsc = new ArrayList<Socket>();
//			System.out.println("Client connect");
//			DataInputStream din = new DataInputStream(s.getInputStream());
//			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//			String str = "", str2 = "";
//			while (!str.equals("stop")) {
//				str = din.readUTF();
//				System.out.println("client says: " + str);
//				str2 = br.readLine();
//				dout.writeUTF(str2);
//				dout.flush();
//			}
//			din.close();
//			s.close();
//			ss.close();
//			
//	}
}

