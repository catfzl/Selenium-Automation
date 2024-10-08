package example.userManagement;


import pages.UserManagementPage;
import utils.WebLogin;
import org.junit.jupiter.api.Assertions;
import config.Config;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestRail(project = TestRailsProject.TEAM_PROJECT)
public class CreateUserTest {

    private final Config config = Config.getInstance();

    @RegisterExtension WebDriverFactory webDriverFactory = new WebDriverFactory();
    @RegisterExtension TestEntityFactory testEntityFactory = new TestEntityFactory();

    private RemoteWebDriver webDriver;
    private TestEntityInfo testEntityInfo;

    @BeforeEach
    public void setup() throws ExecutionException, InterruptedException {
        webDriver = webDriverFactory.createRemoteWebDriver();
        testEntityInfo = testEntityFactory.getBaseEntity().addTestUser(User.TEST_USER).getTestEntityInfo();
    }

    static class createUserInvite{
        private static final String firstNameTestUser = System.getenv("FIRST_NAME_USER","firstNamePlaceholder");
        private static final String lastNameTestUser = System.getenv("LAST_NAME_USER","lastNamePlaceholder");
        private static final String emailTestUser = System.getenv("USER_EMAIL","testuser@example.com");
    }

    @ProofTest
    @DisplayName("Create User - Invite to Activate")
    @TestRail(id = {caseID})
    public void testCreateUser() {

        //login as test user, navigate to user management SPA
        WebLogin.login(webDriver, testEntityInfo, User.TEST_USER);
        UserManagementPage userManagementPage = new UserManagementPage(webDriver, config);
        userManagementPage.goTo(config);

        //create new user
        userManagementPage.goToCreateNewUser();

        //select "yes" on invite to create account toggle
        userManagementPage.selectYesAccountInviteToggle();

        //fill out first name, last name, email fields
        userManagementPage.editFirstName(createUserInvite.firstNameUser);
        userManagementPage.editLastName(createUserInvite.lastNameUser);
        userManagementPage.editEmail(createUserInvite.emailUser);

        //select Add another checkbox to add another user
        userManagementPage.createAnotherUser();

        //Select user creation button to complete user creation
        userManagementPage.submitUserCreation();

        //search newly created user by email, verify first and last name and account status are as expected
        userManagementPage.searchUser(createUserInvite.emailUser);
        Assertions.assertEquals(userManagementPage.getUserFirstNameFromUserManagementTableData(), createUserInvite.firstNameTestUser);
        Assertions.assertEquals(userManagementPage.getUserLastNameFromUserManagementsTableData(), createUserInvite.lastNameTestUser);

        //the expected status is Invited
        UserManagement.UserActivationStatus expectedStatus = UserManagementPage.UserActivationStatus.INVITED;

        //Assert expected status and actual status match
        userManagementPage.assertUserActivationStatusChip(expectedStatus);
    }

}
