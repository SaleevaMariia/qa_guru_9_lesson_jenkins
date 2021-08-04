package guru.qa.page;

import guru.qa.utils.TestData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    public void openPage(){
        open("/automation-practice-form");
        closeGoogleAdvert();
    }

    private void checkField(String fieldName, String expectedValue){
        $(".table-responsive tbody").$(byText(fieldName)).sibling(0).shouldHave(text(expectedValue));
    }

    private void closeGoogleAdvert(){
        if ($("#close-fixedban").exists()){
            $("#close-fixedban").click();
        }
    }

    public RegistrationPage typeFirstName(){
        $("#firstName").setValue(TestData.firstName);
        return this;
    }

    public RegistrationPage typeLastName(){
        $("#lastName").setValue(TestData.lastName);
        return this;
    }

    public RegistrationPage typeEmail(){
        $("#userEmail").setValue(TestData.email);
        return this;
    }

    public RegistrationPage chooseGender(){
        $("#genterWrapper").$(byText(TestData.gender.getTitle())).click();
        return this;
    }

    public RegistrationPage typeUserNumber(){
        $("#userNumber").setValue(TestData.userNumber);
        return this;
    }

    public RegistrationPage typeBirthData(){
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(TestData.birthData.getYear());
        $(".react-datepicker__month-select").selectOptionByValue(String.valueOf(TestData.birthData.getMonth()));
        $$(".react-datepicker__day--" + String.format("%03d", TestData.birthData.getDate())).
                filter(not(cssClass(".react-datepicker__day--outside-month"))).first().click();
        return this;
    }

    public RegistrationPage chooseSubjects(){
        for (String s: TestData.subjects) {
            $("#subjectsInput").setValue(s).pressEnter();
        }
        return this;
    }

    public RegistrationPage chooseHobbies(){
        for (String h:TestData.hobbies) {
            $("#hobbiesWrapper").$(byText(h)).click();
        }
        return this;
    }

    public RegistrationPage uploadPicture(){
        $("#uploadPicture").uploadFromClasspath(TestData.picture);
        return this;
    }

    public RegistrationPage typeAddress(){
        $("#currentAddress").setValue(TestData.address);
        return this;
    }

    public RegistrationPage chooseState(){
        $("#state").scrollTo().click();
        $(byText(TestData.state)).click();
        return this;
    }

    public RegistrationPage chooseCity(){
        $("#city").click();
        $(byText(TestData.city)).click();
        return this;
    }

    public void clickSubmit(){
        $("#submit").click();
    }

    public RegistrationPage fillAllPositiveData(){
        this.typeFirstName().typeLastName().typeEmail();
        this.chooseGender();
        this.typeUserNumber().typeBirthData().chooseSubjects();
        this.chooseHobbies().uploadPicture().typeAddress();
        this.chooseState().chooseCity();
        return this;
    }

    public void checkResult(){
        checkField("Student Name", TestData.firstName + " " + TestData.lastName);
        checkField("Student Email", TestData.email);
        checkField("Gender", TestData.gender.getTitle());
        checkField("Mobile", TestData.userNumber);
        checkField("Date of Birth", TestData.formatBirthToCheck());
        checkField("Subjects", String.join(", ", TestData.subjects));
        checkField("Hobbies", String.join(", ", TestData.hobbies));
        checkField("Picture", TestData.picture);
        checkField("Address", TestData.address);
        checkField("State and City", TestData.state + " " + TestData.city);
    }


}
