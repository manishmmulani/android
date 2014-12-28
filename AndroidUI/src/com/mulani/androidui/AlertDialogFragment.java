package com.mulani.androidui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment {

	public static AlertDialogFragment getInstance() {
		return new AlertDialogFragment();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setCancelable(true)
				.setPositiveButton("Visit MOMA",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent browseURL = new Intent(
										Intent.ACTION_VIEW, Uri
												.parse("http://moma.org"));
								startActivity(browseURL);
							}
						})
				.setNegativeButton("Not Now",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dismiss();
							}
						}).setMessage(R.string.dialog_message).create();
	}
}
