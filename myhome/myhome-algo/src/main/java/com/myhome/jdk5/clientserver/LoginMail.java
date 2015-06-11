package com.myhome.jdk5.clientserver;

/*

 * 20:27:40 30/04/00

 *

 * LoginMail.java - Mail document

 * Copyright (C) 2000 Romain Guy

 * guy.romain@bigfoot.com

 * www.chez.com/powerteam/jext

 *

 * This program is free software; you can redistribute it and/or

 * modify it under the terms of the GNU General Public License

 * as published by the Free Software Foundation; either version 2

 * of the License, or any later version.

 *

 * This program is distributed in the hope that it will be useful,

 * but WITHOUT ANY WARRANTY; without even the implied warranty of

 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the

 * GNU General Public License for more details.

 *

 * You should have received a copy of the GNU General Public License

 * along with this program; if not, write to the Free Software

 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class LoginMail extends JFrame implements ActionListener, Runnable

{

	// nous utilisons un Thread pour éviter de bloquer la machine

	private Thread mailer;

	// la 'trace'

	private JTextArea tracer;

	private boolean traceState;

	// corps du mail

	private JTextArea textArea;

	// les boutons de commandes

	private JButton send, cancel, details;

	// les champs de renseignments

	private JTextField host, from, to, subject;

	// utilisé pour construire l'interface

	private int y = 0;

	private JPanel pane;

	private JPanel _pane;

	private JScrollPane scroller;

	private GridBagLayout gridBag;

	/**
	 * 
	 * Cette méthode ajoute un composant à l'interface en lui donnant
	 * 
	 * un label. Les composants sont ajoutés au panneau des champs de
	 * 
	 * renseignement uniquement.
	 * 
	 */

	protected void addComponent(String label, Component comp)

	{

		GridBagConstraints cons = new GridBagConstraints();

		cons.gridy = y++;

		cons.gridheight = 1;

		cons.gridwidth = 3;

		cons.fill = GridBagConstraints.BOTH;

		cons.weightx = 1.0f;

		cons.insets = new Insets(2, 2, 2, 2);

		cons.gridx = 0;

		JLabel l = new JLabel(label, SwingConstants.RIGHT);

		gridBag.setConstraints(l, cons);

		pane.add(l);

		cons.gridx = 3;

		cons.gridwidth = 1;

		gridBag.setConstraints(comp, cons);

		pane.add(comp);

	}

	/**
	 * 
	 * Création de la fenêtre.
	 * 
	 */

	public LoginMail()

	{

		super("Login Mail");

		// définition du layout principal

		getContentPane().setLayout(new BorderLayout());

		// panneau contenant les champs de renseignements

		pane = new JPanel();

		pane.setLayout(gridBag = new GridBagLayout());

		// champ pour le serveur

		addComponent("Serveur:", (host = new JTextField(15)));

		host.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

		// champ pour l'expéditeur

		addComponent("Expéditeur:", (from = new JTextField(15)));

		from.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

		// champ pour les destinataire

		addComponent("Destinataire:", (to = new JTextField(15)));

		to.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

		// sujet du mail

		addComponent("Sujet:", (subject = new JTextField(15)));

		subject.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

		// panneau des boutons

		JPanel btnPane = new JPanel();

		// bouton Envoi

		btnPane.add((send = new JButton("Envoyer")));

		send.addActionListener(this);

		getRootPane().setDefaultButton(send);

		// bouton annuler

		btnPane.add((cancel = new JButton("Annuler")));

		cancel.addActionListener(this);

		// bouton des détails

		btnPane.add((details = new JButton("Détails >>")));

		details.addActionListener(this);

		// zone de debugging

		tracer = new JTextArea(5, 15);

		tracer.setEditable(false);

		scroller = new JScrollPane(tracer,

		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,

		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		_pane = new JPanel(new BorderLayout());

		_pane.add(pane, BorderLayout.NORTH);

		_pane.add(btnPane, BorderLayout.CENTER);

		getContentPane().add(_pane, BorderLayout.WEST);

		getContentPane().add(new JScrollPane(textArea = new JTextArea(5, 15),

		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,

		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),

		BorderLayout.EAST);

		// à la fermeture de la fenêtre, nous quittons

		addWindowListener(new WindowAdapter()

		{

			public void windowClosing(WindowEvent evt)

			{

				dispose();

				System.exit(0);

			}

		});

		// nous fixons la taille et la position

		pack();

		setResizable(false);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension mine = getSize();

		setLocation(new Point((screen.width - mine.width) / 2,

		(screen.height - mine.height) / 2));

		setVisible(true);

	}

	/**
	 * 
	 * Désactive les champs/boutons nécessaires.
	 * 
	 */

	private void wait(boolean on)

	{

		textArea.setEnabled(!on);

		send.setEnabled(!on);

		host.setEnabled(!on);

		to.setEnabled(!on);

		from.setEnabled(!on);

		subject.setEnabled(!on);

	}

	/**
	 * 
	 * Cette méthode va lancer, après vérification de la validité
	 * 
	 * des renseignements, le thread gérant l'envoi du mail
	 * 
	 */

	private void send()

	{

		if (!check())

			return;

		mailer = new Thread(this);

		mailer.setPriority(Thread.MIN_PRIORITY);

		mailer.setName("LoginMail");

		mailer.start();

	}

	/**
	 * 
	 * Stoppe l'envoi.
	 * 
	 */

	public void stop()

	{

		mailer = null;

	}

	/**
	 * 
	 * C'est dans cette méthode que nous appelons sendMail().
	 * 
	 */

	public void run()

	{

		if (mailer != null)

		{

			wait(true);

			if (sendMail(host.getText(), from.getText(), to.getText(), subject
					.getText()))

				showMessage("Mail envoyé !");

			else

				showMessage("Ne peut pas envoyer le mail !");

			wait(false);

		}

		stop();

	}

	/**
	 * 
	 * Vérifie la validité des renseignements (présence de @ dans les emails...)
	 * 
	 */

	private boolean check()

	{

		if (host.getText().equals("") || host.getText() == null)

		{

			showMessage("Serveur non défini");

			return false;

		}

		if (from.getText().equals("") || from.getText().indexOf('@') == -1 ||

		to.getText().equals("") || to.getText().indexOf('@') == -1)

		{

			showMessage("Mauvaise adresse email");

			return false;

		}

		return true;

	}

	/**
	 * 
	 * Gestion des actions.
	 * 
	 */

	public void actionPerformed(ActionEvent evt)

	{

		Object obj = evt.getSource();

		if (obj == send)

			send();

		else if (obj == details)

			showDetails();

		else if (obj == cancel)

			dispose();

	}

	/**
	 * 
	 * Affiche/cache les détails
	 * 
	 */

	private void showDetails()

	{

		if (traceState)

		{

			_pane.remove(scroller);

			details.setText("Détails >>");

		} else {

			_pane.add(scroller, BorderLayout.SOUTH);

			details.setText("<< Détails");

		}

		pack();

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension mine = getSize();

		setLocation(new Point((screen.width - mine.width) / 2,

		(screen.height - mine.height) / 2));

		traceState = !traceState;

	}

	/**
	 * 
	 * Sert à afficher des informations du dialogue avec le serveur.
	 * 
	 */

	private void trace(String s)

	{

		tracer.append(s + "\n");

		tracer.setCaretPosition(tracer.getDocument().getLength());

	}

	/**
	 * 
	 * Affiche un message.
	 * 
	 */

	public static void showMessage(String message)

	{

		JOptionPane.showMessageDialog(null, message, "Message",

		JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * 
	 * Affiche une erreur.
	 * 
	 */

	public static void showError(String message)

	{

		JOptionPane.showMessageDialog(null, message, "Erreur",

		JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * 
	 * Gère les erreurs pendant l'envoi du mail.
	 * 
	 */

	private boolean error(String s)

	{

		showMessage(s);

		return false;

	}

	/**
	 * 
	 * La partie la plus importante !! C'est ici que nous envoyons les emails
	 * 
	 */

	public boolean sendMail(String host, String from, String to, String subject)

	{

		// le socket

		Socket smtpPipe;

		// les flux de lecture/écriture

		BufferedReader in;

		OutputStreamWriter out;

		// notre propre adresse Internet

		InetAddress ourselves;

		try

		{

			ourselves = InetAddress.getLocalHost();

		} catch (UnknownHostException uhe) {
			return false;
		}

		tracer.setText("");

		// si le nom de seveur a la forme: X.X.X.X:XX

		// alors nous devons récupérer le XX après les :

		// comme étant le numéro de port

		// par défaut, le port est le 25

		int index = host.indexOf(':');

		int port = 25;

		if (index != -1)

		{

			port = Integer.parseInt(host.substring(index + 1));

			host = host.substring(0, index);

		}

		try

		{

			// ////////////////////////////////

			// CONNEXION !!!!!!!!!!!!!!!!!!!!!

			// ////////////////////////////////

			smtpPipe = new Socket(host, port);

			if (smtpPipe == null)

				return false;

			// ////////////////////////////////

			// CREATION DES FLUX

			// ////////////////////////////////

			in = new BufferedReader(new InputStreamReader(smtpPipe
					.getInputStream()));

			out = new OutputStreamWriter(smtpPipe.getOutputStream());

			if (in == null || out == null)

				return false;

			// ////////////////////////////////

			// LECTURE DE LA PREMIER LIGNE

			// ////////////////////////////////

			String response, command;

			trace(response = in.readLine());

			if (!response.startsWith("220")) // !! si la ligne ne commence
												// pas par

				return error("Serveur invalide"); // !! 220, il y a un
													// problème

			// ////////////////////////////////

			// PREMIER ECHANGE

			// ////////////////////////////////

			// nous disons bonour au serveur

			// en donnant notre nom

			// ////////////////////////////////

			command = "HELO " + ourselves.getHostName(); // ## création du
															// message

			out.write(command + "\r\n"); // ## envoi du message

			out.flush();

			trace(command);

			trace(response = in.readLine());

			if (!response.startsWith("250")) // !! si la ligne ne commence
												// pas par

				return error("Serveur invalide"); // !! 250, il y a problème

			// ////////////////////////////////

			// SECOND ECHANGE

			// ////////////////////////////////

			// nous nous identifions en donnant

			// l'email de l'expéditeur

			// ////////////////////////////////

			command = "MAIL FROM:<" + from + ">";

			out.write(command + "\r\n");

			out.flush();

			trace(command);

			trace(response = in.readLine());

			if (!response.startsWith("250")) // !! si la ligne ne commence
												// pas par

				return error("Expéditeur refusé !"); // !! 250, il y a
														// problème

			// ////////////////////////////////

			// TROISIEME ECHANGE

			// ////////////////////////////////

			// nous spécifions le destinataire

			// qui peut être refusé

			// ////////////////////////////////

			command = "RCPT TO:<" + to + ">";

			out.write(command + "\r\n");

			out.flush();

			trace(command);

			trace(response = in.readLine());

			if (!response.startsWith("250")) // !! même principe

				return error("Destinataire inconnu !");

			// ////////////////////////////////

			// QUATRIEME ECHANGE

			// ////////////////////////////////

			// nous annonçons le début du mail

			// ////////////////////////////////

			out.write("DATA\r\n");

			out.flush();

			trace("DATA");

			trace(response = in.readLine());

			if (!response.startsWith("354")) // !! là encore, erreur...

				return error("Erreur inconnue !");

			trace("[Sending mail...]");

			// ////////////////////////////////

			// EN TETE

			// ////////////////////////////////

			// l'en-tête est utilisée par les

			// clients mails pour afficher les

			// noms dans les listes de réception

			// ////////////////////////////////

			out.write("To: <" + to + ">");

			out.write("\r\n");

			out.write("From: <" + from + ">");

			out.write("\r\n");

			out.write("Subject: " + subject);

			out.write("\r\n");

			out.write("X-Mailer: Login: Mail");

			out.write("\r\n");

			out.write("\r\n");

			// ////////////////////////////////

			// CORPS DU MAIL

			// ////////////////////////////////

			// un peu de code Swing...

			// ////////////////////////////////

			try

			{

				// sert à stocker les lignes de texte

				String text;

				// le document text

				Document doc = textArea.getDocument();

				// utilisé pour récupérer les lignes

				Element map = doc.getDefaultRootElement();

				int total = map.getElementCount();

				// pour chaque ligne

				for (int i = 0; i < total; i++)

				{

					// nous récupérons les 'coordonnées'

					// de chaque ligne

					Element lineElement = map.getElement(i);

					int start = lineElement.getStartOffset();

					int end = lineElement.getEndOffset() - 1;

					// donne la longueur de la ligne

					end -= start;

					// récupération de la ligne

					text = doc.getText(start, end);

					// !!!!!!!! case du . sur une ligne seul !!!!!!!!

					if (text.equals("."))

						text = "!";

					// envoi du texte

					out.write(text);

					out.write("\r\n");

					// affichage de la progression

					trace("[" + (i + 1) * 100 / total + "%]");

				}

			} catch (BadLocationException ble) {
			}

			// nous avons fini, nous envoyons un .

			out.write(".\r\n");

			out.flush();

			trace(".");

			trace(response = in.readLine());

			if (!response.startsWith("250"))

				return error("Erreur inconnue !");

			// ////////////////////////////////

			// FIN

			// ////////////////////////////////

			// nous quittons le serveur

			// ////////////////////////////////

			out.write("QUIT");

			trace("QUIT");

			// nous fermons les connexions...

			smtpPipe.close();

			smtpPipe = null;

		} catch (IOException ioe) {
			return false;
		}

		return true;

		// //////////////////////// OUF !

	}

	public static void main(String args[])

	{

		new LoginMail();

	}

}

// End of LoginMail.java

