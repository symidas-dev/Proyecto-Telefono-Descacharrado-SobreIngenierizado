package lib.Generators;

import java.util.Random;

/**
 * RandomNameGenerator
 */
public class RandomNameGenerator {
    private final static String[] names = { "Juan", "Pedro", "Maria", "Jose", "Luis", 
       "Carlos", "Ana", "Marta", "Rosa", "Lucia", "Elena", "Sofia", "Carmen", "Pilar" };

    public static String apply() {
        return names[new Random().nextInt(names.length)];
    }

}
