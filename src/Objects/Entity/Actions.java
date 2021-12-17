package Objects.Entity;

import Window.Settings;

public interface Actions {

    void move(int i);

    boolean isEnemy (int m);

    void attack(Entity entity);

    void applyDamage(int damage);

    void dead();


}
