import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test1 {
	
    private static Operations c;
    static Polinomial a = new Polinomial();
	static Polinomial b = new Polinomial();

    // This annotation will ensure that the setup method is run once, before running all tests
	@BeforeClass
    public static void setup() {
        c = new Operations();
    	
    	Monomial x = new Monomial(2, 3);
    	Monomial x1 = new Monomial(1, 2);
    	Monomial x5 = new Monomial(1, 1);
    	Monomial x7 = new Monomial(1, 0);
    	Monomial x6 = new Monomial(1, 2);
    	Monomial x2 = new Monomial(2, 3);
    	Monomial x3 = new Monomial(2, 4);
    	
    	a.adaugaMonom(x3);
    	a.adaugaMonom(x);
    	a.adaugaMonom(x1);
    	a.adaugaMonom(x5);
    	a.adaugaMonom(x7); //2x^4+2x^3+1x^2+1x^1+1x^0
    	
    	b.adaugaMonom(x3);
    	b.adaugaMonom(x2);
    	b.adaugaMonom(x6); //2x^4+2x^3+1x^2
    }
	@Test
    public void addition_success() {
        assertEquals(c.adauga(a, b).toString(), "  + 4.0x^4.0 + 4.0x^3.0 + 2.0x^2.0 + 1.0x^1.0 + 1.0x^0.0");
    }
	@Test
    public void substraction_success() {
        assertEquals(c.scade(a, b).toString(),"  + 1.0x^1.0 + 1.0x^0.0");
    }
	@Test
    public void multiplication_succes() {
        assertEquals(c.multiply(a, b).toString(), "  + 4.0x^8.0 + 8.0x^7.0 + 8.0x^6.0 + 6.0x^5.0 + 5.0x^4.0 + 3.0x^3.0 + 1.0x^2.0");
    }
	@Test
    public void derivat_success() {
        assertEquals(c.derivat(b).toString(), "  + 8.0x^3.0 + 6.0x^2.0 + 2.0x^1.0");
    }
	@Test
    public void integr_success() {
        assertEquals(c.integr(b).toString(), "  + 0.4x^5.0 + 0.5x^4.0 + 0.3333333333333333x^3.0");
    }
}