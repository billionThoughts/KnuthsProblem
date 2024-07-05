import java.math.BigDecimal;
import java.util.ArrayList;

public class BFS extends SearchAlgorithm{
	private ArrayList<Node> frontier;
	protected ArrayList<Node> tempFrontier;

	public BFS() {
		this.frontier = new ArrayList<Node>();
		tempFrontier = new ArrayList<Node>();
	}

	public boolean searchBFS(int searchValue) {
		Node firstNode = new FirstNode();
		frontier.add(firstNode);
		uniqueValues.add(firstNode.getValue());
		
		boolean found = false;
		if(isFound(firstNode, searchValue)) {
			found = true;
			return found;
		}
		
		long start = System.currentTimeMillis();
		long end = start + 60 * 1000;

		while((!found) && (System.currentTimeMillis() < end)) {
			for(Node parent: frontier) {
				int counter;
				if(!(System.currentTimeMillis() < end)) {
					break;
				}
				for(counter = 1; counter <=3; counter++) {
					Node n = createChild(counter, parent);
					steps++;
					//if the value of n is > 0
					if((new BigDecimal(n.getValue()).compareTo(BigDecimal.ZERO) == 1)) {
						if(!valueExists(n)) {
							if(isFound(n, searchValue)) {
								found = true;
								return found;
							}
							uniqueValues.add(n.getValue());
							tempFrontier.add(n);
						}
					}
				}
			}
			frontier.removeAll(frontier);
			for(Node n : tempFrontier) {
				frontier.add(n);
			}
			tempFrontier.removeAll(tempFrontier);
			if(frontier.size() == 0) {
				return false;
			}
		}
		return found;
	}
	
	public Node createChild(int counter, Node parent) {
		Node n = null;
		if(counter == 1) {
			n = new RootNode(parent);
		}
		else if (counter == 2) {
			n = new FactorialNode(parent);
		}
		else if(counter == 3) {
			n = new FloorNode(parent);
		}
		return n;
	}
}
