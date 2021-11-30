public class Memento {
	
	private String state;
	
	public void set(String state) {
		this.state = state;
		System.out.println("New state set."); 
	}
	
	public ScribeMemento createScribeMemento() {
		System.out.println("State saved to ScribeMemento.");
		return new ScribeMemento(this.state);
	}
	
	public String restoreScribeMemento(ScribeMemento memento) {
		this.state = memento.getState();
		System.out.println("\nState restored");
		return state;
	}
	
	public class ScribeMemento {
		private final String state;
		public ScribeMemento(String setState) {
			state = setState;
		}
		private String getState() {
			return state;
		}
	}
}