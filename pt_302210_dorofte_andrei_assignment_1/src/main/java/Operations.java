public class Operations {	
	public Operations() {
		
	}
	
	public Polinomial adauga(Polinomial a, Polinomial b) {
		Polinomial rez= new Polinomial();
        Monomial x=new Monomial(0, 0);
        Monomial y=new Monomial(0, 0);
        Polinomial rez1 = new Polinomial();
        int i = 0, j = 0;      
        while(i < a.getSize() && j < b.getSize()) {
            x = a.getI(i);
            y = b.getI(j);        
            if (x.getPower() > y.getPower()) {
            	rez.adaugaMonom(x);
            	i++;
            }
            else {
                if (x.getPower() < y.getPower()) {
                	rez.adaugaMonom(y);
                	j++;
                }
                else {
                	rez.adaugaMonom(new Monomial(x.getCoeficient() + y.getCoeficient(), x.getPower()));
                	i++;
                	j++;
                }
            }
            rez1 = adauga(rez1, rez);
            rez.getList().clear();
        }
        while(i < a.getSize()) {
        	x = a.getI(i);
            rez1.adaugaMonom(x);
        	i++;
        }
        while(j < b.getSize()) {
        	y = b.getI(j);
        	rez1.adaugaMonom(y);
        	j++;
        }
        return rez1;
	}
	
	public Polinomial multiply(Polinomial a, Polinomial b) {
		Polinomial rez= new Polinomial();
        Monomial x=new Monomial(0, 0);
        Monomial y=new Monomial(0, 0);
        int i = 0, j = 0;
        Polinomial rez1 = new Polinomial();
        while(i < a.getSize()) {
        	x = a.getI(i);
        	i++;
        	j = 0;
        while(j < b.getSize()) {
        	y = b.getI(j);
        	rez.adaugaMonom(new Monomial(x.getCoeficient() * y.getCoeficient(), x.getPower() + y.getPower()));
        	j++;
        }
        rez1 = adauga(rez1, rez);
        rez.getList().clear();
        }
        return rez1;
	}
	
	public Polinomial scade(Polinomial a, Polinomial b) {
		Polinomial rez= new Polinomial();
        Monomial x=new Monomial(0, 0);
        Monomial y=new Monomial(0, 0);
        Polinomial rez1 = new Polinomial();
        int i = 0, j = 0;      
        while(i < a.getSize() && j < b.getSize()) {
            x = a.getI(i);
            y = b.getI(j);        
            if (x.getPower() > y.getPower()) {
            	rez.adaugaMonom(x);
            	i++;
            }
            else {
                if (x.getPower() < y.getPower()) {
                	rez.adaugaMonom(new Monomial(0 - y.getCoeficient(), y.getPower()));
                	j++;
                }
                else {
                	rez.adaugaMonom(new Monomial(x.getCoeficient() - y.getCoeficient(), x.getPower()));
                	i++;
                	j++;
                }
            }
            rez1 = adauga(rez1, rez);
            rez.getList().clear();
        }
        while(i < a.getSize()) {
        	x = a.getI(i);
            rez.adaugaMonom(x);
        	i++;
        }
        while(j < b.getSize()) {
        	y = b.getI(j);
        	rez.adaugaMonom(new Monomial(0 - y.getCoeficient(), y.getPower()));
        	j++;
        }
        return rez;
	}
	
	public Polinomial derivat(Polinomial b) {
		Polinomial rez = new Polinomial();
		Polinomial rez1 = new Polinomial();
			for(Monomial i : b.getList()) {
				double aux = i.getCoeficient();
				double aux1 = i.getPower();
				if(aux1 != 0){
					Monomial rez2 = new Monomial(aux * aux1, aux1 - 1);
					rez.adaugaMonom(rez2);
				}
				else if(aux1 == 0){
					Monomial rez2 = new Monomial(0, 0);
					rez.adaugaMonom(rez2);
				}
				rez1 = adauga(rez1, rez);
		        rez.getList().clear();
			}
		return rez1;
	}
	
	public Polinomial integr(Polinomial b) {
		Polinomial rez = new Polinomial();
		Polinomial rez1 = new Polinomial();
			for(Monomial i : b.getList()) {
				double aux = i.getCoeficient();
				double aux1 = i.getPower();
				if(aux1 != 0){
					Monomial rez2 = new Monomial(aux / (aux1 + 1), aux1 + 1);
					rez.adaugaMonom(rez2);
				}
				else if(aux1 == 0){
					Monomial rez2 = new Monomial(0, 0);
					rez.adaugaMonom(rez2);
				}
				rez1 = adauga(rez1, rez);
		        rez.getList().clear();
			}
		return rez1;
	}
}