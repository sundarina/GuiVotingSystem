import java.util.Comparator;

public class Candidate implements Comparable<Candidate>, Comparator<Candidate> {
	private String name;
	private int voices = 0;

	public Candidate(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVoices() {
		return voices;
	}

	public void addVoice() {
		voices++;
	}

	@Override
	public int compareTo(Candidate c) { // сортировка по имени
		return (this.name).compareTo(c.name);
	}

	@Override
	public int compare(Candidate c1, Candidate c2) { // сортировка по голосам
		return c1.voices - c2.voices; 
	}
}