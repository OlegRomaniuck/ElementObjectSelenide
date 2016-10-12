package gsmserver.Components;

import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.JSExecutor;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class User {

    public final SelenideElement
            login = $("[name*='username']"),
            password = $("[name*='password']"),
            passwordConfirm = $("[name*='password_confirm']"),
            firstName = $("[name*='firstName']"),
            email = $("[name*='email']"),
            lastName = $("[name*='lastName']"),
            inputCity = $("#city"),
            middleName = $("[name*='middleName']"),
            customCountry = $("#country_id_chosen"),
            customRegion = $("#state_id_chosen"),
            customCity = $("#city_id_chosen"),
            address = $("[name*='[address]']"), //because of element have name = "address[address]"
            phoneCountries = $("a.styled-phone-dropdown-button"),
            phoneNumber = $("div.styled-phone-edit > input"),
            birthday = $("#birthday"),
            zip = $("[name*='zip'"),
            taxId = $("[name*='taxId']");

    @Step
    public User fillLogin(final String value){
        this.login.sendKeys(value);
        return this;
    }

    @Step
    public User fillPassword(final String value){
        this.password.sendKeys(value);
        return this;
    }

    @Step
    public User fillFirstName(final String value){
        this.firstName.setValue(value);
        return this;
    }

    @Step
    public User fillLastName(final String value) {
        this.lastName.setValue(value);
        return this;
    }

    @Step
    public User fillMiddleName(final String value) {
        this.middleName.setValue(value);
        return this;
    }

    @Step
    public User fillEmail(final String value) {
        this.email.setValue(value);
        return this;
    }

    @Step
    public User fillAddress(final String value) {
        this.address.setValue(value);
        return this;
    }


    @Step
    public User fillPhoneByCountry(final String country, final String phone){
        this.phoneCountries.click();
        $(String.format("b[data-hint='%s']", country)).scrollTo().click();
        this.phoneNumber.setValue(phone);
        return this;
    }

    @Step
    public User chooseCountry(final String value){
        $("h1").scrollTo();
        this.customCountry.click();
        this.selectItem(value);
        return this;
    }

    @Step
    public User chooseRegion(final String value){
        $("h1").scrollTo();
        this.customRegion.click();
        this.selectItem(value);
        return this;
    }

    @Step
    public User chooseCity(final String value){
        $("h1").scrollTo();
        this.customCity.click();
        this.selectItem(value);
        return this;
    }

    @Step
    public User fillCity(final String value){
        this.inputCity.setValue(value);
        return this;
    }

    private void selectItem(String nameOfItem){
        $(byXpath(String.format("//li//span[.='%s']", nameOfItem))).scrollTo().click();
    }

    @Step
    public void loginUserViaJS(final String login, final String password){
        new JSExecutor().POSTWithParams("/user/login/", String.format("{'login[username]':'%s', 'login[password]':'%s'}", login, password));
    }

    @Step
    public void setCountryUSAForUserViaJS(){
        new JSExecutor().POSTWithParams("/account/data/", "{'account[countryId]':'840', 'account[stateId]':'-1'}");
    }

    @Step
    public void setCountrySpainForUserViaJS(){
        new JSExecutor().POSTWithParams("/account/data/", "{'account[countryId]':'724', 'account[stateId]':'-1'}");
    }

}
