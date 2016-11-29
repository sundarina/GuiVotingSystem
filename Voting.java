import java.util.ArrayList;
import java.util.List;

class Voting {
	private static String title;
	private List<Candidate> candidates;

	public Voting(String title) {
		this.title = title;
		this.candidates = new ArrayList<Candidate>(); // каждый раз новый список
														// ??
	}

	public void addCandidates(Candidate cnd) {
		candidates.add(cnd);
	}

	public static String getTitle() {
		return title;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}
}
