package com.geo.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ThumbnailZoomFragment extends DialogFragment {

    private static final String ARG_FILE = "photofile";

    private File mPhotoFile;
    private ImageView mPhotoView;

    public static ThumbnailZoomFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FILE, file);
        ThumbnailZoomFragment fragment = new ThumbnailZoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mPhotoFile = (File) getArguments().getSerializable(ARG_FILE);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_thumbnail_zoom, null);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Ho Ho Ho")
                .create();
        Button mCloseButton = (Button) v.findViewById(R.id.close_thumbnail_button);
        mPhotoView = (ImageView) v.findViewById(R.id.zoom_thumbnail);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        updatePhotoView();

        return dialog;
    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}
