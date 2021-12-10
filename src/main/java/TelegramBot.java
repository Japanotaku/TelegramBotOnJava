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
                } else if ("�������".equals(command)) {
                    if (!BucketImpl.pills.isEmpty()) {
                        message.setText(BucketImpl.pills.toString());
                    }
                    setButtons(message);
                } else if ("�������".equals(command)) {
                    message.setText(Wallet.sum + "");
                    setButtons(message);
                } else if ("��������".equals(command)) {
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
                        sendMessage.setText("����������������� ������� ������������ ����� ����� ������ ���������� ��������� ��� ����� ��������������� ������� ������������ �����, ��������������� � ���� ��� ��������� �����������, ����������� � ������� ����, ����� ����������� � ������, ��������� ����� � ���������� �������.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                if (update.getCallbackQuery().getData().equals("22")) {
                    if (Wallet.sum > 200) {
                        Wallet.sum -= 200;
                        BucketImpl.pills.add(new Pills(PillsType.Analgin, 200));
                        sendMessage.setText("�� ���������� ���������: ����� ��� ����� � �������� ��� ������� �������� ��������������� �������. ����� ��������� � ������, ����������� � ����. ������������� ������������ �/100 � ������������: ���� � 1,4; ������� ���� � 5; ������ � 14,4; ��������� � 2; ������ � ���������; ���������� ���� � ������ ���������; ������ � �����������.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                if (update.getCallbackQuery().getData().equals("33")) {
                    if (Wallet.sum > 300) {
                        Wallet.sum -= 300;
                        BucketImpl.pills.add(new Pills(PillsType.Paratecemol, 300));
                        sendMessage.setText("�������� � ������� � ������������ � ������������ ��������� � ���������� ������ ������ ���� (������������ ������������), ���������� ���� ������������ ��� ��������� ���������� �����. ����� �������� � �������, � ������������ ����, � ��������� ������������ ����� � ������ �����, ������������ ���, �� ���� ������� �����..");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }

                }
                if (update.getCallbackQuery().getData().equals("44")) {
                    if (Wallet.sum > 400) {
                        Wallet.sum -= 400;
                        BucketImpl.pills.add(new Pills(PillsType.Arthrocol, 400));
                        sendMessage.setText("������� ����, ���������� ��������� ���������������, ��������������������� � �������������� ��������. ��� ���������� ���������� ���������� ������ ��������������� � ������������, ������� ���������� ��� � �������� � ��������������, ����� ���������� ������ ����������� � ������������� ������������� ��������.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                if (update.getCallbackQuery().getData().equals("55")) {
                    if (Wallet.sum > 500) {
                        Wallet.sum -= 500;
                        BucketImpl.pills.add(new Pills(PillsType.Bainwel, 500));
                        sendMessage.setText("���� ��������� �����������, ���������������������, ������� ������������ � ��������� ��������������� ��������. ��� �������� � ���� ���������� �������������� ������� ��������� ���� � ��������� ������, ��������� ������������� ������, �������� �������� ����, �������� �������������� ���� � ��������� ������.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                if (update.getCallbackQuery().getData().equals("66")) {
                    if (Wallet.sum > 600) {
                        Wallet.sum -= 600;
                        BucketImpl.pills.add(new Pills(PillsType.Bam_Benge, 600));
                        sendMessage.setText("���-����� ������������� �������� � ������������� ������ � ��������. �������� ��������� ���������������������, �������������� � ���������� �������������� ��������. ������� �������� �� ������ ����. �������� ���������� ������� ������������� � ����� ���������.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                if (update.getCallbackQuery().getData().equals("77")) {
                    if (Wallet.sum > 700) {
                        Wallet.sum -= 700;
                        BucketImpl.pills.add(new Pills(PillsType.Valerian, 700));
                        sendMessage.setText("��������� ������������ ������� �� ������������������ ������, ���������� � �������������� ������, ������ ����� ������������ ��������, ������ � ������������ ������ �������, �������� ���������. ����� ���� �������� ������� ���������� ������� �����, � ��� ����� ����� 50 % ������� � 4-9 % ������ �������.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                if (update.getCallbackQuery().getData().equals("88")) {
                    if (Wallet.sum > 800) {
                        Wallet.sum -= 800;
                        BucketImpl.pills.add(new Pills(PillsType.Corvalol, 800));
                        sendMessage.setText("��������� ������������ ���, �������� ���������� ��������������, ��������� �������������� � �������� ����������� �������� ���������-��������� ������, ��������� ���������������� � ������������� ��������. � ������� ��������� �� ������ ������� ������������ ���� �������� ��� ������ ������� ������.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                if (update.getCallbackQuery().getData().equals("99")) {
                    if (Wallet.sum > 900) {
                        Wallet.sum -= 900;
                        BucketImpl.pills.add(new Pills(PillsType.Validol, 900));
                        sendMessage.setText("�������� ��������� ������������� �������� �� ����������� ������� �������, � ����� ��������� ��������� ������������ ����������������� (������������������) ������� ����� ������������� ����������� �������������� ������� (�����������) ���������� ��������� �������� ������� ���.");
                    } else {
                        sendMessage.setText("� ��� �� ������� �����");
                    }
                }
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }

    public synchronized void setButtons(SendMessage sendMessage) throws TelegramApiException {
        // ������� �����������
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // ������� ������ ����� ����������
        List<KeyboardRow> keyboard = new ArrayList<>();

        // ������ ������� ����������
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // ��������� ������ � ������ ������� ����������
        keyboardFirstRow.add(new KeyboardButton("�������"));
        // ������ ������� ����������
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // ��������� ������ �� ������ ������� ����������
        keyboardSecondRow.add(new KeyboardButton("�������"));
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // ��������� ������ �� ������ ������� ����������
        keyboardThirdRow.add(new KeyboardButton("��������"));

        // ��������� ��� ������� ���������� � ������
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // � ������������� ���� ������ ����� ����������
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