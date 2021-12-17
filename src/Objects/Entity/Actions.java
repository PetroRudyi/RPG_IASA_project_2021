package Objects.Entity;

import Window.Settings;

public interface Actions {

    public void move(int i);

    public boolean isEnemy (int m);

    public void attack(Entity entity);

    public void applyDamage(int damage);

    public void dead();


}
