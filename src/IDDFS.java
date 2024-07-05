import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class IDDFS extends SearchAlgorithm {
	HashMap<Node, Integer> frontier; //node, number of children 
	Node firstNode;
	Node current;
	int limit;
	int depth;
	long sumSteps;
	long start;
	long now;
	long end;
	
	public IDDFS () {
		this.frontier = new HashMap<>();
		this.firstNode = new FirstNode();
		this.current = null;
		sumSteps = 0;
		limit = 0;
		depth = 0;
	}

	public boolean iterativeDeepeningSearch(int searchValue) {
		boolean found = false;
		
		start = System.currentTimeMillis();
		end = start + 60 * 1000;
		
		while(!found && (System.currentTimeMillis() < end)) {
			if(searchDFS(searchValue)) {
				found = true;
				return true;
			}
			limit++;
			sumSteps = steps + sumSteps;
			steps = 0;
			uniqueValues = new ArrayList<>();
			frontier.clear();
		}
		return found;
	}
	
	public boolean searchDFS(int searchValue) {
		this.current = firstNode;
		
		uniqueValues.add(current.getValue());
		frontier.put(current, 0);
		depth = 0;

		boolean found = false;
		
		while(!found && (now < (end-start))) {
			if(isFound(current, searchValue)) {
				sumSteps = steps + sumSteps;
				System.out.println("(Overall steps: " + sumSteps + ")");
				return true;
			}
			
			current = next();
			
			if(current == null) {
				return false;
			}
			
			if(isCurrentAcceptable(current)) {
				uniqueValues.add(current.getValue());
				steps++;
			}
			else {
				if(current.getValue().equals("-1") || (valueExists(current) && (frontier.get(current) == 0))) {
					depth--;
					current = current.getParent();
					steps++;
				}
			}
			now = System.currentTimeMillis() - start;
		}
		return found;
	}
	
	public Node createChild() {
		Node n = null;
		if(frontier.get(current) == 0) {
			n = new RootNode(current);
			frontier.put(current, 1);
		}
		else if(frontier.get(current) == 1) {
			n = new FactorialNode(current);
			frontier.put(current, 2);
		}
		else if(frontier.get(current) == 2) {
			n = new FloorNode(current);
			frontier.put(current, 3);
		}
		frontier.put(n, 0);
		return n;
	}
	
	public Node next() {
		Node next = null;

		if((depth == limit) || (frontier.get(current) == 3)) {
			if(current.equals(firstNode)) {
				return null;
			}
			next = current.getParent();
			depth--;
		}
		else {
			next = createChild();
			depth++;
		}
		return next;
	}
	
	public boolean isCurrentAcceptable(Node next) {
		if(new BigDecimal(next.getValue()).compareTo(BigDecimal.ZERO) == 1) {
			if(!valueExists(next)) {
				return true;
			}
		}
		return false;
	}
}
