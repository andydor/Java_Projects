import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexda {
	
	public Regexda() {
		
	}
	
	public static boolean isNumeric(String str) {
	    return str.matches("[+-]*\\d*\\.?\\d+");
	}
	
	public Polinomial rez(String text) {
		String monomialFormat = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
    	String monomialPartsFormat = "([+-]?[\\d\\.]*)([a-zA-Z]?)\\^?(\\d*)";
        Pattern p1 = Pattern.compile(monomialFormat);
        Matcher m1 = p1.matcher(text);
        Polinomial a = new Polinomial();
        while (!m1.hitEnd()) {
            m1.find();
            Pattern p2 = Pattern.compile(monomialPartsFormat);
            Matcher m2 = p2.matcher(m1.group());
            if (m2.find()) {
                double coefficient;
                try {
                    String coef = m2.group(1);
                    if (isNumeric(coef)) {
                        coefficient = Double.valueOf(coef);
                    } else {
                        coefficient = Double.valueOf(coef + "1");
                    }
                } catch (IllegalStateException e) {
                    coefficient = 0.0F;
                }
                Double exponent;
                try {
                    String exp = m2.group(3);
                    if (isNumeric(exp)) {
                        exponent = Double.valueOf(exp);
                    } else {
                        exponent = (double) 1;
                    }
                } catch (IllegalStateException e) {
                    exponent = (double) 0.0F;
                }
                Monomial b = new Monomial(coefficient, exponent);
                a.adaugaMonom(b);
            }
        }
        return a;
	}
}