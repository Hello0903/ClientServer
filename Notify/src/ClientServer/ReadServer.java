package ClientServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ReadServer extends Thread {
	private Socket server;

	public ReadServer(Socket server) {
		this.server = server;
	}

	@Override
	public void run() {
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(server.getInputStream());
			while (true) {
				String sms = dis.readUTF();
				String[] pub = sms.split(" ", 3);
//				System.out.println(pub[0]);
				switch (pub[0]) {
				case "PUB":
					if (Server.mapuser.containsKey(pub[1]))
						for (Socket item : Server.mapuser.get(pub[1])) {
							DataOutputStream dos = new DataOutputStream(item.getOutputStream());
							dos.writeUTF("Chanel: " + pub[1] + " sent new notify: " + pub[2]);
						}
					System.out.println("Server handle public content" + pub[2]);
					break;
				case "SUB":
					DataOutputStream dos = new DataOutputStream(server.getOutputStream());
					if (Server.mapuser.containsKey(pub[1])) {
						if (Server.mapuser.get(pub[1]).contains(server)) {
							dos.writeUTF("You was in chanel.");
							continue;
						}
						Server.mapuser.get(pub[1]).add(server);
						System.out.println("Chanel has " + Server.mapuser.get(pub[1]).size() + " Member");
						dos.writeUTF("You Subcriber chanel: " + pub[1]);
					} else {
						ArrayList<Socket> soc = new ArrayList<Socket>();
						soc.add(server);
						Server.mapuser.put(pub[1], soc);
						System.out.println("Server create new chanel " + server);
						dos.writeUTF("You Subcriber chanel: " + pub[1]);
					}
					break;
				}
				System.out.println(sms);
			}
		} catch (Exception e) {
			// TODO: handle exception
			try {
				dis.close();
				server.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
