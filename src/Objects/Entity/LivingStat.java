package Objects.Entity;

public interface LivingStat {


    /**
     * Get entity health.
     * @return Entity health
     */
    public int getHP();

    /**
     * Get entity max health.
     * @return Entity maximal health
     */
    public int getMaxHP();

    /**
     * Set health of the entity.
     * @param HP amount of health
     */
    public void setHP(int HP);

    /**
     * Set maximal health of the entity.
     * @param maxHP amount of maximal health
     */
    public void setMaxHP(int maxHP);

}
