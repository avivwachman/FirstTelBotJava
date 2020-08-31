import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class aviv_edu_bot extends TelegramLongPollingBot {

  @Override
  public void onUpdateReceived(Update update) {

    // Here We check if update has a message and if the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {

      // Here we set the variables
      String message_text = update.getMessage().getText(); //copy the message received
      long chat_id = update.getMessage().getChatId();

      Authenticator authenticator = new IamAuthenticator("apiKey");
      LanguageTranslator service = new LanguageTranslator("version-date", authenticator);
      service.setServiceUrl("hereGoes/TheUrl.com");
      TranslateOptions translateOptions = new TranslateOptions.Builder()
              .addText(message_text)
              .source(Language.ENGLISH)
              .target(Language.HEBREW)
              .build();
      TranslationResult translationResult = service.translate(translateOptions).execute().getResult();
      message_text = "*" + translationResult.getTranslations().get(0).getTranslation() + "*";

      SendMessage message =
          new SendMessage(chat_id, message_text) // Here we create a message object
              .setParseMode(ParseMode.MARKDOWN);//set the parseMode so bold text will work

      try {
        execute(message); // Here we send our message object to the user
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String getBotUsername() {
    return "edu_aviv_bot";
  }

  @Override
  public String getBotToken() {
    // Here we return the bot token given from BotFather
    return "botTokenHere";
  }
}
