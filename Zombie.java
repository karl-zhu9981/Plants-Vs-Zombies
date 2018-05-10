public class Zombie {
	private int v,hp, damage, x, y;
    public Zombie(int xx, int yy, int n){
    	x = xx;
    	y = yy;
    	if(n == 0){
    		v = 3;
    		hp = 100;
    		damage = 20;
    	}
    	else if(n == 1){
    		v = 4;
    		hp = 100;
    		damage = 20;
    	}
    	else if(n == 2){
    		v = 3;
    		hp = 150;
    		damage = 20;
    	}
    	else if(n == 3){
    		v = 2;
    		hp = 300;
    		damage = 35;
    	}
    	
    }
    public int getV(){
    	return v;
    }
    public int gethp(){
    	return hp;
    }
    public int getDam(){
    	return damage;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
}

class Plant{
	private int hp,damage,special,x,y,cycle;
	
	public Plant(int xx, int yy, int n){
		//0=walnut, 1=peashooter, 2=doublepeas, 3=icepea, 4=cherry, 5=sunflower
		cycle = 0;
    	if(n == 0){
    		x = xx;
    		y = yy;
    		hp = 300;
    		damage = 0;
    	}
    	else if(n == 1){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 25;
    	}
    	else if(n == 2){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 25;
    	}
    	else if(n == 3){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 20;
    		special = 1;
    	}
    	else if(n == 4){
    		x = xx;
    		y = yy;
    		hp = 50;
    		damage = 100;
    	}
    	else if(n == 5){
    		x = xx;
    		y = yy;
    		hp = 100;
    		damage = 0;
    		special = 2;
    	}
	}
	public void sprite(){
		cycle += 1;
		if(cycle==50){
			cycle = 0;
		}
	}
    	
    public int gethp(){
    	return hp;
    }
    public int getDam(){
    	return damage;
    }
    public int special(){
    	return special;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public int getS(){
    	return cycle;
    }
}
