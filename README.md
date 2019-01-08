# Recorder_ImproveSkill
# LEVEL 4 [![Build Status](https://travis-ci.org/nomensa/jquery.hide-show.svg)](https://travis-ci.org/nomensa/jquery.hide-show.svg?branch=master)

   ```You just do.```
  
## Content:
- [x] Step by step to record file audio from Android device.
> - Create a variable to handle true/false when users double-click
```java
mStartRecording = !mStartRecording;
```
> - Establish UI with file .xml to control 
```xml
<com.melnykov.fab.FloatingActionButton
        android:id="@+id/btnRecord"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:fab_colorNormal="@color/primary"
        android:layout_marginBottom="10dp"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:src="@drawable/ic_mic_white_36dp" />
```

> - Establish control relates to widget in file .xml
```java
mRecordButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (Utils.isDoubleClick()) {
            return;
        }
        if (Utils.checkPermission(mHomeActivity)) {
            onRecord(mStartRecording);
            mStartRecording = !mStartRecording;
        } else {
            Utils.settingPermission(mRecordFragment);
        }
    }
});
```

> - Here, we need to analyze two problems
>> - Problem 1: We need to create thread running without mainThread to record audio from users by Android device. Anyways, there are two ways. 
>>> - Basically, it's very simple: We need to store file Audio, so about UI. It requests get Output file before starting record. Look like we are using FormatFactory.
```java
String outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
```

>> - Problem 2: 
