package com.functionality.voiceanalyzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ParametersCollection {

    public static final String CHANNEL_ID = "VoiceAnalyzerNotificationChannel";

    public static final int REQUEST_RECORD_AUDIO_PERMISSION = 201;

    public static final String sharedPrefFile = "VoiceAnalyzerPreferences";
    public static final String TOOLTIPS_TITLE = "Welcome to Voice Analyzer";
    public static final String TOOLTIPS_MESSAGE = "TODO ADD MESSAGE";
    public static final String TEXT_TO_READ = "Le Corbeau et le Renard" +
            "Maître Corbeau, sur un arbre perché,\n"+
            "Tenait en son bec un fromage.\n"+
            "Maître Renard, par l'odeur alléché,\n"+
            "Lui tint à peu près ce langage :\n"+
            "Et bonjour, Monsieur du Corbeau,\n"+
            "Que vous êtes joli ! que vous me semblez beau !\n"+
            "Sans mentir, si votre ramage\n"+
            "Se rapporte à votre plumage,\n"+
            "Vous êtes le Phénix des hôtes de ces bois.\n"+
            "À ces mots le Corbeau ne se sent pas de joie,\n"+
            "Et pour montrer sa belle voix,\n"+
            "Il ouvre un large bec, laisse tomber sa proie.\n"+
            "Le Renard s'en saisit, et dit : Mon bon Monsieur,\n"+
            "Apprenez que tout flatteur\n"+
            "Vit aux dépens de celui qui l'écoute.\n"+
            "Cette leçon vaut bien un fromage sans doute.\n"+
            "Le Corbeau honteux et confus\n"+
            "Jura, mais un peu tard, qu'on ne l'y prendrait plus.\n"+
                "Jean de La Fontaine";
    public static final String TITLE_READ = "It's time to read !";
    public static String FILE_NAME = "VoiceAnalyzer";
    public static final String INFO_LEGAL = "TODO ADD TERM OF USE";
    public static final String TITLE_LEGAL = "Term of Use";

    public static final SimpleDateFormat dfYear = new SimpleDateFormat("YYYY");
    public static final SimpleDateFormat dfMonth = new SimpleDateFormat("MMM");
    public static final SimpleDateFormat dfDay = new SimpleDateFormat("d");
    public static final SimpleDateFormat dfHour = new SimpleDateFormat("hh-mm-ss");

    //public static final String DEFAULT_NOTIFY_TIME = "19:30";
    //public static final Calendar NOTIFY_DATE = Calendar.getInstance();
    public static final DateFormat dayTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
}
