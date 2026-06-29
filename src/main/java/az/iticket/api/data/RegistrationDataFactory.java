package az.iticket.api.data;

import az.iticket.api.model.RegistrationRequest;
import net.datafaker.Faker;

public class RegistrationDataFactory {
    private static final Faker faker = new Faker();

    public static RegistrationRequest getShortPhoneNumber () {
        return  new RegistrationRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                "+99455",
                faker.internet().emailAddress(),
                faker.internet().password(8,8));
    }

     public static RegistrationRequest getMaxLengthPhoneNumber() {
        return  new RegistrationRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                "+994707772223232323232",
                faker.internet().emailAddress(),
                faker.internet().password(8,8));
     }

     public static RegistrationRequest getPhoneNumberFieldRejectsLetters() {
        return  new RegistrationRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                "qwerty",
                faker.internet().emailAddress(),
                faker.internet().password(8,8));
     }

     public static RegistrationRequest getPhoneNumberFieldRejectsOnlySymbols() {
        return  new RegistrationRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                "!!!@@@@#####",
                faker.internet().emailAddress(),
                faker.internet().password(8,8));
     }
}
