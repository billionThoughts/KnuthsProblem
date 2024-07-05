import java.math.*;

public class RootNode extends Node{
	
	public RootNode(Node parent) {
		this.operator = "root";
		this.parent = parent;
		this.path = (parent.getPath() + " " + this.operator);
		this.value = sqrt(new BigDecimal(parent.getValue())).toString();
	}
	
    public BigDecimal getInitialApproximation(BigDecimal x) {
        BigInteger integerPart = x.toBigInteger();
        int length = integerPart.toString().length();
        if ((length % 2) == 0) {
            length--;
        }
        length /= 2;
        BigDecimal guess = BigDecimal.ONE.movePointRight(length);
        return guess;
    }
	
    // square root of big numbers
    // source: https://www.sixhat.net/square-root-big-numbers-java-20101110.html 
	public BigDecimal sqrt(BigDecimal x) {
		int scale = 20;
		int maxIterations = 10;
		BigDecimal error;
		
		if(x.compareTo(BigDecimal.ZERO) <= 0) {
			return new BigDecimal("-1");
		}
		
		BigDecimal initialGuess = getInitialApproximation(x);
	    BigDecimal lastGuess = BigDecimal.ZERO;
	    BigDecimal guess = new BigDecimal(initialGuess.toString());
	    
	    int i = 0;
        boolean more = true;
        while (more) {
            lastGuess = guess;
            guess = x.divide(guess, scale, RoundingMode.HALF_UP);
            guess = guess.add(lastGuess);
            guess = guess.divide(new BigDecimal("2"), scale, RoundingMode.HALF_UP);
            error = x.subtract(guess.multiply(guess));
            if (++i >= maxIterations) {
                more = false;
            } else if (lastGuess.equals(guess)) {
                more = error.abs().compareTo(BigDecimal.ONE) >= 0;
            }
        }
        return guess.stripTrailingZeros();
    }
		
}


