import models.BucketImpl;
import models.Pills;
import models.PillsType;
import models.Wallet;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            SendPhoto messagePhoto = new SendPhoto();
            File file = new File(Paths.get("src/main/java/img/3540584.jpg").toAbsolutePath().toString());
            messagePhoto.setChatId(update.getMessage().getChatId().toString());
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());
            String command = message.getText();
            try {
                if ("/start".equals(command)) {
                    messagePhoto.setPhoto(new InputFile(file));
                    setInline(messagePhoto);
                } else if ("Корзина".equals(command)) {
                    if (!BucketImpl.pills.isEmpty()) {
                        message.setText(BucketImpl.pills.toString());
                    }
                    setButtons(message);
                } else if ("Кошелек".equals(command)) {
                    message.setText(Wallet.sum + "");
                    setButtons(message);
                } else if ("Таблетки".equals(command)) {
                    message.setText("Aspirin,Paratecemol,Analgin,Arthrocol,Bainwel,Bam_Benge,Corvalol,Valerian,Validol");
                    setButtons(message);
                } else {
                    message.setText("There is no command like that, please try again");
                    setButtons(message);
                }

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            try {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
                if (update.getCallbackQuery().getData().equals("1")) {
                    sendMessage.setText("Your throat is broken?   ");
                    setInlineSecondLevel(sendMessage);
                }
                if (update.getCallbackQuery().getData().equals("2")) {
                    sendMessage.setText("Your body is broken?");
                    setInlineSecondLevel(sendMessage);
                }
                if (update.getCallbackQuery().getData().equals("3")) {
                    sendMessage.setText("Your heart is broken?");
                    setInlineSecondLevel(sendMessage);
                }
                if (update.getCallbackQuery().getData().equals("11")) {
                    if (Wallet.sum > 100) {
                        Wallet.sum -= 100;
                        BucketImpl.pills.add(new Pills(PillsType.Aspirin, 100));
                        sendMessage.setText("Ацетилсалициловая кислота представляет собой белые мелкие игольчатые кристаллы или лёгкий кристаллический порошок слабокислого вкуса, малорастворимый в воде при комнатной температуре, растворимый в горячей воде, легко растворимый в спирте, растворах едких и углекислых щелочей.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                if (update.getCallbackQuery().getData().equals("22")) {
                    if (Wallet.sum > 200) {
                        Wallet.sum -= 200;
                        BucketImpl.pills.add(new Pills(PillsType.Analgin, 200));
                        sendMessage.setText("По физическим свойствам: белый или белый с кремовым или розовым оттенком кристаллический порошок. Легко растворим в спирте, нерастворим в воде. Растворимость парацетамола г/100 г растворителя: вода — 1,4; кипящая вода — 5; этанол — 14,4; хлороформ — 2; ацетон — растворим; диэтиловый эфир — слегка растворим; бензол — нерастворим.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                if (update.getCallbackQuery().getData().equals("33")) {
                    if (Wallet.sum > 300) {
                        Wallet.sum -= 300;
                        BucketImpl.pills.add(new Pills(PillsType.Paratecemol, 300));
                        sendMessage.setText("Вступает в реакцию с гидроперитом в измельчённом состоянии с выделением едкого белого дыма (гидросульфид метиламмония), вследствие чего используется как эффектный химический фокус. Также вступает в реакцию, в измельчённом виде, с раствором перманганата калия в кислой среде, обесцвечивая его, за счёт сульфит ионов..");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }

                }
                if (update.getCallbackQuery().getData().equals("44")) {
                    if (Wallet.sum > 400) {
                        Wallet.sum -= 400;
                        BucketImpl.pills.add(new Pills(PillsType.Arthrocol, 400));
                        sendMessage.setText("Являясь НПВП, кетопрофен оказывает анальгезирующее, противовоспалительное и жаропонижающее действие. При воспалении кетопрофен ингибирует синтез простагландинов и лейкотриенов, тормозя активность ЦОГ и частично — липооксигеназы, также ингибирует синтез брадикинина и стабилизирует лизосомальные мембраны.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                if (update.getCallbackQuery().getData().equals("55")) {
                    if (Wallet.sum > 500) {
                        Wallet.sum -= 500;
                        BucketImpl.pills.add(new Pills(PillsType.Bainwel, 500));
                        sendMessage.setText("Мазь оказывает отвлекающее, противовоспалительное, местное раздражающее и умеренное антисептическое действие. При втирании в кожу раздражает чувствительные нервные окончания кожи и подкожных тканей, расширяет поверхностные сосуды, вызывает ощущение жара, улучшает кровоснабжение кожи и подкожных тканей.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                if (update.getCallbackQuery().getData().equals("66")) {
                    if (Wallet.sum > 600) {
                        Wallet.sum -= 600;
                        BucketImpl.pills.add(new Pills(PillsType.Bam_Benge, 600));
                        sendMessage.setText("Бом-Бенге универсальный помощник в обезболивании тканей и суставов. Препарат проявляет противовоспалительное, обезболивающее и улучшающее кровообращение действие. Лечение занимает до десяти дней. Активные компоненты снимают болезненность в месте поражения.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                if (update.getCallbackQuery().getData().equals("77")) {
                    if (Wallet.sum > 700) {
                        Wallet.sum -= 700;
                        BucketImpl.pills.add(new Pills(PillsType.Valerian, 700));
                        sendMessage.setText("Уменьшает возбуждающие влияния на сосудодвигательные центры, коронарные и периферические сосуды, снижая общее артериальное давление, снимая и предупреждая спазмы сосудов, особенно сердечных. Масло мяты содержит большое количество эфирных масел, в том числе около 50 % ментола и 4-9 % эфиров ментола.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                if (update.getCallbackQuery().getData().equals("88")) {
                    if (Wallet.sum > 800) {
                        Wallet.sum -= 800;
                        BucketImpl.pills.add(new Pills(PillsType.Corvalol, 800));
                        sendMessage.setText("Уменьшает возбудимость ЦНС, улучшает коронарное кровообращение, усиливает желчеотделение и секрецию железистого аппарата желудочно-кишечного тракта, оказывает спазмолитическое и гипотензивное действие. О влиянии валерианы на высшую нервную деятельность было известно еще врачам Древней Греции.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                if (update.getCallbackQuery().getData().equals("99")) {
                    if (Wallet.sum > 900) {
                        Wallet.sum -= 900;
                        BucketImpl.pills.add(new Pills(PillsType.Validol, 900));
                        sendMessage.setText("Препарат проявляет успокаивающее действие на центральную нервную систему, а также оказывает умеренное рефлекторное сосудорасширяющее (коронаролитическое) влияние путем рефлекторного раздражения чувствительных нервных («холодовых») рецепторов слизистой оболочки полости рта.");
                    } else {
                        sendMessage.setText("У вас не хватает денег");
                    }
                }
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


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
        keyboardFirstRow.add(new KeyboardButton("Корзина"));
        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("Кошелек"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardThirdRow.add(new KeyboardButton("Таблетки"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        execute(sendMessage);
    }

    private void setInline(SendPhoto sendMessage) throws TelegramApiException {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> addButtons = new ArrayList<>();
        InlineKeyboardButton deepButton = new InlineKeyboardButton();
        InlineKeyboardButton deepButton2 = new InlineKeyboardButton();
        InlineKeyboardButton deepButton3 = new InlineKeyboardButton();
        deepButton.setText("Throat");
        deepButton2.setText("Body");
        deepButton3.setText("Heart");
        deepButton.setCallbackData("1");
        deepButton2.setCallbackData("2");
        deepButton3.setCallbackData("3");
        addButtons.add(deepButton);
        addButtons.add(deepButton2);
        addButtons.add(deepButton3);
        buttons.add(addButtons);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markupKeyboard);
        execute(sendMessage);
    }

    private void setInlineSecondLevel(SendMessage sendMessage) throws TelegramApiException {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> addButtons = new ArrayList<>();
        InlineKeyboardButton deepButton = new InlineKeyboardButton();
        InlineKeyboardButton deepButton2 = new InlineKeyboardButton();
        InlineKeyboardButton deepButton3 = new InlineKeyboardButton();
        if (sendMessage.getText().equals("Your throat is broken?   ")) {
            deepButton.setText("Aspirin");
            deepButton2.setText("Paracetamol");
            deepButton3.setText("Analgin");
            deepButton.setCallbackData("11");
            deepButton2.setCallbackData("22");
            deepButton3.setCallbackData("33");
        }
        if (sendMessage.getText().equals("Your body is broken?")) {
            deepButton.setText("Arthrocol");
            deepButton2.setText("Bainwel");
            deepButton3.setText("Bom-Benge");
            deepButton.setCallbackData("44");
            deepButton2.setCallbackData("55");
            deepButton3.setCallbackData("66");
        }

        if (sendMessage.getText().equals("Your heart is broken?")) {
            deepButton.setText("Corvalol");
            deepButton2.setText("Valerian");
            deepButton3.setText("Validol");
            deepButton.setCallbackData("77");
            deepButton2.setCallbackData("88");
            deepButton3.setCallbackData("99");
        }
        addButtons.add(deepButton);
        addButtons.add(deepButton2);
        addButtons.add(deepButton3);
        buttons.add(addButtons);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markupKeyboard);
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
        return "5052739120:AAFxFHtJPy94kPeNkYTTiqV4ITNhek4w_6E";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}