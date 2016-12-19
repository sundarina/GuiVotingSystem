import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.*;

import javax.swing.*;

public class Registration extends WindowAdapter implements ActionListener {

	protected JFrame regFrame;
	protected JPanel txtPanel, txtPanel2, listPanel, resultPanel;
	protected JPanel btnPanel, btnPanel2, btnPanel3;
	protected JTextField nameField, loginField, passwordField, loginTxt, passwordTxt, voting;
	protected JButton regButton, name, login, login2, password2, password, enterButton, votingButton;
	protected JTextArea listC, listR;

	JScrollPane jsP1, jsP2;

	protected Dialog dialog;
	protected Label label, candList;
	protected JButton dialogButton;

	java.util.List<Candidate> listArea;

	public Registration(java.util.List<Candidate> list) {

		this.listArea = list;

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

		regFrame.setSize(400, 300);
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

	}

	private String userRegistrationName = "";
	private String userRegistrationLogin = "";
	private String userRegistrationPassword = "";
	private String userLogin = "";
	private String userPassord = "";
	private String userVote = "";

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == dialogButton) {
			dialog.setVisible(false);
		} else if (e.getSource() == regButton) { // регистрация
			userRegistrationName = nameField.getText();
			userRegistrationLogin = loginField.getText();
			userRegistrationPassword = passwordField.getText();

			if (userRegistrationName.equals("") || userRegistrationLogin.equals("") // проверка
																					// на
																					// пустое
																					// поле
					|| userRegistrationPassword.equals("")) {

				dialog = new Dialog(regFrame, "Massage", true);
				label = new Label();
				dialogButton = new JButton("Ok");
				dialog.setLayout(new FlowLayout());
				dialog.add(label);
				dialog.add(dialogButton);
				dialogButton.addActionListener(this);
				label.setText("Пожалуйста, заполните все поля");
				dialog.setSize(250, 100);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}

			regFrame.remove(txtPanel); // удаление 1 панели
			regFrame.remove(btnPanel);

			txtPanel2 = new JPanel();
			loginTxt = new JTextField(20);
			passwordTxt = new JTextField(20);

			login2 = new JButton("Логин: ");
			password2 = new JButton("Пароль: ");

			enterButton = new JButton("Войти в систему");

			txtPanel2.setLayout(new GridLayout(2, 2));
			txtPanel2.add(login2);
			txtPanel2.add(loginTxt);
			txtPanel2.add(password2);
			txtPanel2.add(passwordTxt);

			loginTxt.addActionListener(this);
			passwordTxt.addActionListener(this);

			btnPanel2 = new JPanel();
			btnPanel2.add(enterButton, BorderLayout.CENTER);
			enterButton.addActionListener(this);

			regFrame.add(txtPanel2, BorderLayout.NORTH); // добавление 2 панели
			regFrame.add(btnPanel2, BorderLayout.SOUTH);
			regFrame.setVisible(true);
			regFrame.repaint();

		} else if (e.getSource() == enterButton) { // вход
			userLogin = loginTxt.getText();
			userPassord = passwordTxt.getText();

			if (userLogin.equals("") || userPassord.equals("")) { // проверка на
																	// пустое
																	// поле

				dialog = new Dialog(regFrame, "Massage", true);
				label = new Label();
				dialogButton = new JButton("Ok");
				dialog.setLayout(new FlowLayout());
				dialog.add(label);
				dialog.add(dialogButton);
				dialogButton.addActionListener(this);
				label.setText("Пожалуйста, заполните все поля");
				dialog.setSize(250, 100);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}

			if (userLogin.equals(userRegistrationLogin) || userPassord.equals(userRegistrationPassword)) { // проверка
																											// входа
				regFrame.remove(txtPanel2); // удаление 2 панели
				regFrame.remove(btnPanel2);

				listPanel = new JPanel();
				listPanel.setLayout(new BorderLayout());
				voting = new JTextField(20);
				votingButton = new JButton("Голосовать");
				candList = new Label("Введите имя выбраного кандидата в поле: ");
				listC = new JTextArea();

				Iterator<Candidate> iterator = listArea.iterator(); // заполнение
				// списка
				// кандидатов
				while (iterator.hasNext()) {
					listC.append(iterator.next().getName() + '\n');
				}
				jsP1 = new JScrollPane(listC, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

				listPanel.add(candList, BorderLayout.NORTH);
				listPanel.add(jsP1, BorderLayout.CENTER);

				btnPanel3 = new JPanel();
				btnPanel3.add(voting, BorderLayout.WEST);
				btnPanel3.add(votingButton, BorderLayout.EAST);
				voting.addActionListener(this);
				votingButton.addActionListener(this);

				regFrame.add(listPanel, BorderLayout.NORTH); // переход на 3
																// панель
				regFrame.add(btnPanel3, BorderLayout.SOUTH);

				regFrame.setVisible(true);
				regFrame.repaint();
			} else {
				dialog = new Dialog(regFrame, "Massage", true);
				label = new Label();
				dialogButton = new JButton("Ok");
				dialog.setLayout(new FlowLayout());
				dialog.add(label);
				dialog.add(dialogButton);
				dialogButton.addActionListener(this);
				label.setText("Неверный пароль или логин, введите заново");
				dialog.setSize(250, 100);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}

		} else if (e.getSource() == votingButton) {
			userVote = voting.getText(); // ввод имени кандидата

			if (userVote.equals("")) { // проверка на пустое поле
				label.setText("Пожалуйста, заполните полe");
				dialog.setSize(250, 100);
				dialog.setResizable(false);
				dialog.setVisible(true);
			}

			regFrame.remove(listPanel);
			regFrame.remove(btnPanel3);

			resultPanel = new JPanel();
			resultPanel.setLayout(new BorderLayout());

			

			listR = new JTextArea("Результат голосования: " + '\n');

			jsP2 = new JScrollPane(listR, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

			resultPanel.add(jsP2, BorderLayout.CENTER);

			regFrame.add(resultPanel);
			
			Iterator<Candidate> iterator = listArea.iterator(); // заполнение
			// списка
			// кандидатов
			while (iterator.hasNext()) {
				Candidate temp = iterator.next();
				listR.append(temp.getName() + "  : " + temp.getVoices() + '\n');
			}

			regFrame.setVisible(true);
			regFrame.repaint();

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

	public String getUserVote() {
		return userVote;
	}

	public void windowClosing(WindowEvent e) { // close the frame/window
		regFrame.dispose();
	}
}
