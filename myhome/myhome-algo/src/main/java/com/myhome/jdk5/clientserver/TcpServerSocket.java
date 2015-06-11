package com.myhome.jdk5.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author glevi
 * 
 */
public class TcpServerSocket extends Thread {

	/**
	 * Subscriber's output flow List
	 */
	private List<PrintWriter> _tabClients = new LinkedList<PrintWriter>();

	private int _nbClients = 0;

	private int nextId = 1;

	boolean listening = true;

	private ServerSocket serverSocket;

	private int serverPort;

	public TcpServerSocket() {
	}

	public static void main(String[] args) {
		new TcpServerSocket(3099).run();
	}

	public TcpServerSocket(int port) {
		this.serverPort = port;
	}

	public void run() {

		try {

			System.out.println("Start Server port: " + serverPort);

			serverSocket = new ServerSocket(serverPort, 2);

			while (listening) {
				new Subscriber(serverSocket.accept(), this);

			}

		} catch (Exception e) {

			System.out.println(e.getLocalizedMessage());

			System.exit(1);
		} finally {
			try {
				if (serverSocket != null) {

					System.out.println("Close server connection port: "
							+ serverPort);

					serverSocket.close();
				}
			} catch (IOException e) {
				System.out.println("Cannot close connection: "
						+ e.getLocalizedMessage());

			}

		}

	}

	/**
	 * Send message to all clients
	 */
	synchronized public void send(String trame) {
		PrintWriter out;

		for (int i = 0; i < _tabClients.size(); i++)

		{
			out = (PrintWriter) _tabClients.get(i);
			if (out != null) {

				out.println(trame);
				out.flush(); // envoi dans le flux de sortie
				System.out.println("Send trame: " + trame);
			}
		}
	}

	/**
	 * Remove a Subscriber's output flow
	 */
	synchronized public void delClient(PrintWriter out) {
		_nbClients--;
		_tabClients.remove(out);

	}

	/**
	 * add a Subscriber's output flow
	 * 
	 * @return client id
	 */
	synchronized public int addClient(PrintWriter out) {
		_nbClients++;
		_tabClients.add(out);
		return nextId++;

	}

	/**
	 * Return connected clients count
	 */
	synchronized public int getNbClients() {
		return _nbClients;
	}

	/**
	 * Classe associee à chaque subscriber
	 */
	class Subscriber implements Runnable {

		private TcpServerSocket server;

		private Thread current;

		private Socket socket;

		/** gestion du flux de sortie */
		private PrintWriter out;

		/** gestion du flux d'entre */
		private BufferedReader in;

		/** numero client */
		private int numClient = 0;

		private String from_name;

		public Subscriber(Socket socket, TcpServerSocket server) {
			this.server = server;
			this.socket = socket;
			this.from_name = socket.getInetAddress().getHostName();

			try {
				out = new PrintWriter(socket.getOutputStream());
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				numClient = server.addClient(out);
			} catch (IOException e) {
			}

			current = new Thread(this);
			current.start();
		}

		public void run() {

			System.out.println("Client " + from_name + ":" + serverPort + " ID"
					+ numClient + " is connected");

			try {
				// client (bloquant sur _in.readLine())
				String line;
				while ((line = in.readLine()) != null) {

				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());

			} finally
			// deconnexion du client
			{
				try {

					System.out.println("Client " + from_name + ":" + serverPort
							+ " ID" + numClient + " is disconnected");

					server.delClient(out); // on supprime le client de
					// la liste
					out.close();
					in.close();
					socket.close();
				} catch (IOException e) {
					System.out.println("Cannot close connection: "
							+ e.getLocalizedMessage());

				}
			}
		}
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public int getServerPort() {
		return serverPort;
	}

}