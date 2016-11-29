import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.*;

import javax.swing.*;

public class Registration extends WindowAdapter implements ActionListener {

	protected JFrame regFrame;
	protected JPanel txtPanel, txtPanel2, listPanel;
	protected JPanel btnPanel, btnPanel2, btnPanel3;
	protected JTextField nameField, loginField, passwordField, loginTxt, passwordTxt, voting;
	protected JButton regButton, name, login, password, enterButton, votingButton;
	protected JTextArea listC;
	
	protected Dialog dialog;
	protected Label label, candList;
	protected JButton dialogButton;
	
	protected Voting vote;
	
	ArrayList<Candidate> listArea; 

	public Registration(Voting vote) {
		
		this.vote = vote;
		listArea = new ArrayList<Candidate>();
		listArea = vote.getCandidates();
		
		regFrame = new JFrame(Voting.getTitle()); // название голосования
		txtPanel = new JPanel();
		btnPanel = new JPanel();
		name = new JButton("Имя пользователя: ");
		login = new JButton("Логин: ");
		password = new JButton("Пароль: ");
		nameField = new JTextField(20);
		loginField = new JTextField(20);
		passwordField = new JTextField(20);

		regButton = new JButton("Зарегистрироваться");

		regFrame.setSize(600, 400);
		regFrame.add(txtPanel, BorderLayout.NORTH);
		regFrame.add(btnPanel, BorderLayout.SOUTH);

		txtPanel.setLayout(new GridLayout(3, 2));
		txtPanel.add(name);
		txtPanel.add(nameField);
		txtPanel.add(login);
		txtPanel.add(loginField);
		txtPanel.add(password);
		txtPanel.add(passwordField);
		nameField.addActionListener(this);
		loginField.addActionListener(this);
		passwordField.addActionListener(this);

		btnPanel.add(regButton, BorderLayout.CENTER);
		regButton.addActionListener(this);

		regFrame.addWindowListener(this);
		regFrame.setVisible(true);

		////////
		
		dialog = new Dialog(regFrame, "Massage", true);
		label = new Label();
		dialogButton = new JButton("Ok");
		dialog.setLayout(new FlowLayout());
		dialog.add(label);
		dialog.add(dialogButton);
		dialogButton.addActionListener(this);

		///////
		
		txtPanel2 = new JPanel();
		loginTxt = new JTextField(20);
		passwordTxt = new JTextField(20);

		enterButton = new JButton("Войти в систему");

		txtPanel2.setLayout(new GridLayout(2, 2));
		txtPanel2.add(login);
		txtPanel2.add(loginTxt);
		txtPanel2.add(password);
		txtPanel2.add(passwordTxt);

		loginTxt.addActionListener(this);
		passwordTxt.addActionListener(this);

		btnPanel2 = new JPanel();
		btnPanel2.add(enterButton, BorderLayout.CENTER);
		enterButton.addActionListener(this);
		
		////////
		
		listPanel = new JPanel();
		voting = new JTextField(20);
		votingButton = new JButton("Голосовать");
		candList =  new Label("Введите имя выбраного кандидата в поле: ");
		listC = new JTextArea();
		
		
		
		listPanel.add(candList, BorderLayout.SOUTH);
		listPanel.add(listC , BorderLayout.NORTH);
		
		btnPanel3 = new JPanel();
		btnPanel3.add(voting, BorderLayout.WEST);
		btnPanel3.add(votingButton, BorderLayout.EAST);
		voting.addActionListener(this);
		votingButton.addActionListener(this);
		
		
		

	}

	public void windowClosing(WindowEvent e) { // close the frame/window
		regFrame.dispose();
	}

	private String userRegistrationName = "";
	private String userRegistrationLogin = "";
	private String userRegistrationPassword = "";
	private String userLogin = "";
	private String userPassord = "";

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == regButton) {
			userRegistrationName = nameField.getText();
			userRegistrationLogin = loginField.getText();
			userRegistrationPassword = passwordField.getText();

			if (userRegistrationName.equals("") || userRegistrationLogin.equals("")
					|| userRegistrationPassword.equals("")) {
				label.setText("Пожалуйста, заполните все поля");
				dialog.setSize(250, 100);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}

			regFrame.remove(txtPanel);
			regFrame.remove(btnPanel);

			regFrame.add(txtPanel2, BorderLayout.NORTH);
			regFrame.add(btnPanel2, BorderLayout.SOUTH);

		} else if (e.getSource() == dialogButton) {
			dialog.setVisible(false);
		} else if (e.getSource() == enterButton) {
			userLogin = loginTxt.getText();
			userPassord = passwordTxt.getText();

			if (userLogin.equals("") || userPassord.equals("")) {
				label.setText("Пожалуйста, заполните все поля");
				dialog.setSize(250, 100);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}

			if (userLogin.equals(userRegistrationLogin) || userPassord.equals(userRegistrationPassword)) {
				regFrame.remove(txtPanel2);
				regFrame.remove(btnPanel2);
				
				regFrame.add(listPanel, BorderLayout.NORTH);
				regFrame.add(btnPanel3, BorderLayout.SOUTH);

			} else {
				label.setText("Пожалуйста, заполните все поля");
				dialog.setSize(250, 100);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}
			
			 Iterator iterator = listArea.iterator();
             while (iterator.hasNext()){
             listC.append(iterator.next().toString()+ '\n');  
           }
		}
	}





	public String getUserRegistrationName() {
		return userRegistrationName;
	}

	public String getUserRegistrationLogin() {
		return userRegistrationLogin;
	}

	public String getUserRegistrationPassword() {
		return userRegistrationPassword;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public String getUserPassord() {
		return userPassord;
	}

	public static void main(String args[]) {
		new Registration(votig);
	}
}
