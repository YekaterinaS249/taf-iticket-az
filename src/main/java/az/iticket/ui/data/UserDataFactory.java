package az.iticket.ui.data;

import net.datafaker.Faker;

import java.util.Locale;


public class UserDataFactory {
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

    public static String getInvalidLengthNumber() {
        String [] prefixes = {"50", "55", "77", "70"};
        String prefix = prefixes[faker.random().nextInt(prefixes.length)];
        String rest = faker.number().digits(8);
        return prefix + rest;
    }

    public static String getLongPhoneNumber(int length) {
        return faker.regexify("[0-9]{" + length + "}");
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

    public static String getLastNameTooLong(int length) {
        return "B".repeat(256);
    }

    public static String getLastNameByLength(int length) {
        return "C".repeat(length);
    }

    public static String getUserWithEmailContainingPlus() {
        return faker.name().username() + "+test@gmail.com";
    }

    public static String getEmailContainsLeadingSpace() {
        return " " + faker.name().username() + faker.number().digits(3) + "@gmail.com";
    }

    public static String getEmailContainsSpaceinMiddle() {
        return faker.internet().emailAddress().replace("@", " @");
    }

    public static String getEmailContainsEndingSpace() {
        return faker.internet().emailAddress() + " ";
    }

    public static String getEmailWithTabCharacters() {
        return faker.internet().emailAddress().replace("@", "\\t@");
    }

    public static String getEmailWithNewLineCharacters() {
        return faker.internet().emailAddress().replace("@", "\\n");
    }

    public static String getPasswordByLength(int length) {
        return faker.regexify("[A-Za-z0-9]{" + length + "}");
    }

    public static String getSpecialSymbolsPassword(int length) {
        return faker.regexify("[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>?,./]{" + length + "}");

    }

    public static String getOnlyDigitsPassword(int length) {
        return faker.regexify("[0-9]{" + length + "}");
    }

    public static String getPasswordWithLeadingPassword() {
        return " " + faker.internet().password(8, 8);
    }

    public static String getPasswordWithMiddlePassword() {
        String password = faker.internet().password(8, 8);
        int middle = password.length() / 2;
        return password.substring(0, middle) + " " + password.substring(middle);
    }

    public static String getPasswordWithEndingPassword() {
        return faker.internet().password(8, 8) + " ";
    }
}

