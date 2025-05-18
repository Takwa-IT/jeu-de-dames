package utils;
import model.modelException.PositionInvalideException;
import models.Case;
import models.Pion;
import models.Plateau;
import views.AuthFrame;
import views.LoginOnlyFrame;
import views.MenuFrame;

public class test {

	public static void main(String[] args) throws PositionInvalideException {
	/*	AuthFrame frame1 = new AuthFrame();
		frame1.setVisible(true);
		
		MenuFrame menu1 = new MenuFrame();
		menu1.setVisible(false);

		
		LoginOnlyFrame log1 = new LoginOnlyFrame();
		log1.setVisible(false);*/
		Plateau p = new Plateau();
		p.afficherPlateau();
		Pion pion = p.getPion(8,1);
		System.out.print(pion.getNom());
		Case c = pion.getPosition();
		c.getColonne();
		 char  colone_valide_d =  (char)(c.getColonne()+1);
		
		System.out.print(colone_valide_d);
	}

}
