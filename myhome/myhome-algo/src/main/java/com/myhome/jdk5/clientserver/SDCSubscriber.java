package com.myhome.jdk5.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//** Classe associŽe ˆ chaque client **
//** Il y aura autant d'instances de cette classe que de clients connectŽs **
//implŽmentation de l'interface Runnable (une des 2 mŽthodes pour crŽer un thread)
class SDCSubscriber implements Runnable {
	private Thread _t; // contiendra le thread du client
	private Socket _s; // recevra le socket liant au client
	private PrintWriter _out; // pour gestion du flux de sortie
	private BufferedReader _in; // pour gestion du flux d'entrŽe
	private SDCServer _blablaServ; // pour utilisation des mŽthodes de la
									// classe principale
	private int _numClient = 0; // contiendra le numŽro de client gŽrŽ par ce
								// thread

	// ** Constructeur : crŽe les ŽlŽments nŽcessaires au dialogue avec le
	// client **
	SDCSubscriber(Socket s, SDCServer client) // le param s est donnŽe
													// dans BlablaServ par
													// ss.accept()
	{
		_blablaServ = client; // passage de local en global (pour gestion
									// dans les autres mŽthodes)
		_s = s; // passage de local en global
		try {
			// fabrication d'une variable permettant l'utilisation du flux de
			// sortie avec des string
			_out = new PrintWriter(_s.getOutputStream());
			// fabrication d'une variable permettant l'utilisation du flux
			// d'entrŽe avec des string
			_in = new BufferedReader(new InputStreamReader(_s.getInputStream()));
			// ajoute le flux de sortie dans la liste et rŽcupŽration de son
			// numero
			_numClient = client.addClient(_out);
		} catch (IOException e) {
		}

		_t = new Thread(this); // instanciation du thread
		_t.start(); // demarrage du thread, la fonction run() est ici lancŽe
	}

	// ** Methode : exŽcutŽe au lancement du thread par t.start() **
	// ** Elle attend les messages en provenance du serveur et les redirige **
	// cette mŽthode doit obligatoirement �tre implŽmentŽe ˆ cause de
	// l'interface Runnable
	public void run() {
		String message = ""; // dŽclaration de la variable qui recevra les
								// messages du client
		// on indique dans la console la connection d'un nouveau client
		System.out
				.println("Un nouveau client s'est connecte, no " + _numClient);
		try {
			// la lecture des donnŽes entrantes se fait caract�re par caract�re
			// ...
			// ... jusqu'ˆ trouver un caract�re de fin de chaine
			char charCur[] = new char[1]; // dŽclaration d'un tableau de char
											// d'1 Žlement, _in.read() y
											// stockera le char lu
			while (_in.read(charCur, 0, 1) != -1) // attente en boucle des
													// messages provenant du
													// client (bloquant sur
													// _in.read())
			{
				System.out.println("waa");
				// on regarde si on arrive ˆ la fin d'une chaine ...
				if (charCur[0] != '\u0000' && charCur[0] != '\n'
						&& charCur[0] != '\r')
					message += charCur[0]; // ... si non, on concat�ne le
											// caract�re dans le message
				else if (!message.equalsIgnoreCase("")) // juste une
														// vŽrification de
														// principe
				{
					if (charCur[0] == '\u0000') // le dernier caract�re Žtait
												// '\u0000' (char de terminaison
												// nulle)
						// on envoi le message en disant qu'il faudra concatŽner
						// '\u0000' lors de l'envoi au client
						_blablaServ.sendAll(message);
					else
						_blablaServ.sendAll(message); // sinon on envoi le
															// message ˆ tous
					message = ""; // on vide la chaine de message pour qu'elle
									// soit rŽutilisŽe
				}
			}
		} catch (Exception e) {
		} finally // finally se produira le plus souvent lors de la
					// deconnexion du client
		{
			try {
				// on indique ˆ la console la deconnexion du client
				System.out.println("Le client no " + _numClient
						+ " s'est deconnecte");
				_blablaServ.delClient(_numClient); // on supprime le client de
													// la liste
				_s.close(); // fermeture du socket si il ne l'a pas dŽjˆ ŽtŽ (ˆ
							// cause de l'exception levŽe plus haut)
			} catch (IOException e) {
			}
		}
	}
}