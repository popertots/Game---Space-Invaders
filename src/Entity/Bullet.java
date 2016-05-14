package Entity;

import Graphics.Screen;
import Graphics.Sprite;
import Level.world;

public class Bullet {
	private Screen screen;
	private Sprite sprite;
	private world level;
	public boolean removed=false;
	private int x=0,y=0,dir=0;
	
	public Bullet(int x, int y, Screen screen, world level, Boolean CanHitPlayer){
		this.x=x;
		this.y=y;
		this.screen=screen;
		this.level=level;
		sprite=new Sprite(2,0xffffff);
		dir=CanHitPlayer?1:-1;
	}
	
	public void update(){
		y+=2*dir;
		if(y<=0){
			remove();
		}
		if(dir==-1){
			for(int i = 0; i < level.Enemies.size();i++){
				for(int ya = 0; ya < 8;ya++){
					for(int xa = 0; xa < 12;xa++){
						if(x==level.Enemies.get(i).x+xa+2&&y==level.Enemies.get(i).y+ya+4){
							level.Enemies.get(i).Kill();
							removed=true;
						}
					}
				}
			}
			for(int i = 0; i < level.Motherships.size();i++){
				for(int ya = 0; ya < 7;ya++){
					for(int xa = 0; xa < 16;xa++){
						if(x==level.Motherships.get(i).x+xa&&y==level.Motherships.get(i).y+ya+3){
							level.Motherships.get(i).Kill();
							removed=true;
						}
					}
				}
			}
		}else{
			for(int ya = 0; ya < 8;ya++){
				for(int xa = 0; xa < 14;xa++){
					if(x==level.Players.get(0).x+xa+1&&y==level.Players.get(0).y+ya+8){
						removed=true;
						System.exit(1);
					}
				}
			}
		}
	}
	
	private void remove() {
		removed = true;
	}

	public void render(){
		screen.renderEntity(x, y, sprite, 0);
	}
}
