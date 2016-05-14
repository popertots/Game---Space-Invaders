package Entity;

import java.util.Random;

import Graphics.Screen;
import Graphics.Sprite;
import Level.world;

public class Enemy {
	public long time=0;
	private Screen screen;
	private Sprite sprite;
	private world level;
	public int x=0,y=0;
	public int speed=5,DeltaSpeed=60;
	public boolean removed=false;
	private Random rnd = new Random();

	public Enemy(int x, int y, Screen screen,world level){
		this.x=x;
		this.y=y;
		this.screen=screen;
		this.level=level;
		int num = rnd.nextInt(3)+1;
		if(num==1)sprite=Sprite.Enemy1;
		if(num==2)sprite=Sprite.Enemy2;
		if(num==3)sprite=Sprite.Enemy3;
	}

	public void ChangeDir(){
		speed*=-1;
		x+=speed;
		y+=5;
	}
	
	public void Kill(){
		removed = true;
		level.Score+=50;
		level.AddExplosion(x, y);
	}

	public void update() {
		if(rnd.nextInt(5000+level.Enemies.size())==0){
			level.AddBullet(new Bullet(x,y,screen,level,true));
		}
		time++;
		if(x-8<=0){
			level.turnEnemies();
		}else if(x+24>=Screen.width){
			level.turnEnemies();
		}else if(time%DeltaSpeed==0)x+=speed;
	}

	public void render() {
		screen.renderEntity(x, y, sprite, 0);
	}
}
