package com.udacity.gradle.builditbigger;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import com.example.evindj.androidjokes.JokeActivity;

/**
 * Created by evindj on 12/22/15.
 */
public class JokeButtonClickTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public JokeButtonClickTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }
    private MainActivity mainActivity;
    private Button jokeButton;
    @Override
    protected void setUp() throws Exception{
        super.setUp();
        setActivityInitialTouchMode(true);
        mainActivity = getActivity();
        jokeButton = (Button)mainActivity.findViewById(R.id.jokeButton);
    }
    @MediumTest
    public void testJokeActivityWasLaunched(){
        Instrumentation.ActivityMonitor jokeActivityMonitor = getInstrumentation().addMonitor(JokeActivity.class.getName(),null, false);
        TouchUtils.clickView(this, jokeButton);
        JokeActivity jokeActivity = (JokeActivity) jokeActivityMonitor.waitForActivityWithTimeout(2000);
        assertNotNull("jokeActivity is null", jokeActivity);
        assertEquals("Monitor for JokeActivity has not been called", 1, jokeActivityMonitor.getHits());
        assertEquals("Activity is of wrong type", JokeActivity.class, jokeActivity.getClass());
        Intent intent = jokeActivity.getIntent();
        final String joke = intent.getStringExtra(JokeActivity.EXTRA_MESSAGE);
        assertEquals("message is empty",joke,"");
    }
}
