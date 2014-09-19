package cs5200.jpa;

public enum CardType {
	Visa,
	MasterCard,
	AmericanExpress;
	
	public String toString() {
		String s = super.toString();
		return s;
	}
	public CardType toCardType(String str) {
		if (str.equals("Visa")) {
			return CardType.Visa;
		} else if (str.equals("MasterCard")) {
			return CardType.MasterCard;
		} else if (str.equals("AmericanExpress")) {
			return CardType.AmericanExpress;
		} else {
			return null;
		}
	}
}
