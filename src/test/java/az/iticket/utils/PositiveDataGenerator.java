package az.iticket.utils;

import net.datafaker.Faker;

import java.util.Locale;

public class PositiveDataGenerator {
    private static final Faker faker = new Faker();
    private static final Faker fakerRu = new Faker(new Locale("ru"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getEmailWithPlus() {
        return "user+test" + faker.number().digits(3)+ "@test.com";
    }

    public static String getEmailWithDot(){
        return "qa.test" + faker.number().digits(2) +"@test.com";
    }

    public static String getEmailWithSubDomain() {
        return "user" + faker.number().digits(2) + "@qa.test.com";
    }

    public static String getPhoneNumberAz() {
        return faker.number().digits(9);
    }

    public static String getPassword() {
        return "Test" + faker.number().digits(4);
    }

    public static String getConfirmPassword() {
        return "Test" + faker.number().digits(4);
    }
}
