import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class SearchAlgorithm {
	protected ArrayList<String> uniqueValues;
	protected long steps;
	
	public SearchAlgorithm () {
		uniqueValues = new ArrayList<String>();
		this.steps = 0;
	}
	
	public boolean isFound(Node n, int searchValue) {
		BigDecimal v = new BigDecimal(n.getValue());
		BigDecimal sv = new BigDecimal(Integer.toString(searchValue));
		if(v.compareTo(sv) == 0) {
			System.out.println("!!! Found " + n.getValue() + " !!! Steps: " + steps + " Path: " + n.getPath());
			return true;
		}
		return false;
	}
	
	public boolean valueExists(Node n) {
		BigDecimal nv = new BigDecimal(n.getValue());
		BigDecimal uv;
		for(String v: uniqueValues) {
			uv = new BigDecimal(v);
			if(nv.subtract(uv).abs().compareTo(new BigDecimal("0.00000001")) < 1) {
				return true;
			}
		}
		return false;
	}

}
