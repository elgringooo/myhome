package com.myhome.jdk5.swing.appender;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JScrollPane jScrollPane = null;

	private JTextPane jTextPane = null;

	private JButton jButton = null;

	private JPanel jPanel = null;

	private JButton jButton1 = null;

	private JButton jButton2 = null;

	private JButton jButton3 = null;

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setMaximumSize(new Dimension(800, 300));
			jScrollPane.setMinimumSize(new Dimension(800, 300));
			jScrollPane.setPreferredSize(new Dimension(800, 300));
			jScrollPane.setBorder(BorderFactory.createTitledBorder(null, "JtextPane Log", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jScrollPane.setViewportView(getJTextPane());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setEditable(false);
			jTextPane.addCaretListener(new javax.swing.event.CaretListener() {
				public void caretUpdate(javax.swing.event.CaretEvent e) {
					String s = getJTextPane().getText();
					System.out.println(s.length()); // TODO Auto-generated Event stub caretUpdate()
				}
			});
		}
		return jTextPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("AddError");			
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					MainLogger.logError("An Error");
						
					}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(getJButton(), null);
			jPanel.add(getJButton1(), null);
			jPanel.add(getJButton2(), null);
			jPanel.add(getJButton3(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Add Exception");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						throw new Exception("This is a custom exception");
					} catch (Exception e1) {
						MainLogger.logError(e1);
					}
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Add Warning");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					MainLogger.logWarn("This is a warning!!");
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Add Trace");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					MainLogger.logTrace("This is a Trace message!");
				}
			});
		}
		return jButton3;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub						
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TestFrame thisClass = new TestFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
				MainLogger.init(
						new JTextPaneAppender(thisClass.getJTextPane()),
						org.apache.log4j.Level.TRACE);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public TestFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setMaximumSize(new Dimension(800, 600));
		this.setMinimumSize(new Dimension(800, 600));
		this.setPreferredSize(new Dimension(800, 600));
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJScrollPane(), BorderLayout.SOUTH);
			jContentPane.add(getJPanel(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
