package koroler.Spring.IoCDI_DZ;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCdiDzApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
		player.getMusicListTrack(0).setName("\"J.S. Bach: Prelude in C Major\"");
		System.out.println(player.playMusic());
		context.close();
	}

}
