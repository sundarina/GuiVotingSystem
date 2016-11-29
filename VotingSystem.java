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

	public static String randomField() {
		String str = "";
		for (int i = 0; i < 5; i++) {
			char ch = (char) (Math.random() * 25 + 97);
			str += Character.toString(ch);
		}
		return str;
	}

	public static void main(String[] args) {
		
		
	
		
//		List<Elector> electors = new ArrayList<Elector>();
//		for (int i = 0; i < 11; i++) {
//			electors.add(new Elector(randomField(), randomField(), randomField()));
//		}

		Scanner scanner = new Scanner(System.in);
		Voting votig = new Voting("Гoлосование за меры города");

		votig.addCandidates(new Candidate("Павлик Виктор "));
		votig.addCandidates(new Candidate("Зибров Павел"));
		votig.addCandidates(new Candidate("Писанка Руслана"));
		votig.addCandidates(new Candidate("Кличко Владимир"));
		votig.addCandidates(new Candidate("Карпа Ирэна"));
		Collections.sort(votig.getCandidates());

		
//		  System.out.println("Введите имя: ");
//		  String userRegistrationName = scanner.nextLine(); 
//		  System.out.println("Введите логин: "); 
//		  String userRegistrationLogin = scanner.nextLine();
//		  System.out.println("Введите пароль: ");
//		  String userRegistrationPassword = scanner.nextLine();
		 

		// Elector currentUser = new Elector(userRegistrationName, userRegistrationLogin, userRegistrationPassword);
		
		Registration reg = new Registration(votig);
		
		Elector currentUser = new Elector(reg.getUserRegistrationName(), reg.getUserRegistrationLogin(), reg.getUserRegistrationPassword());

		VotingSystem votingSystem = new VotingSystem();

		
		 votingSystem.addUser(currentUser.getName(), currentUser.getLogin(), currentUser.getPassword()); 
		  //добавляет пользователя в список голосующих в системе
		 

//		for (int i = 0; i < electors.size(); i++) {
//
//			votingSystem.addUser(electors.get(i).getName(), electors.get(i).getLogin(), electors.get(i).getPassword());
//			Elector e = votingSystem.findUser(electors.get(i).getLogin(), electors.get(i).getPassword());
//			e.setVoting(votig);
//			e.vote(votig.getCandidates().get((int) (Math.random() * 5)).getName());
//		}

		
		 //boolean b = false; 
		 
//		 do{
//			 System.out.println("Введите логин, чтобы войти в систему: "); 
//			 String userLogin = scanner.nextLine();
//			 System.out.println("Введите пароль, чтобы войти в систему: "); 
//			 String userPassword = scanner.nextLine();
//			 
//		 if (currentUser.enter(userLogin, userPassword) == true) { 
//			 currentUser = votingSystem.findUser(userLogin, userPassword);
//			 System.out.println("Вы вошли в систему, и можете голосовать."); 
//			 b = true; 
//			 break; 
//		 } else {
//			 System.out.println("Неверный логин или пароль, введите еще раз: "); 
//			 }
//		 } while (b == false);
		
		
			if(currentUser.enter(reg.getUserLogin(), reg.getUserPassord()) == true) {
			
			currentUser = votingSystem.findUser(reg.getUserLogin(), reg.getUserPassord());
				
			}
		 
//
//		System.out.println("Список кандидатов: ");
//		for (Candidate a : votig.getCandidates()) {
//			System.out.println(a.getName());
//		}

		
		for (Candidate a : votig.getCandidates()) {
			System.out.println(a.getName());
		}
		  
		 

		
		  System.out.println("Введите имя кандидата: "); 
		  String candidateName = scanner.nextLine();
		  
		  currentUser.setVoting(votig);
		  currentUser.vote(candidateName);
		 

		 List<Candidate> candidateList = votig.getCandidates();

		System.out.println("Результат голосования: ");

		/* List<Candidate> candidateList = votig.getCandidates(); */

		/*
		 * Collections.sort(votig.getCandidates(), new
		 * Candidate(candidateName)); 
		 * System.out.println(" ");
		 */

		for (Candidate a : votig.getCandidates()) {
			System.out.println(a.getName() + "  : " + a.getVoices() + ", ");
		}

		int imax = 0;
		for (int i = 1; i < votig.getCandidates().size(); i++) {

			if (votig.getCandidates().get(imax).getVoices() < votig.getCandidates().get(i).getVoices()) {
				imax = i;
			}
		}
		
		System.out.println("Победитель: " + votig.getCandidates().get(imax).getName());
		System.out.println();
		scanner.close();
		
		
	
	}
}

