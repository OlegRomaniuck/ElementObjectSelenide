package gsmserver.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import gsmserver.Utils.BaseTest;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gsmserver.Components.Product.addToCartButtons;
import static gsmserver.Components.Product.inputQuantity;

public final class HomePage extends BaseTest{

    private final SelenideElement mainBanner = $("#sm_slider > ul.sm_slider-inner");

    public HomePage(){
        $(byName("q")).shouldBe(Condition.visible);
    }

    @Step
    public static HomePage openHomePage(){
        open(baseUrl);
        return new HomePage();
    }

    public HomePage bannersShouldBeVisible(){
        this.mainBanner.shouldBe(Condition.visible);
        $("div.side-banner.left-banner").shouldBe(Condition.visible);
        $("div.side-banner.right-banner").shouldBe(Condition.visible);
        return this;
    }

    @Step
    public void infoBlockHaveImages(){
        $(".info-blocks").$$("img").forEach(SelenideElement::isImage);
    }

    @Step
    public HomePage addToCartFirstProduct(){
        addToCartButtons.get(0).click();
        inputQuantity.shouldBe(Condition.visible);
        return this;
    }

    @Step
    public void cartIconHaveCount(){
        $(".cart-indicator a.cart-icon").shouldHave(Condition.attribute("data-cart-quantity", "1"));
    }

}
