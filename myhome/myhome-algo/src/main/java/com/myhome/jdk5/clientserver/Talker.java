package com.myhome.jdk5.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Talker {

	// Class Variables

	static Vector<Subscriber> connectiontable = new Vector<Subscriber>();

	static int nextid = 1; // Increasing i.d.

	public Talker() {
		// Parent thread - create a server socket and await a connection
		ServerSocket server = null;
		Socket s = null;

		try {
			server = new ServerSocket(8080);
			while ((s = server.accept()) != null) {

				Subscriber client = new Subscriber(this, s);
				// Save talker into vector

			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// should add finally block to close down
	}

	// ///////////////////////////// Main method ////////////////////////

	public static void main(String[] args) {
		new Talker();
	}

	// /////////////// Constructor for a new thread //////////////////////

	class Subscriber implements Runnable {
		private Talker server;
		private Thread _t;
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		private int id; // i.d. of the connection
		private String from_name; // name of host connecting

		public Subscriber(Talker server, Socket from) {
			this.server = server;
			this.socket = from;
			this.id = nextid++;
			InetAddress source = socket.getInetAddress();
			from_name = source.getHostName();
			try {
				out = new PrintWriter(new OutputStreamWriter(socket
						.getOutputStream()));
				in = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
			} catch (Exception e) {
			}

			_t = new Thread(this); // instanciation du thread
			_t.setDaemon(true);
			_t.start(); // demarrage du thread, la fonction run() est ici lancŽe

			connectiontable.addElement(this);
		}

		public void run() {

			String line = " [User has just logged in x] ";
			try {
				while (true) {
					boolean done = false;

					// read a line from the user

					if (line == null) {
						try {
							out.print(">: "); // prompt,flush,read
							out.flush();
							line = in.readLine();
						} catch (Exception e) {
							System.out.println(e);
							done = true; // force exit if there's a problem
						}
					}

					// echo the line (with a header) to all users

					String outline = from_name + " " + id + ": " + line;
					int k;
					for (k = 0; k < connectiontable.size(); k++) {
						Subscriber person = (Subscriber) connectiontable
								.elementAt(k);
						person.out.println(outline);
						person.out.flush(); // Vital - ensure it is sent!
					}

					// clear out the user if they're done

					line = null;
				}
			} catch (Exception e) {
			} finally // finally se produira le plus souvent lors de la
			// deconnexion du client
			{
				try {
					// on indique ˆ la console la deconnexion du client
					System.out.println("Le client no " + id
							+ " s'est deconnecte");
					connectiontable.removeElement(this);
					out.close(); // closes needed to terminate connection
					in.close(); // otherwise user's window goes mute
					socket.close();
				} catch (IOException e) {
				}
			}

		}
	}

}