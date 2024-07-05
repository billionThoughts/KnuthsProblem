import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class FloorNode extends Node{
	
	public FloorNode(Node parent) {
		this.operator = "floor";
		this.parent = parent;
		this.path = (parent.getPath() + " " + this.operator);
		this.value = floor(parent.getValue());
	}
	
	public String floor(String value) {
		BigDecimal v = new BigDecimal(value);
		BigInteger fvi = v.setScale(0, RoundingMode.FLOOR).unscaledValue();
		return fvi.toString();
	}
}
