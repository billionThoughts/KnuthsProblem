public abstract class Node {
	protected String operator;
	protected String path;
	protected Node parent;
	protected String value;
	
	public Node() {
		this.operator = "";
		this.parent = null;
		this.path = "";
	}

	public String getOperator() {
		return operator;
	}

	public Node getParent() {
		return parent;
	}

	public String getPath() {
		return path;
	}
	
	public String getValue() {
		return value;
	}
}
