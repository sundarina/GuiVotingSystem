import java.util.List;

public class User {

	private String name;
	private String login;
	private String password;

	public User(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean enter(String login, String password) {
		if (this.login.equals(login) && this.password.equals(password)) {
			return true;
		}

		return false;
	}

	

}

class Elector extends User {

	private boolean voted;
	private Voting voting;

	public Elector(String name, String login, String password) {
		super(name, login, password);
	}

	public boolean isVoted() {
		return voted;
	}

	public void vote(String name) {
		if (voted == true) {
			return; // если пользователь голосовал уже - выход из метода
		}

		List<Candidate> list = voting.getCandidates();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				list.get(i).addVoice();
				voted = true;
				break; // чтоб не делать лишних прохождений цикла
			}
		}
	}

	public void setVoting(Voting vt) { // доступ изберателя к голосованию
		voting = vt;
	}

}
