package Objects.Entity;

public interface LivingStat {


    /**
     * Get entity health.
     * @return Entity health
     */
    public double getHP();

    /**
     * Get entity max health.
     * @return Entity maximal health
     */
    public double getMaxHP();

    /**
     * Set health of the entity.
     * @param HP amount of health
     */
    public void setHP(double HP);

    /**
     * Set maximal health of the entity.
     * @param maxHP amount of maximal health
     */
    public void setMaxHP(double maxHP);

}
