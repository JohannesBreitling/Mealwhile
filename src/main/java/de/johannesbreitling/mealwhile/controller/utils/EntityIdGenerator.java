package de.johannesbreitling.mealwhile.controller.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

/**
 * Class for custom id string generation for the repositories
 * Creates ids that only consist of letters
 *
 * @author Johannes Breitling
 */
public class EntityIdGenerator implements IdentifierGenerator {

    private final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ID_LENGTH = 12;

    private String randomString(int length, String letters) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = Math.abs(random.nextInt()) % letters.length();
            result.append(letters.charAt(index));
        }

        return result.toString();
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return randomString(ID_LENGTH, CHARS);
    }

}
