package com.unipagosintegration;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class MainActivity extends Activity implements OnClickListener {
	/**
	 * Determines whether to always show the simplified settings UI, where
	 * settings are presented in a single list. When false, settings are shown
	 * as a master/detail two-pane view on tablets. When true, a single pane is
	 * shown on tablets.
	 */
	private static final boolean ALWAYS_SIMPLE_PREFS = false;
	private EditText referenceEditText;
	private EditText refTextEditText;
	private EditText amountEditText;
	private EditText recipientEditText;
	private Button button;

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity_layout);
		referenceEditText = (EditText)findViewById(R.id.referenceEditText);
		refTextEditText = (EditText)findViewById(R.id.refTextEditText);
		amountEditText = (EditText)findViewById(R.id.amountEditText);
		recipientEditText = (EditText)findViewById(R.id.recipientEditText);
		
		button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(this);
	}
	
	
//	/**
//	 * Shows the simplified settings UI if the device configuration if the
//	 * device configuration dictates that a simplified, single-pane UI should be
//	 * shown.
//	 */
//	private void setupSimplePreferencesScreen() {
//		if (!isSimplePreferences(this)) {
//			return;
//		}
//
//		// In the simplified UI, fragments are not used at all and we instead
//		// use the older PreferenceActivity APIs.
//
//		// Add 'general' preferences.
//		addPreferencesFromResource(R.xml.pref_general);
//
//		// Add 'notifications' preferences, and a corresponding header.
//		PreferenceCategory fakeHeader = new PreferenceCategory(this);
//		fakeHeader.setTitle(R.string.pref_header_notifications);
//		getPreferenceScreen().addPreference(fakeHeader);
//		addPreferencesFromResource(R.xml.pref_notification);
//
//		// Add 'data and sync' preferences, and a corresponding header.
//		fakeHeader = new PreferenceCategory(this);
//		fakeHeader.setTitle(R.string.pref_header_data_sync);
//		getPreferenceScreen().addPreference(fakeHeader);
//		addPreferencesFromResource(R.xml.pref_data_sync);
//
//		// Bind the summaries of EditText/List/Dialog/Ringtone preferences to
//		// their values. When their values change, their summaries are updated
//		// to reflect the new value, per the Android Design guidelines.
//		bindPreferenceSummaryToValue(findPreference("example_text"));
//		bindPreferenceSummaryToValue(findPreference("example_list"));
//		bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
//		bindPreferenceSummaryToValue(findPreference("sync_frequency"));
//	}
//
//	/** {@inheritDoc} */
//	@Override
//	public boolean onIsMultiPane() {
//		return isXLargeTablet(this) && !isSimplePreferences(this);
//	}

	/**
	 * Helper method to determine if the device has an extra-large screen. For
	 * example, 10" tablets are extra-large.
	 */
	private static boolean isXLargeTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (validateFields()) {
			String recipient = recipientEditText.getText().toString();
			String amount = amountEditText.getText().toString();
			String refId = referenceEditText.getText().toString();
			String refText = refTextEditText.getText().toString();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("unipagos://pay?r=")
				.append("(mdn:")
				.append(recipient).append(")")
				.append("&a=")
				.append(amount);			
			if (refId != null && refId.length() != 0) {
				stringBuilder.append("&i=").append(refId);
			}			
			if (refText != null && refText.length() != 0) {
				stringBuilder.append("&t=").append(refText);
			}
			stringBuilder.append("&url=").append("unipagosIntegrationApp");
			Uri webpage = Uri.parse(stringBuilder.toString());
		    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
		    if (intent.resolveActivity(getPackageManager()) != null) {
		        startActivity(intent);
		    }
		}
		
	}

	private boolean validateFields() {
		// TODO Auto-generated method stub
		if (recipientEditText.getText() == null && recipientEditText.getText().length() == 0)
			return false;
		if (amountEditText.getText() == null && amountEditText.getText().length() == 0)
			return false;		
		return true;
	}

	


}
