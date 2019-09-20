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
    public static final String TEXT_TO_READ = "Allégorie" +
            "C'est une femme belle et de riche encolure,\n" +
            "Qui laisse dans son vin traîner sa chevelure.\n" +
            "Les griffes de l'amour, les poisons du tripot,\n" +
            "Tout glisse et tout s'émousse au granit de sa peau.\n" +
            "Elle rit à la mort et nargue la Débauche,\n" +
            "Ces monstres dont la main, qui toujours gratte et fauche,\n" +
            "Dans ses jeux destructeurs a pourtant respecté\n" +
            "De ce corps ferme et droit la rude majesté.\n" +
            "Elle marche en déesse et repose en sultane ;\n" +
            "Elle a dans le plaisir la foi mahométane,\n" +
            "Et dans ses bras ouverts, que remplissent ses seins,\n" +
            "Elle appelle des yeux la race des humains.\n" +
            "Elle croit, elle sait, cette vierge inféconde\n" +
            "Et pourtant nécessaire à la marche du monde,\n" +
            "Que la beauté du corps est un sublime don\n" +
            "Qui de toute infamie arrache le pardon.\n" +
            "Elle ignore l'Enfer comme le Purgatoire,\n" +
            "Et quand l'heure viendra d'entrer dans la Nuit noire,\n" +
            "Elle regardera la face de la Mort,\n" +
            "Ainsi qu'un nouveau-né, - sans haine et sans remord.\n" +
            "Charles Baudelaire. ";
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
