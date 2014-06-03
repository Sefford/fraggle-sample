package com.sefford.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefford.FraggleSampleActivity;
import com.sefford.R;
import com.sefford.fraggle.interfaces.FraggleFragment;

import java.util.Random;

/**
 * Sample Fragment that implements FraggleFragment. It is a configurable Fragment for Entry and Single
 * instances flags.
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class SampleFragment extends Fragment implements FraggleFragment {

    public static final String EXTRA_ENTRY = "extra_is_entry";
    public static final String EXTRA_INSTANCE = "extra_is_single";
    public static final String EXTRA_NUMBER = "extra_number";
    public static final String EXTRA_TARGET = "extra_target";

    protected boolean isEntryFragment;
    protected boolean isSingleInstance;
    protected int number;
    protected String target;
    protected String tag;

    protected TextView tvFragmentTag;
    protected CheckedTextView cbEntry;
    protected CheckedTextView cbSingle;
    protected CheckedTextView cbCustom;
    protected CheckedTextView cbBack;
    protected Button btCreate;
    private ImageView ivColor;

    protected Random random = new Random();
    protected int newColor;

    /**
     * Creates a new compatible bundle with this Fragment
     *
     * @param target   Custom TAG with the onBackPressed target
     * @param number   Number of Fragment (autoincremental)
     * @param isEntry  Flags if is an Entry Fragment
     * @param isSingle Flags if is a Single Fragment
     * @return Bundle ready to be set via {@link Fragment#setArguments(android.os.Bundle) setArguments}
     */
    public static Bundle createBundle(String target, int number, boolean isEntry, boolean isSingle) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TARGET, target);
        bundle.putInt(EXTRA_NUMBER, number);
        bundle.putBoolean(EXTRA_ENTRY, isEntry);
        bundle.putBoolean(EXTRA_INSTANCE, isSingle);
        return bundle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newColor = computeNewColor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.screen_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        processArguments(getArguments());
        tvFragmentTag = (TextView) view.findViewById(R.id.tv_tag);
        cbEntry = (CheckedTextView) view.findViewById(R.id.cb_entry);
        cbSingle = (CheckedTextView) view.findViewById(R.id.cb_single);
        cbCustom = (CheckedTextView) view.findViewById(R.id.cb_custom);
        cbBack = (CheckedTextView) view.findViewById(R.id.cb_back);
        btCreate = (Button) view.findViewById(R.id.bt_create);
        ivColor = (ImageView) view.findViewById(R.id.iv_color);

        ivColor.setImageDrawable(new ColorDrawable(newColor));
        tvFragmentTag.setText(tag);
        configureActionListeners();
    }

    protected void configureActionListeners() {
        cbEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbEntry.setChecked(!cbEntry.isChecked());
            }
        });
        cbSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbSingle.setChecked(!cbSingle.isChecked());
            }
        });
        cbCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbCustom.setChecked(!cbCustom.isChecked());
            }
        });
        cbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbBack.setChecked(!cbBack.isChecked());
            }
        });
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * This Call will take into account:
                 *
                 * If "make it return to first" will create a fragment whose onBackTarget returns the
                 * Fragment #1 to showcase the custom flow return
                 *
                 * If "Is Single Instance" will create a "Fragment #1" instance and will force Fraggle
                 * to pop back to Fragment #1.
                 *
                 * "Show a Toast on Back" will open a Fragment that first will show a Toast, then
                 * go back to demonstrate the feature of custom actions on backPressed.
                 *
                 * If "Is Entry Fragment" a Fragment will create a Fragment that will trigger Fraggle
                 * to reach an Activity finish() termination
                 */
                final Bundle bundle = createBundle(cbBack.isChecked() ? getString(R.string.fragment_tag, 1) : "",
                        cbSingle.isChecked() ? 1 : number + 1,
                        cbEntry.isChecked(), cbSingle.isChecked());
                if (cbCustom.isChecked()) {
                    ((FraggleSampleActivity) getActivity()).openCustomFragment(bundle);
                } else {
                    ((FraggleSampleActivity) getActivity()).openNormalFragment(bundle);
                }
            }
        });
    }

    /**
     * Computes a new Random color for differentiation between other fragments
     *
     * @return Hexadecimal (int) representation of a color
     */
    protected int computeNewColor() {
        return Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Override
    public boolean customizedOnBackPressed() {
        return false;
    }

    @Override
    public String getFragmentTag() {
        return tag;
    }

    @Override
    public boolean isEntryFragment() {
        return isEntryFragment;
    }

    @Override
    public boolean isSingleInstance() {
        return isSingleInstance;
    }

    @Override
    public void onFragmentVisible() {
        // This space for rent
    }

    @Override
    public void onFragmentNotVisible() {
        // This space for rent
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    @Override
    public String onBackPressedTarget() {
        return target;
    }

    /**
     * Processes the Arguments of this kind of Fragments.
     *
     * @param args Bundle with the arguments to process
     */
    public void processArguments(Bundle args) {
        isEntryFragment = args.getBoolean(EXTRA_ENTRY);
        isSingleInstance = args.getBoolean(EXTRA_INSTANCE);
        number = args.getInt(EXTRA_NUMBER);
        target = args.getString(EXTRA_TARGET);
    }

    /**
     * Sets the tag. This is a limitation because Fraggle still does not correctly support dynamic
     * TAG identification before it has been introduced into the fagment manager.
     *
     * @param tag Fragment Identification Tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}
