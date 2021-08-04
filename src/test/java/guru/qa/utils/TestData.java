package guru.qa.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestData {
    public static String firstName;
    public static String lastName;
    public static String email;
    public static Gender gender;
    public static String userNumber;
    public static Date birthData;
    public static List<String> subjects;
    public static List<String> hobbies;
    public static String picture;
    public static String address;
    public static String city;
    public static String state;

    public static void initPositiveTestData(){
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("ru-RU"), new RandomService());

        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = fakeValuesService.bothify("??????##@gmail.com");
        gender = Gender.getGenderForId(faker.number().numberBetween(1, 3)).get();
        userNumber = fakeValuesService.bothify("##########");
        birthData = faker.date().birthday();
        subjects = Arrays.asList("Maths", "Computer Science", "Accounting");
        hobbies = Arrays.asList("Sports", "Reading");
        picture = "Smile.png";
        address = faker.address().fullAddress();
        state = "Haryana";
        city = "Panipat";
    }

    public static String formatBirthToCheck(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);
        return dateFormat.format(birthData);
    }

}
