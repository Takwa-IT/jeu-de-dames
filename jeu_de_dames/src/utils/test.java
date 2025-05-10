package utils;
import views.AuthFrame;
import views.LoginOnlyFrame;
import views.MenuFrame;

public class test {

	public static void main(String[] args) {
		AuthFrame frame1 = new AuthFrame();
		frame1.setVisible(true);
		
		MenuFrame menu1 = new MenuFrame();
		menu1.setVisible(false);

		
		LoginOnlyFrame log1 = new LoginOnlyFrame();
		log1.setVisible(false);

		
	}

}
