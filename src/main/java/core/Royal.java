package core;

// Method of custom enum taken from JavaHowTo.blogspot.com
// URL: http://javahowto.blogspot.com/2008/04/java-enum-examples.html

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
