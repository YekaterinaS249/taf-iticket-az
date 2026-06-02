package az.iticket.utils;

import net.datafaker.Faker;

public class NegativeDataGenerator {
    private static final Faker faker = new Faker();

    public static String getLonNumberPhone() {
        return faker.lorem().characters(10);
    }

    public static String getEmailWithoutAt() {
        return faker.internet().emailAddress().replace("@", "");
    }

    public static String getEmailWithoutDomen() {
        return faker.name().username() + "@";
    }

    public static String getEmailWithoutUserName(){
        return "@" + faker.internet().domainWord() + ".com";
    }

    public static String getFirstNameTooLong(int length) {
        return "A".repeat(256);
    }

}
