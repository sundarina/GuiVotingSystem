import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VotingSystem {

	private List<Elector> electors;
	private Voting currentVoting;
	private Elector currentUser;
	List<Candidate> candidatesList;

	public VotingSystem() {
		this.electors = new ArrayList<Elector>();
		this.candidatesList = new ArrayList<Candidate>();
		this.currentVoting = currentVoting;
		this.currentUser = currentUser;
	}

	public void addUser(String name, String login, String password) {
		Elector elector = new Elector(name, login, password);
		electors.add(elector);

	}

	private Elector findUser(String login, String password) {
		for (int i = 0; i < electors.size(); i++) {
			currentUser = electors.get(i);
			if (electors.get(i).getLogin().equals(login) && electors.get(i).getPassword().equals(password)) {
				return currentUser;
			}
		}
		return null; // выход из цикла , если пользователь не найден

	}

	public List<Candidate> getResults() {
		return currentVoting.getCandidates();
	}

//	public static String randomField() {
//		String str = "";
//		for (int i = 0; i < 5; i++) {
//			char ch = (char) (Math.random() * 25 + 97);
//			str += Character.toString(ch);
//		}
//		return str;
//	}

	public static void main(String[] args) {
			

		Scanner scanner = new Scanner(System.in);
		Voting voting = new Voting("Гoлосование за меры города");

		voting.addCandidates(new Candidate("Павлик Виктор "));
		voting.addCandidates(new Candidate("Зибров Павел"));
		voting.addCandidates(new Candidate("Писанка Руслана"));
		voting.addCandidates(new Candidate("Кличко Владимир"));
		voting.addCandidates(new Candidate("Карпа Ирэна"));
		Collections.sort(voting.getCandidates());

		Registration reg = new Registration(voting.getCandidates());
		
		Elector currentUser = new Elector(reg.getUserRegistrationName(), reg.getUserRegistrationLogin(), reg.getUserRegistrationPassword());

		VotingSystem votingSystem = new VotingSystem();

		
		 votingSystem.addUser(currentUser.getName(), currentUser.getLogin(), currentUser.getPassword()); 

		if (currentUser.enter(reg.getUserLogin(), reg.getUserPassord()) == true) {
			currentUser = votingSystem.findUser(reg.getUserLogin(), reg.getUserPassord());	
			}
		currentUser.setVoting(voting);
		currentUser.vote(reg.getUserVote());

	}
}

