/**
 * 
 */
package edu.phone.jenks.ted;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;
import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class RandomPhoneNumber extends AbstractRandomPhoneNumber {
	
	private final short MAX_PLUS1_EXCHANGE_EXCLUSIVE = 742 + 1;

	/* (non-Javadoc)
	 * @see edu.jenks.dist.phone.AbstractRandomPhoneNumber#generateRandomPhoneNumber()
	 */
	@Override
	public String generateRandomPhoneNumber() {
		StringBuilder sb = new StringBuilder(12);
		buildAreaCode(sb);
		sb.append("-");
		buildExchange(sb);
		sb.append("-");
		buildSubscriber(sb);
		return sb.toString();
	}
	
	private void buildAreaCode(StringBuilder sb) {
		for(int count = 3; count > 0; count--)
			sb.append(nextInt(8));
	}
	
	private void buildExchange(StringBuilder sb) {
		int exchange = nextInt(MAX_PLUS1_EXCHANGE_EXCLUSIVE);
		if(exchange < 100)
			sb.append(StringUtil.prependCharacter(exchange, '0', 3));
		else
			sb.append(exchange);
	}
	
	private void buildSubscriber(StringBuilder sb) {
		int subscriber = nextInt(10000);
		if(subscriber < 1000)
			sb.append(StringUtil.prependCharacter(subscriber, '0', 4));
		else
			sb.append(subscriber);
	}
	
	private int nextInt(int maxExclusive) {
		return (int)(Math.random() * maxExclusive);
	}
}
