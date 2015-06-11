package com.myhome.jdk5.clientserver;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Vector;

//** Classe principale du serveur, g�re les infos globales **
public class SDCServer {
	 Vector _tabClients = new Vector();

	private int _nbClients = 0;

	boolean listening = true;

	public SDCServer() {
		try {

			ServerSocket ss = new ServerSocket(8080);

			while (listening) {
				new SDCSubscriber(ss.accept(), this);
			}
			ss.close();
		} catch (Exception e) {
		}
	}

	public static void main(String args[]) {
		new SDCServer();

	}

	// ** Methode : envoie le message ˆ tous les clients **
	synchronized public void sendAll(String message) {
		PrintWriter out;

		for (int i = 0; i < _tabClients.size(); i++)

		{
			out = (PrintWriter) _tabClients.elementAt(i);
			if (out != null) {

				out.print(message);
				out.flush(); // envoi dans le flux de sortie
			}
		}
	}

	// ** Methode : dŽtruit le client no i **
	synchronized public void delClient(int i) {
		_nbClients--;
		if (_tabClients.elementAt(i) != null) {
			_tabClients.removeElementAt(i);
		}
	}

	// ** Methode : ajoute un nouveau client dans la liste **
	synchronized public int addClient(PrintWriter out) {
		_nbClients++;
		_tabClients.addElement(out);

		return _tabClients.size() - 1;
	}

	// ** Methode : retourne le nombre de clients connectŽs **
	synchronized public int getNbClients() {
		return _nbClients;
	}

}