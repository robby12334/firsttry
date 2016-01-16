import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import arcadia.Arcadia;
import arcadia.Button;
import arcadia.Game;
import arcadia.Input;
import arcadia.Sound;
import intro.IntroGame;

public class MyGame extends Game {

	float y = 10;
	float velocity = 0;
	float gravity = 0.5f;
	boolean canJump = false;
	Image banner;
	
	public MyGame(){
		try {
			banner = ImageIO.read(MyGame.class.getResource("banner.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick(Graphics2D g, Input p1, Input p2, Sound s) {

		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.BLACK);
		g.fillOval(0, (int) y, 100, 100);

		velocity += gravity;
		y += velocity;
		
		if (y + 100 > HEIGHT) {
			velocity = 0;
			y = HEIGHT-100;
			canJump = true;
		} 
		if (canJump && p1.pressed(Button.A)){
			velocity  = -15;		
			canJump = false;
		}

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image banner() {
		// 512 x 128
		return banner;
	}

	public static void main(String[] args) {
		Arcadia.display(new Arcadia(new Game[] {new MyGame(), new IntroGame()} ));
	}
}
