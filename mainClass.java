import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class mainClass {
    public static void main(String[] args) {
        // Here we initialize Api Context
        ApiContextInitializer.init();

        // Here we instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Here we register our bot
        try {
            botsApi.registerBot(new aviv_edu_bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
