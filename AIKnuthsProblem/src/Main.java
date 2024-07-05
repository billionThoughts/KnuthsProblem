import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Knuth's Problem");
		System.out.println("Please enter an integer: ");
		int searchValue = keyboard.nextInt();
		
		System.out.println("Select an algorithm (1 BFS, 2 Iterative Deepening DFS)");
		int algorithm = keyboard.nextInt();
		if(algorithm == 1) {
			BFS bfs = new BFS();
			long startTime = System.currentTimeMillis();
			bfs.searchBFS(searchValue);
			long endTime = System.currentTimeMillis();
			System.out.println((endTime - startTime)/1000 + " seconds");
		}
		else if (algorithm == 2) {
			IDDFS iddfs = new IDDFS();
			long startTime = System.currentTimeMillis();
			iddfs.iterativeDeepeningSearch(searchValue);
			long endTime = System.currentTimeMillis();
			System.out.println((endTime - startTime)/1000 + " seconds");
		}
		else System.out.println("Invalid answer");
		
		
		/*
		//test
		Node p = new FirstNode();
		FactorialNode n = new FactorialNode(p);
		System.out.println(n.factorial(n.factorial("24")));
		*/
		
	}
}
