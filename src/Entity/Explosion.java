package Entity;

import Graphics.Screen;
import Graphics.Sprite;

public class Explosion {
	private int x,y,time;
	private Sprite sprite = Sprite.Exp1;
	private Screen screen;
	public boolean removed=false;
	
	public Explosion(int x, int y, Screen screen){
		this.x=x;
		this.y=y;
		this.screen=screen;
		time=0;
	}
	
	public void update(){
		time++;
		if(time>=20)remove();
		sprite=time<5?Sprite.Exp1:time>=5&&time<10?Sprite.Exp2:time>=10&&time<15?Sprite.Exp3:time>=15?sprite.Exp4:Sprite.Enemy4;
	}
	
	private void remove() {
		removed = true;
	}

	public void render(){
		screen.renderSprite(x, y, sprite);
	}
}
