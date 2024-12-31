package com.example.lanugagebasicstest.keyboardDetection;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class KeyboardAccessibilityService extends AccessibilityService {

    private static final String TAG = "KeyboardAccessibilityService";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if(event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED){
            Log.d(TAG, "onAccessibilityEvent: " + event.getText());
        }

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;
        info.notificationTimeout = 100;
        setServiceInfo(info);

        Log.d(TAG, "onServiceConnected");
    }
}
