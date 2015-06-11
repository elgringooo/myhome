package com.myhome.jdk5.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Le processus client se connecte au site fourni dans la commande d'appel en
 * premier argument et utilise le port distant 8080.
 */
public class Client implements Runnable {
	static final int hostPort = 3099;

	static final String hostIP = "localhost";

	private Socket socket = null;

	/** Delay before retry tcp connection */
	private int retryDelay = 1;

	public void openStream() {

		boolean ok = false;
		while (!ok) {
			try {

				System.out
						.println("Try to connect to IP adress: " + hostIP + " and port: "
								+ hostPort + "...");

				socket = new Socket(hostIP, hostPort);

				System.out.println("Connection established !");

				ok = true;

			} catch (IOException e) {

				System.out.println("Connection failed, wait " + retryDelay
						+ "s before retry.");

				try {
					Thread.sleep(retryDelay * 1000);
				} catch (InterruptedException e1) {

					System.out.println("Connection interrupted: "
							+ e1.getLocalizedMessage());

				}
			}
		}
	}

	public void closeStream() {
		try {
			System.out.println("Close connection with IP adress: " + hostIP
					+ " and port: " + hostPort);

			socket.close();
		} catch (IOException e) {
			System.out.println("Cannot close connection: "
					+ e.getLocalizedMessage());

		}
	}

	public void start() {
		System.out.println("Start Client");

		Thread th = new Thread(this);
		th.setName("TCP Connector");
		th.start();
	}

	public void run() {

		openStream();

		StringBuffer buffer = null;
		BufferedReader in = null;
		while (true) {
			try {
				in = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				while (true) {
					buffer = new StringBuffer(in.readLine());
					if (buffer == null) {
						break;
					} else {
						System.out.println("Trame read: " + buffer.toString());
					}

				}
				Thread.sleep(100);

			} catch (InterruptedException e) {
				System.out.println("Execution interrupted: ");

				stop();
			} catch (Exception e) {
				System.out.println("Connection lost");

				openStream();
			}
		}
	}

	public void stop() {
		System.out.println("Stop TCP connection");

		closeStream();
	}

	public static void main(String[] args) throws Exception {
		new Client().start();
	}
}
