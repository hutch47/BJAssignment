package core;

public enum Royal {
	J(10), Q(10), K(10);
	private int id;
	private Royal(int id) {
		this.id = id;
	}
	public int getValue() {
		return id;
	}
}
