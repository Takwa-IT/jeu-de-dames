package models;

public class MouvmentSimple {
    private Case destination;
    private Case depart;
    private Pion pion;

    public MouvmentSimple(Case dest, Pion pion) {
        this.depart = pion.getPosition();
        this.destination = dest;
        this.pion = pion;
    }

    public void seDeplacer() {
        int ligneDepart = depart.getLigne();
        char colDepart = depart.getColonne();
        int ligneDest = destination.getLigne();
        char colDest = destination.getColonne();

        if (!pion.estDame()) {
            int ligneValide = pion.getCouleur().equals("noir") ? ligneDepart - 1 : ligneDepart + 1;
            char colGauche = (char) (colDepart - 1);
            char colDroite = (char) (colDepart + 1);

            if ((colDest == colGauche || colDest == colDroite) && ligneDest == ligneValide) {
                pion.setPosition(destination);
            }
        } else {
            
            int diffLigne = Math.abs(ligneDest - ligneDepart);
            int diffCol = Math.abs(colDest - colDepart);

            if (diffLigne == diffCol && diffLigne >= 1) {
                pion.setPosition(destination);
            }
        }
    }
}

		
		
	
	


