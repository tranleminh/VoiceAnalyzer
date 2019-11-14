package com.functionality.voiceanalyzer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ParametersCollection {

    public static final String CHANNEL_ID = "VoiceAnalyzerNotificationChannel";

    public static final int REQUEST_RECORD_AUDIO_PERMISSION = 201;

    public static final String sharedPrefFile = "VoiceAnalyzerPreferences";
    public static final String TOOLTIPS_TITLE = "Bienvenue dans l'application d'analyse vocale !";
    public static final String TOOLTIPS_MESSAGE = "TODO ADD MESSAGE";
    public static final String TEXT_TO_READ = "Le Corbeau et le Renard\n" +
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
    public static final String TITLE_READ = "C'est l'heure de la lecture !";
    public static String FILE_NAME = "VoiceAnalyzer";
    public static final String INFO_LEGAL = "Capteurs sensori-moteurs sur smartphone et vie quotidienne\n"+
            "Thèse en informatique\n" +
            "Doctorante : Marion Kissous\n" +
            "Réalisée sous la direction de Gérard Dray, Sophie Martin, Anne-Lise Courbis et Thomas Lambolais\n"+
            "\n" +
            "Objectif de l’étude \n" +
            "\n" +
            "Ce projet de recherche s’inscrit a pour objectif est d’explorer si les capteurs sensori-moteurs intégrés dans les " +
            "smartphones permettent de déceler des changements durant la vie quotidienne. Nous nous intéressons plus " +
            "particulièrement aux données du type géo-localisation, analyse vocale et variation du rythme de la marche. " +
            "Notre hypothèse est que l’ensemble de ces indicateurs permettront de refléter des modifications comportementales " +
            "inhérentes aux situations quotidiennes qui sont couteuses en ressources personnelles. L’étude à laquelle nous vous " +
            "proposons de participer a pour objectif de tester la fonctionnalité d’une version prototype de l’application qui " +
            "sera ultérieurement développée.\n"+
            "\n"+
            "Si vous acceptez de participer\n" +
            "\n" +
            "- Vous devrez remplir un questionnaire en début d’étude (essentiellement à visée de récolte de données " +
            "sociodémographiques) \n" +
            "- Vous devrez alors remplir un questionnaire mensuel visant 1) à évaluer des dimensions du quotidiens et 2) à " +
            "repérer si des évènements mineurs ou majeurs ont eu lieu durant le mois passé. Vous aurez aussi à lire de courts " +
            "textes.\n" +
            "- Enfin, nous pourrons être amenés à nous entretenir avec vous au cours de l’étude dans le but de comprendre au " +
            "mieux certaines données relevées.\n" +
            "- SI vous vous engagez à participer, nous comptons sur votre aide et votre bienveillance pour réaliser l’ensemble " +
            "des étapes proposées.\n"+
            "\n" +
            "La protection de vos données est notre priorité\n"+
            "\n"+
            "Votre participation à cette étude est volontaire. Vous pourrez cesser d’y participer à tout moment, sans aucune " +
            "incidence pour vous. En revanche, en cas de retrait, l’ensemble de vos données deviendraient inexploitables pour " +
            "notre recherche. \n"+
            "Conformément aux dispositions de la loi Informatique et Libertés, les participants disposent du droit à demander " +
            "la suppression de leurs données. Si tel était le cas, il vous suffit de contacter la doctorante Marion Kissous " +
            ": marion.kissous@mines-ales.fr\n"+
            "Les données recueillies seront stockées sur un serveur sécurisé de IMT Mines Alès avec une clé qui vous sera " +
            "remise lors de la distribution de l'application et du lancement de l'étude." +
            "Il ne sera donc pas possible à l'équipe de recherche de vous identifier nominativement.\n"+
            "Le stockage et la sauvegarde de ces données se fera sur les serveurs sécurisés des laboratoires engagés dans " +
            "cette recherche. Seuls la doctorante et son équipe encadrante auront un accès direct aux données. " +
            "Vous pourrez nous solliciter si vous souhaitez connaître les résultats de cette étude et vous aider à les " +
            "comprendre.\n"+
            "Marion Kissous se tiendra à votre disposition si vous avez des questions concernant le sujet de recherche et de " +
            "ses débouchés tout au long de l’étude. Les résultats finaux de cette recherche seront diffusés au cours de " +
            "différentes communications telles que des congrès, soutenance de thèse, publications, conférences ou encore " +
            "colloques.\n" +
            "\n" +
            "CONSENTEMENT ÉCLAIRÉ\n" +
            "\n" +
            "J’accepte de mon plein gré de participer à cette étude intitulée « Capteurs sensori-moteurs sur smartphone & " +
            "vie quotidienne », réalisée par Marion Kissous, Doctorante en 2nde année au LGI2P de l’IMT Mines Alès et du " +
            "Laboratoire de psychologie EPSYLON de l’université de Montpellier. Les raisons de cette étude m’ont été exposées " +
            "et les conditions de sa réalisation m’ont été clairement indiquées. Je connais la possibilité qui m’est réservée " +
            "à tout moment d’interrompre ma participation sans en fournir la raison et sans que cela ne me porte préjudice. " +
            "J’ai également été informé(e) que je peux en conséquence demander la suppression de mes données.\n" +
            "J’ai été avisé(e) que je ne recevrai aucune indemnité financière pour ma participation et que les données de " +
            "cette étude préserveront totalement mon anonymat, la confidentialité, et le secret professionnel pour toutes " +
            "informations me concernant.\n" +
            "En application de la loi « informatique et liberté » du 6 janvier 1978, modifiée par les lois n° 94-548 " +
            "du 1er juillet 1994, n° 2002-303 du 4 mars 2002, et 2004-801 du 6 août 2004, j’accepte que les données " +
            "enregistrées à l’occasion de cette étude, puissent faire l’objet d’un traitement informatique, l’anonymat et " +
            "la confidentialité restant totalement respectés.\n" +
            "\n" +
            "« J’accepte de participer à cette présente recherche ce qui présuppose d’utiliser les applications demandées " +
            "(StepAnalyzer, LocationTracker & VoiceAnalysis) ainsi que de répondre aux questionnaires pour lesquels vous " +
            "serez sollicités ».\n";

    public static final String TITLE_LEGAL = "Conditions de participation";

    public static final SimpleDateFormat dfYear = new SimpleDateFormat("YYYY");
    public static final SimpleDateFormat dfMonth = new SimpleDateFormat("MMM");
    public static final SimpleDateFormat dfDay = new SimpleDateFormat("d");
    public static final SimpleDateFormat dfHour = new SimpleDateFormat("hh-mm-ss");

    //public static final String DEFAULT_NOTIFY_TIME = "19:30";
    //public static final Calendar NOTIFY_DATE = Calendar.getInstance();
    public static final DateFormat dayTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
}
