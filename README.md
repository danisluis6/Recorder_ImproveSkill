# Recorder_ImproveSkill
# LEVEL 1 [![Build Status](https://travis-ci.org/nomensa/jquery.hide-show.svg)](https://travis-ci.org/nomensa/jquery.hide-show.svg?branch=master)

   ```You just do.```
  
## Content:
- [x] How to make activity in Android not rotate. Set hard mode: Potrait
```java
<activity android:name=".MainActivity"
            android:screenOrientation="portrait">
```
- [x] set/get Title in activity in Android
```java
<activity android:name=".MainActivity"
            android:label="@string/app_name">
```
```java
Toast.makeText(this, MainActivity.this.getTitle(), Toast.LENGTH_SHORT).show();
```
- [x] How to build Multi-Language Supported Application
```java
- values-cs
- values-de
- values-eo
- ...
```
- [x] Using the library: <b>PagerSlidingTabStrip</b>
<p>Access link: <a href="https://github.com/astuetz/PagerSlidingTabStrip" rel="nofollow">Get dependency</a></p>

```java
compile 'com.jpardogo.materialtabstrip:library:1.0.6'
```
- [x] Using the library: <b>PagerSlidingTabStrip</b>
- [x] Working with restore Fragment when full ram or split screen

```java
@Provides
    @ActivityScope
    PagerAdapterPushed providePagerAdapterPushed(FragmentManager fragmentManager, TitleStringUtils titleStringUtils,
                                                 FileViewerFragment fileViewerFragment,
                                                 RecordFragment recordFragment) { return new PagerAdapterPushed(fragmentManager, titleStringUtils, fileViewerFragment, recordFragment); }
```

```java
@Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:{
                return mRecordFragment.newInstance(mRecordFragment, position);
            }
            case 1:{
                return mFileViewerFragment.newInstance(mFileViewerFragment, position);
            }
        }
        return null;
    }
```

```java
@SuppressLint("ValidFragment")
public class RecordFragment extends BaseFragment {

    private static final String ARG_POSITION = "mPosition";
    private int mPosition;

    public Fragment newInstance(RecordFragment fragment, int position) {
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        fragment.setArguments(b);
        return fragment;
    }

    public RecordFragment() {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_viewer, container, false);
        bindView(view);
        mPosition = getArguments().getInt(ARG_POSITION);
        return view;
    }
}
```
## Structure: Folder




