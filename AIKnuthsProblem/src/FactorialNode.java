import java.math.*;

public class FactorialNode extends Node{
	private String limit = "2000"; 						// A limit needs to be in place in order to calculate  								
														// only the computable factorial nodes, or else the 
														// search stops at level 3 [trying to compute node (((4)!)!)! forever]
	public FactorialNode(Node parent) {
		this.operator = "factorial";
		this.parent = parent;
		this.path = (parent.getPath() + " " + this.operator);
		if(isInteger(parent.getValue()) && isComputable(parent.getValue())) {
			this.value = factorial(parent.getValue());
		}
		else value = "-1";
	}
	
	public boolean isInteger(String value) {
		BigDecimal v = new BigDecimal(value);
		BigInteger fvi = v.setScale(0, RoundingMode.FLOOR).unscaledValue();
		BigDecimal fv = new BigDecimal(fvi.toString());
		if(v.subtract(fv).compareTo(new BigDecimal("0.00000001")) == -1) {
			return true;
		}
		return false;
	}
	
	public boolean isComputable(String value) {
		BigInteger v = new BigInteger(value);
		BigInteger max = new BigInteger(limit);
		if(v.compareTo(max) == -1) {
			return true;
		}
		else return false;
	}
	
	public String factorial(String value) {
		BigInteger result = BigInteger.ONE;
		BigInteger v = new BigInteger(value);
		
		while(v.compareTo(BigInteger.ONE) > 0) {
			result = result.multiply(v);
			v = v.subtract(BigInteger.ONE);
		}
		return result.toString();	
	}
}
