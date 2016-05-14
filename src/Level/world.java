package Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.Bullet;
import Entity.Enemy;
import Entity.Explosion;
import Entity.MotherShip;
import Entity.User;
import Graphics.Screen;
import Input.Keyboard;

public class world {
	
	public int width, height, num=0,turn=0;
	private Screen Screen;
	protected Random rnd = new Random();
	
	public List<User> Players = new ArrayList<User>();
	public List<Enemy> Enemies = new ArrayList<Enemy>();
	public List<Explosion> Explosions = new ArrayList<Explosion>();
	public List<MotherShip> Motherships = new ArrayList<MotherShip>();
	public List<Bullet> Bullets = new ArrayList<Bullet>();
	public int Score=0;

	public world(int width, int height, Screen screen, Keyboard key){
		this.width=width;
		this.height=height;
		this.Screen = screen;
		Players.add(new User(Screen.width/2-8, Screen.height-27, screen, key, this));
		for(int o = 0; o < 6;o++){
			for(int i = 0; i < 16;i++){
				Enemies.add(new Enemy(32+(i*16), 8+(o*16), screen, this));
			}
		}
	}
	
	public void AddBullet(Bullet B){
		Bullets.add(B);
	}
	
	public void AddExplosion(int x, int y){
		Explosions.add(new Explosion(x, y, Screen));
	}
	
	public void RemoveBullets(Bullet B){
		Bullets.remove(B);
	}
	
	public void RemoveEnemies(Enemy E){
		Enemies.remove(E);
	}
	
	public void turnEnemies(){
		turn++;
		if(turn==1){
			for(int i = 0; i < Enemies.size();i++){
				Enemies.get(i).ChangeDir();
			}
		}
	}
	
	public void update(){
		if(rnd.nextInt(2500)==0&&Motherships.size()==0){
			Motherships.add(new MotherShip(-32, -2, Screen, this));
		}
		turn=0;
		for(int i = 0; i < Players.size();i++){
			Players.get(i).update();
		}
		for(int i = 0; i < Explosions.size();i++){
			Explosions.get(i).update();
		}
		for(int i = 0; i < Enemies.size();i++){
			Enemies.get(i).DeltaSpeed=(30-(Score/200));
			Enemies.get(i).update();
		}
		
		for(int i = 0; i < Motherships.size();i++){
			Motherships.get(i).update();
		}
		for(int i = 0; i < Bullets.size();i++){
			Bullets.get(i).update();
		}
		removeStuff();
		if(Enemies.size()>0){
			if(Enemies.get(Enemies.size()-1).y>=Screen.height-24){
				System.exit(1);
			}
		}
	}
	
	private void removeStuff() {
		for(int i = 0; i < Bullets.size();i++){
			if(Bullets.get(i).removed){	
				RemoveBullets(Bullets.get(i));
			}
		}
		for(int i = 0; i < Enemies.size();i++){
			if(Enemies.get(i).removed){	
				RemoveEnemies(Enemies.get(i));
			}
		}
		for(int i = 0; i < Motherships.size();i++){
			if(Motherships.get(i).removed){	
				Motherships.remove(i);
			}
		}
		for(int i = 0; i < Explosions.size();i++){
			if(Explosions.get(i).removed){	
				Explosions.remove(i);
			}
		}
	}

	public void render(){
		for(int i = 0; i < Screen.width;i++){
			Screen.pixels[i+(Screen.height-11)*Screen.width]=0x00aa00;
		}
		for(int i = 0; i < Players.size();i++){
			Players.get(i).render();
		}
		for(int i = 0; i < Enemies.size();i++){
			Enemies.get(i).render();
		}
		for(int i = 0; i < Motherships.size();i++){
			Motherships.get(i).render();
		}
		for(int i = 0; i < Bullets.size();i++){
			Bullets.get(i).render();
		}
		for(int i = 0; i < Explosions.size();i++){
			Explosions.get(i).render();
		}
	}
}
