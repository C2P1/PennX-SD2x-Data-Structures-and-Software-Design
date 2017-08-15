/*
 * SD2x Homework #8
 * This class creates the classes in the three tiers and links them together.
 */

public class Main {
	
	public static void main(String[] args) {
		
		DataTier dt = new DataTier(args[0]);
		LogicTier lt = new LogicTier(dt);
		PresentationTier pt = new PresentationTier(lt);
		pt.start();
		
	}

}
