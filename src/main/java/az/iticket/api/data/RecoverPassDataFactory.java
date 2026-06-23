package az.iticket.api.data;

import az.iticket.api.model.RecoverPassRequest;

public class RecoverPassDataFactory {

    public static RecoverPassRequest getEmptyEmail(){
        return new RecoverPassRequest("");
    }

    public static RecoverPassRequest getMessageRegisrtedUser(){
        return new RecoverPassRequest("omnqqmvgtlixqnwjtp@jbsze.com");
    }

    public static RecoverPassRequest getInvalidEmailMessageWithoutAtSymbols(){
        return new RecoverPassRequest("testtest.com");
    }

    public static RecoverPassRequest getInvalidEmailMessageWithDoubleAtSymbols(){
        return new RecoverPassRequest("test@@test.com");
    }

    public static RecoverPassRequest getInvalidEmailMessageWithoutDomainPart(){
        return new RecoverPassRequest("test");
    }

    public static RecoverPassRequest getInvalidEmailWithoutUsername(){
        return new RecoverPassRequest("@gmail.com");
    }

    public static RecoverPassRequest getInvalidEmailMessageWithTabCharacters(){
        return new RecoverPassRequest("test\\t@test.com");
    }

    public static RecoverPassRequest getInvalidEmailMessageWithNewLineCharacters(){
        return new RecoverPassRequest("test\\n@test.com");
    }

    public static RecoverPassRequest getInvalidEmailWithStartingSpaces(){
        return new RecoverPassRequest(" user@user.com");
    }

    public static RecoverPassRequest getInvalidEmailWithContainsMiddlwSpaces(){
        return  new RecoverPassRequest("user @user.com");
    }

    public static RecoverPassRequest getInvalidEmailWithEndingSpaces(){
        return new RecoverPassRequest("user@user.com ");
    }

    public static RecoverPassRequest getInvalidEmailWithOnlySpaces(){
        return new RecoverPassRequest("           ");
    }

    public static RecoverPassRequest getMessageNotRegisteredUser(){
        return new RecoverPassRequest("hatlfaxkhovnnrzaqr@onldm.net");
    }

    public static RecoverPassRequest getEmailMaxLength(){
        String email = "a".repeat(64) + "@" + "b".repeat(187) + ".com";
        return new RecoverPassRequest(email);
    }

    public static RecoverPassRequest getInvalidEmailWithDotAfterAtSymbols(){
        return new RecoverPassRequest("user@.user.com");
    }

    public static RecoverPassRequest getInvalidEmailWithCyrillicUsername(){
        return new RecoverPassRequest("юзер@user.com");
    }

    public static RecoverPassRequest getInvalidEmailWithoutDomainSuffixIsRejected(){
        return new RecoverPassRequest("user@test");
    }

    public static RecoverPassRequest getInvalidEmailWithDotBeforeAtSymbols(){
        return new RecoverPassRequest("user.@test.com");
    }

}
