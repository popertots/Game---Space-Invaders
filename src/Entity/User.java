package Entity;

import Graphics.Screen;
import Graphics.Sprite;
import Input.Keyboard;
import Level.world;

public class User {
	private Screen Screen;
	private Keyboard key;
	private Sprite sprite;
	private world level;
	public int x=0,y=0,xa=0,ya=0;
	private boolean fired=false;
	private int fireTime=0;
	
	public User(int x, int y, Screen screen, Keyboard key, world level){
		this.x=x;
		this.y=y;
		this.Screen=screen;
		this.key=key;
		this.level=level;
		sprite = Sprite.player;
	}
	
	public void update(){
		xa=0;
		ya=0;
		if(fired)fireTime++;
		if(fireTime>=25){
			fired=false;
			fireTime=0;
		}
		if((key.enter||key.space)&&!fired){
			level.AddBullet(new Bullet(x+7, y+9, Screen,level, false));
			fired=true;
		}
		if(key.left)xa=-1;
		if(key.right)xa=1;
		move(xa, ya);
	}
	
	public void move(int xa, int ya){
		if (xa!=0&&ya!=0){
			move(xa,0);
			move(0,ya);
			return;
		}
		x += xa;
		y += ya;
	}
	
	public void render(){
		Screen.renderEntity(x, y, sprite, 0);
	}
}
