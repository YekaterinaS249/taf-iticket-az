package az.iticket.utils;

import net.datafaker.Faker;

import java.util.Locale;

public class TestDataGenerator {
    private static final Faker faker = new Faker();
    private static final Faker fakerRu = new Faker(new Locale("ru"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().username() + "@gmail.com";
    }

    public static String getFirstNameRu() {
        return fakerRu.name().firstName();
    }

    public static String getFirstNameByLength(int length) {
        return "A".repeat(length);
    }

    public static String getLastNameRu() {
        return fakerRu.name().lastName();
    }

    public static String getEmailWithPlus() {
        return "user+test" + faker.number().digits(3) + "@test.com";
    }

    public static String getEmailWithDot() {
        return "qa.test" + faker.number().digits(2) + "@test.com";
    }

    public static String getEmailWithSubDomain() {
        return "user" + faker.number().digits(2) + "@qa.test.com";
    }

    public static String getPhoneNumberAz() {
        String[] prefixes = {"50", "55", "77", "70"};
        String prefix = prefixes[faker.random().nextInt(prefixes.length)];
        String rest = faker.number().digits(7);
        return prefix + rest;

    }

    public static String getPassword() {
        return "Test" + faker.number().digits(4);
    }

    public static String getConfirmPassword(String password) {
        return password;
    }

    public static String getLonNumberPhone() {
        return faker.lorem().characters(10);
    }

    public static String getEmailWithoutAt() {
        return faker.internet().emailAddress().replace("@", "");
    }

    public static String getEmailWithoutDomen() {
        return faker.name().username() + "@";
    }

    public static String getEmailWithoutUserName() {
        return "@" + faker.internet().domainWord() + ".com";
    }

    public static String getFirstNameTooLong(int length) {
        return "A".repeat(256);
    }
}

