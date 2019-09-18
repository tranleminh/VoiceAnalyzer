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
    public static final String TEXT_TO_READ = "So what do u want to read here ?";
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
