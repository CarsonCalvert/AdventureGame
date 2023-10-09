public class Attack {
    public static void main(String[] args) {
       //How would I do this and apply damage to the monsters
        int playerHealth = 100;
        int weaponDamage = 0;
    }
    
    public static int attack(int monsterHealth, int weaponDamage) {
        //random add to attack damage 1-10 more damage.
        int damage = 0;
        int randomAdd= (int) (Math.random() * 10 + 1);
        weaponDamage += randomAdd;
        if (weaponDamage > 0) {
            damage = weaponDamage;
        } else {
            damage = 1;
        }
        monsterHealth -= damage;
        return monsterHealth;
    }


    
    public static int monAttack(int playerHealth, int monDamage) {
        //random add to attack damage 1-10 more damage.
        int damage = 0;
        int randomAdd= (int) (Math.random() * 10 + 1);
        monDamage += randomAdd;
        if (monDamage > 0) {
            damage = monDamage;
        } else {
            damage = 1;
        }
        playerHealth -= damage;
        return playerHealth;
    }

public static void attackingTheMonster (int weaponDamage){
        int monster=0;
        int randMonster = (int) (Math.random() * 4 + 1);
        switch(randMonster){
            case 1:
            System.out.println("You have encountered a Goblin!");
            int gobHealth = 10;
            attack(gobHealth, weaponDamage);
            monster = 1;
            break;

            case 2:
            System.out.println("You have encountered a Troll!");
            int trollHealth = 25;
            attack(trollHealth, weaponDamage);
            monster=2;
            break;

            case 3:
            System.out.println("You have encountered a Orc!");
            int orcHealth = 60;
            attack(orcHealth, weaponDamage);
            monster=3;
            break;

            case 4:
            System.out.println("You have encountered a Minotaur!");
            int minoHealth = 100;
            attack(minoHealth, weaponDamage);
            monster=4;
            break;


        }
    }
}