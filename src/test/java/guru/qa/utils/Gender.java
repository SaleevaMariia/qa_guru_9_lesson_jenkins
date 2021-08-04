package guru.qa.utils;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    MALE("Male", 1),
    FEMALE("Female", 2),
    OTHER("Other", 3);

    private String title;
    private int id;

    Gender(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public static Optional<Gender> getGenderForId(int id){
        return Arrays.stream(Gender.values()).filter(e -> e.id == id).findFirst();
    }
}
