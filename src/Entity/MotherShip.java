package Entity;

import Graphics.Screen;
import Graphics.Sprite;
import Level.world;

public class MotherShip {
	public int x=0,y=0;
	private Screen screen;
	private world level;
	private Sprite sprite;
	public boolean removed=false;
	
	public MotherShip(int x, int y, Screen screen,world level){
		this.x=x;
		this.y=y;
		this.screen=screen;
		this.level=level;
		sprite=Sprite.Enemy4;
	}
	
	public void update(){
		x++;
		if(x>screen.width){
			remove();
		}
	}
	
	public void Kill(){
		remove();
		level.Score+=250;
	}
	
	public void remove(){
		removed =true;
	}
	
	public void render(){
		screen.renderEntity(x, y, sprite, 0);
	}
}
