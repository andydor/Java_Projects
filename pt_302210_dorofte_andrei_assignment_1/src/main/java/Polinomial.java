import java.util.ArrayList;
import java.util.List;

public class Polinomial {
	private List <Monomial> monoame = new ArrayList <Monomial> ();
	
	public Polinomial() {
		
	}
	
	public void adaugaMonom(Monomial monom) {
		monoame.add(monom);
	}
	
	public Monomial getMonom(int i) {
		return monoame.get(i);
	}
	
	public void scoateMonom(int i) {
		monoame.remove(i);
	}
	
	public int getSize() {
		return this.monoame.size();
	}
	
	public Monomial getI(int i) {
		return this.monoame.get(i);
	}
	
	public List <Monomial> getList(){
		return this.monoame;
	}
	
	public String toString() {
		String out= " ";
		for(Monomial i : monoame){
			if(i.getCoeficient() > 0)
				out += " + " + i.getCoeficient() + "x^" + i.getPower();
			else if(i.getCoeficient() < 0)
				out += " - " + Math.abs(i.getCoeficient()) + "x^" + i.getPower();
			else if(i.getCoeficient() == 0)
				out += "";
			else
				out += " " + i.getCoeficient() + "x^" + i.getPower();
        }
        return out;  
    }
}