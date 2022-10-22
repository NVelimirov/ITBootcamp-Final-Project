package helpers;

public enum Page_TitlesTextsAndMessages {
    LOG_IN_PAGE_TITLE("Account Login"),
    LOG_OUT_PAGE_TITLE("Account Logout"),
    WISHLIST_PAGE_TITLE("My Wish List"),
    ACCOUNT_PAGE_TITLE("My Account"),
    HOME_PAGE_TEXT("Prodavnica drustvenih igara"),
    LOG_IN_WARNING("Warning: No match for E-Mail Address and/or Password."),
    SEARCH_PAGE_TEXT("Search Criteria"),
    LOG_IN_WARNING_NUMBER_OF_ATTEMPTS("Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour."),
    HOME_PAGE_LANGUAGE_SERBIAN("SRPSKI"),
    HOME_PAGE_LANGUAGE_ENGLISH("ENGLISH"),
    FACEBOOK_PAGE_TITLE("Drustvene igre Prodaja Prodavnica Klub drustvenih igara"),
    EXCEL_TABLE_PATH("src\\utils\\ListOfInvalidUserCredentials.xlsx");
    private String absolutePath;
    private Page_TitlesTextsAndMessages(String absolutePath) {
        this.absolutePath = absolutePath;
    }
    public String getAbsolutePath() {
        return this.absolutePath;
    }
}
