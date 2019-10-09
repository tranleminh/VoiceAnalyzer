package com.functionality.voiceanalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.RECORD_AUDIO;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AUDIO_RECORD";
    private SharedPreferences mPreferences;
    private String id = "NOT_INITIALIZED";
    private EditText ID;
    private Button btnRecord;
    private Button btnShowText;
    private Button btnInfo;
    private Chronometer CLK;
    private MediaRecorder recorder = null;
    private String currentFilename;
    private Date currentDate;
    private File audiofile;
    private StorageReference mStorageRef;
    private boolean isRecording = false;
    private boolean isNotificationEnabled = false;
    private Intent notifyIntent;
    private boolean isFirstTime = true;

    /**
     * Check if user already gives permission to access to location to the application
     * @return true if permission granted, false otherwise
     */
    private boolean checkAudioRecordPermission() {
        int result = ContextCompat.checkSelfPermission(this, RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Overidden method that ask user for permission
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ParametersCollection.REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length > 0) {
                boolean recordAudioPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (recordAudioPermission)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void displayTooltips() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(ParametersCollection.TOOLTIPS_TITLE);
        builder.setMessage(ParametersCollection.TOOLTIPS_MESSAGE);
        builder.show();
    }

    private void displayText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(ParametersCollection.TITLE_READ);
        builder.setMessage(ParametersCollection.TEXT_TO_READ);
        builder.show();
    }

    /**
     * A method that displays a dialog containing term of use
     */
    private void displayTermOfUse() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(ParametersCollection.TITLE_LEGAL);
        builder.setMessage(ParametersCollection.INFO_LEGAL);
        builder.show();
    }

    private void startRecording() {
        currentDate = Calendar.getInstance().getTime();
        String hour = ParametersCollection.dfHour.format(currentDate);
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        currentFilename = ParametersCollection.FILE_NAME + "_" + hour;
        try {
            audiofile = File.createTempFile(currentFilename, ".amr", getApplicationContext().getFilesDir());
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.setOutputFile(audiofile.getAbsolutePath());

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording(String username) {
        recorder.stop();
        recorder.release();
        recorder = null;
        String year = ParametersCollection.dfYear.format(currentDate);
        String month = ParametersCollection.dfMonth.format(currentDate);
        String day = ParametersCollection.dfDay.format(currentDate);

        Uri file = Uri.fromFile(audiofile);
        StorageReference fileRef = mStorageRef.child(username + "/" + year + "/" + month + "/" + day + "/" + currentFilename);
        Toast.makeText(getApplicationContext(), "URI : " + file, Toast.LENGTH_LONG).show();

        /***The putFile() method used for file uploading***/
        fileRef.putFile(file)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return mStorageRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Upload complete!" , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Upload failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***Check whether this app has access to the location permission***/
        if (!checkAudioRecordPermission()) {
            ActivityCompat.requestPermissions(this,
                    new String[]{RECORD_AUDIO}, ParametersCollection.REQUEST_RECORD_AUDIO_PERMISSION);
        }

        /***Instantiate the Firebase Cloud Storage***/
        mStorageRef = FirebaseStorage.getInstance().getReference();

        /**Get the shared preferences file by name, declared on ParametersCollection.java, then get saved user ID from it**/
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Context directBootContext = this.createDeviceProtectedStorageContext();
            mPreferences = directBootContext.getSharedPreferences(ParametersCollection.sharedPrefFile, MODE_PRIVATE);
        }
        else {
            mPreferences = getSharedPreferences(ParametersCollection.sharedPrefFile, MODE_PRIVATE);
        }
        id = mPreferences.getString("ID", id);
        isNotificationEnabled = mPreferences.getBoolean("Notification", isNotificationEnabled);
        isFirstTime = mPreferences.getBoolean("FirstTime", isFirstTime);

        /**UI instantiated here**/
        ID = findViewById(R.id.id);
        btnRecord = findViewById(R.id.btn_record);
        btnShowText = findViewById(R.id.btn_showtext);
        btnInfo = findViewById(R.id.btn_info);
        CLK = findViewById(R.id.clk);


        if (!id.equals("NOT_INITIALIZED"))
            ID.setText(id);

        /**Instantiate intent to launch service**/
        notifyIntent = new Intent(this, NotificationService.class);

        /**Display tooltips before anything else**/
        if (isFirstTime) {
            displayTooltips();
            isFirstTime = false;
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putBoolean("FirstTime", isFirstTime);
            preferencesEditor.apply();
        }

        /***A text changed listener with afterTextChanged() method implemented in order to automatically save ID right after the ID field is modified***/
        ID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                id = ID.getText().toString();
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString("ID",id);
                preferencesEditor.apply();
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNotificationEnabled) {
                    isNotificationEnabled = true;
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    preferencesEditor.putBoolean("Notification",isNotificationEnabled);
                    preferencesEditor.apply();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(notifyIntent);
                    } else {
                        startService(notifyIntent);
                    }
                }
                if (!isRecording) {
                    isRecording = true;
                    startRecording();
                    displayText();
                    btnRecord.setText("Stop Recording");
                    btnShowText.setVisibility(View.VISIBLE);
                    CLK.setBase(SystemClock.elapsedRealtime());
                    CLK.setVisibility(View.VISIBLE);
                    CLK.start();
                }
                else {
                    isRecording = false;
                    stopRecording(id);
                    CLK.stop();
                    btnShowText.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayText();
            }
        });

        /***Term of Use button implemented here***/
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayTermOfUse();
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        try {
//            Intent intent = new Intent(MainActivity.this, NotificationReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            Date notifyDate = null;
//            notifyDate = ParametersCollection.dayTime.parse(ParametersCollection.DEFAULT_NOTIFY_TIME);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notifyDate.getTime(), AlarmManager.INTERVAL_DAY, pendingIntent);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }
}
