import com.example.automationFramework.base.BasePage;
import com.example.automationFramework.pages.HTMLTable;
import com.example.automationFramework.pages.HTMLTable.TableRow;
import com.example.automationFramework.selenium.FindBy;
import com.example.automationFramework.selenium.RefreshingComponent;
import com.example.automationFramework.utils.Config;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserManagementPage extends BasePage {

    private static final String URL = "https://example.com/api";
    private final Config config;

    //enum declaration
    public enum UserActivationStatus {
        ACTIVE,
        ARCHIVED,
        EXPIRED,
        INVITED;
    }

    @FindBy (dataTestId = "chip")
    RefreshingComponent userActivationStatusChip;
    
    @FindBy (dataTestId = "fullAccess-switcher-input")
    RefreshingComponent = accountInviteToggle

    @FindBy(dataTestId = "CreateUserButton")
    WebElement createNewUserButton;

    @FindBy(dataTestId = "submit-user-creation-form")
    RefreshingComponent submitUserCreationButton;

    @FindBy(dataTestId = "create-another-user-input")
    RefreshingComponent selectCreateAnotherUser;

    @FindBy(id = "firstName")
    WebElement addFirstName;

    @FindBy(id = "lastName")
    WebElement addLastName;

    @FindBy(dataTestId = "email-field")
    RefreshingComponent addEmail;

    @FindBy(dataTestId = "searchBarInput")
    RefreshingComponent searchBar;

    @FindBy(dataTestId = "cell_firstName")
    RefreshingComponent userFirstNameFromUserManagementTableData;

    @FindBy(dataTestId = "cell_lastName")
    RefreshingComponent userLastNameFromUserManagementTableData;

    public UserManagementPage(WebDriver driver, Config config) {
        super(driver);
        this.config = config;
    }

    public void goTo(Config config) {
        driver.get(config.getToastwebUrl() + URL);
        waitForPageToLoad();
    }

    public void goToCreateNewUser() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(createNewUserButton));
        createNewUserButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(submitUserCreationButton));
    }

    public void submitUserCreation() {
        submitUserCreationButton.click();
    }

    public void createAnotherUser() {
        selectCreateAnotherUser.click();
    }

    public void editFirstName(String firstName) {
        this.addFirstName.clear();
        this.addFirstName.sendKeys(firstName);
    }

    public void editLastName(String lastName) {
        this.addLastName.clear();
        this.addLastName.sendKeys(lastName);
    }

    public void editPasscode(String passcode) {
        this.addPasscode.clear();
        this.addPasscode.sendKeys(passcode);
    }

    public void editEmail(String email) {
        this.addEmail.clear();
        this.addEmail.sendKeys(email);
    }

    public void searchUser(String userInput){
        searchBar.clear();
        searchBar.sendKeys(userInput);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[@data-testid='cell_firstName']/parent::tr/following-sibling::tr")));
    }

    public String getUserFirstNameFromUserManagementTableData(){
        return userFirstNameFromUserManagementTableData.getText();
    }

    public String getUserLastNameFromUserManagementTableData(){
        return userLastNameFromUserManagementTableData.getText();
    }

    public void selectYesAccountInviteToggle(){
        accountInviteToggle.click();
    }

    public void assertUserActivationStatusChip(UserActivationStatus expectedStatus) {
        //get text of the chip
        String chipText = userActivationStatusChip.getText().trim();
        //map chip text to corresponding UserActivationStatus enum values
        UserActivationStatus actualStatus = mapChipTextToUserActivationStatus(chipText);
        //assert that the actual status matches the expected status
        Assertions.assertEquals(expectedStatus, actualStatus);
    }

    // Helper function for mapping the chip text to the corresponding UserActivationStatus enum value
    private UserActivationStatus mapChipTextToUserActivationStatus(String chipText) {
        switch (chipText) {
            //user has completed activation
            case "Active":
                return UserActivationStatus.ACTIVE;
            //user has been archived
            case "Archived":
                return UserActivationStatus.ARCHIVED;
            //user has been invited to create account, but invite has expired
            case "Expired":
                return UserActivationStatus.EXPIRED;
            //user has been invited to create account, but has not yet completed activation
            case "Invited":
                return UserActivationStatus.INVITED;
            default:
                throw new IllegalArgumentException("Invalid chip text: " + chipText);
        }

    }
}
