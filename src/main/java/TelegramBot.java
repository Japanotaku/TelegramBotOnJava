import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

            String command = message.getText();
            String src = message.getChatId();
            String blogUrl = "https://kurs.kz/";

//            try {
//                 Document doc = Jsoup.connect(blogUrl).get();
//                 System.out.println(doc);
//                 Element firstArticle = doc.select("td").first();
//                 System.out.println(firstArticle);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                // Call method to send the message
                switch (command) {
                    case "/start" -> message.setText("Hello,choose rate)");
                    case "USD" -> message.setText("1 \"USD\" to " + getURl(new URL("https://v6.exchangerate-api.com/v6/5c2f03797324ecd7abc9af55/latest/USD")));
                    case "EUR" -> message.setText("1 \"EUR\" to " + getURl(new URL("https://v6.exchangerate-api.com/v6/5c2f03797324ecd7abc9af55/latest/EUR")));
                    case "RUB" -> message.setText("1 \"RUB\" to " + getURl(new URL("https://v6.exchangerate-api.com/v6/5c2f03797324ecd7abc9af55/latest/RUB")));
                    default -> message.setText("I do not know what you want(");
                }
               setButtons(message);
            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }
        }


    }
    public String getURl(URL url) throws IOException {
        InputStream input = url.openStream();
        byte[] buffer = input.readAllBytes();
        String str = new String(buffer);
        int last = str.lastIndexOf("\"KZT");
        int first = last + 12;
        String WhatWeNeed = str.substring(last,first);
        WhatWeNeed = removeLastChar(WhatWeNeed);
        return WhatWeNeed;
    }

    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }
    public synchronized void setButtons(SendMessage sendMessage) throws TelegramApiException {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("USD"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("EUR"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardThirdRow.add(new KeyboardButton("RUB"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        execute(sendMessage);
    }
    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "Dore_bot";
    }

    @Override
    public String getBotToken() {
        return "2044791768:AAH7WGbd_2jVVIVQS888LS_D-Z3JwekGaTE";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }


}