package mobileapp.leadgraph.com.leadgraph.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mobileapp.leadgraph.com.leadgraph.MainActivity;
import mobileapp.leadgraph.com.leadgraph.R;
import mobileapp.leadgraph.com.leadgraph.adapters.StafffListAdapter;
import mobileapp.leadgraph.com.leadgraph.controller.ChannelStaffController;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyDesignationController;
import mobileapp.leadgraph.com.leadgraph.controller.CompanyMasterController;
import mobileapp.leadgraph.com.leadgraph.controller.CountryApiCall;
import mobileapp.leadgraph.com.leadgraph.dialog.ItemDialog;
import mobileapp.leadgraph.com.leadgraph.dialog.PickerDialog;
import mobileapp.leadgraph.com.leadgraph.models.CityModel;
import mobileapp.leadgraph.com.leadgraph.models.CompanyDropDown;
import mobileapp.leadgraph.com.leadgraph.models.DesignationListModel;
import mobileapp.leadgraph.com.leadgraph.models.StaffListModel;
import mobileapp.leadgraph.com.leadgraph.models.StateModel;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;

import static android.app.Activity.RESULT_CANCELED;

/**
 * @author neeraj on 7/12/18.
 */
public class ChannelStaffFragment extends Fragment implements View.OnClickListener, PickerDialog.ImagePickerListener, CompanyMasterController.CompanyDropDownListner, ItemDialog.SelectionListener, CompanyDesignationController.DesignationListener, CountryApiCall.StateListener, CountryApiCall.CityListener, ChannelStaffController.StaffControllerListner, StafffListAdapter.CompanyActionListener {
    View view;
    TextView join_date, birth_date;
    private int CAMERA_REQUEST = 22;
    private int PICK_IMAGE_REQUEST = 23;
    ImageView img_profile;
    ImageView serach_btn, add_btn;
    CardView add_card;
    TextView save_btn, search_button, spin_company, designation, blood_grp, marital_status, city, state, search_pcomp;
    CompanyMasterController companyMasterController;
    CompanyDesignationController companyDesignationController;
    List<CompanyDropDown.ValueBean> valueBeans = new ArrayList<>();
    List<String> companies = new ArrayList<>();
    String st_parent, st_designation, st_email, st_name, st_contact, st_blood, st_marrital, st_state, st_city, st_joindate, st_dob, st_aadhar, st_pan;
    EditText email_staff, name_staff, contact_staff, pan_staff, aadhar_staff;
    List<String> bloodGroups = new ArrayList<>();
    List<String> maritalList = new ArrayList<>();
    CountryApiCall countryApiCall;
    List<StateModel.ValueBean> stateList = new ArrayList<>();
    List<String> stateString = new ArrayList<>();
    List<CityModel.ValueBean> cityList = new ArrayList<>();
    List<String> cityString = new ArrayList<>();
    Switch switch_k;
    boolean isActive;
    ChannelStaffController channelStaffController;
    RecyclerView list_search;
    EditText search_email, search_contact, search_name;
    List<StaffListModel.ValueBean> staffList = new ArrayList<>();
    StafffListAdapter stafffListAdapter;
    ImageView show_filter;
    LinearLayout lin_filters, search_card;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_channel_staff, container, false);
        }
        initViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSelected(false, false, true, false, false, false, false, false, false, false);
        ((MainActivity) getActivity()).setBottomNavigation(false, false, false);

        ((MainActivity) getActivity()).setTitle("Channel Staff");
    }

    private void initViews() {
        st_parent="";
        companyMasterController = new CompanyMasterController(getActivity());
        companyMasterController.setCompanyDropDownListner(this);
        channelStaffController = new ChannelStaffController(getActivity());
        stafffListAdapter = new StafffListAdapter(getActivity(), staffList);
        channelStaffController.setStaffControllerListner(this);
        countryApiCall = new CountryApiCall(getActivity());
        countryApiCall.callStates(this, "1");
        companyDesignationController = new CompanyDesignationController(getActivity());
        companyDesignationController.setDesignationListener(this);
        img_profile = view.findViewById(R.id.img_profile);
        join_date = view.findViewById(R.id.join_date);
        birth_date = view.findViewById(R.id.birth_date);
        birth_date.setOnClickListener(this);
        join_date.setOnClickListener(this);
        img_profile.setOnClickListener(this);
        serach_btn = view.findViewById(R.id.serach_btn);
        add_card = view.findViewById(R.id.add_card);
        search_card = view.findViewById(R.id.search_card);
        add_btn = view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        serach_btn.setOnClickListener(this);
        save_btn = view.findViewById(R.id.save_btn);
        search_button = view.findViewById(R.id.search_button);
        save_btn.setOnClickListener(this);
        search_button.setOnClickListener(this);
        spin_company = view.findViewById(R.id.spin_company);
        spin_company.setOnClickListener(this);
        designation = view.findViewById(R.id.designation);
        designation.setOnClickListener(this);
        email_staff = view.findViewById(R.id.email_staff);
        name_staff = view.findViewById(R.id.name_staff);
        contact_staff = view.findViewById(R.id.contact_staff);
        blood_grp = view.findViewById(R.id.blood_grp);
        blood_grp.setOnClickListener(this);
        marital_status = view.findViewById(R.id.marital_status);
        marital_status.setOnClickListener(this);
        state = view.findViewById(R.id.state);
        city = view.findViewById(R.id.city);
        city.setOnClickListener(this);
        state.setOnClickListener(this);
        bloodGroups.add("A+");
        bloodGroups.add("A-");
        bloodGroups.add("B+");
        bloodGroups.add("B-");
        bloodGroups.add("O+");
        bloodGroups.add("O-");
        bloodGroups.add("AB+");
        bloodGroups.add("AB-");
        maritalList.add("Married");
        maritalList.add("UnMarried");
        maritalList.add("Widowed");
        maritalList.add("Divorced");
        aadhar_staff = view.findViewById(R.id.aadhar_staff);
        pan_staff = view.findViewById(R.id.pan_staff);
        switch_k = view.findViewById(R.id.switch_k);
        search_pcomp = view.findViewById(R.id.search_pcomp);
        search_pcomp.setOnClickListener(this);
        search_name = view.findViewById(R.id.search_name);
        search_contact = view.findViewById(R.id.search_contact);
        search_email = view.findViewById(R.id.search_email);
        list_search = view.findViewById(R.id.list_search);
        stafffListAdapter.setCompanyActionListener(this);
        list_search.setAdapter(stafffListAdapter);
        lin_filters = view.findViewById(R.id.lin_filters);
        show_filter = view.findViewById(R.id.show_filter);
        show_filter.setOnClickListener(this);
        switch_k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isActive = b;
            }
        });
    }

    private void openDatePicker(final int flag) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                String text = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear();
                if (flag == 0) {
                    birth_date.setText(text);
                } else if (flag == 1) {
                    join_date.setText(text);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.birth_date:
                openDatePicker(0);
                break;
            case R.id.join_date:
                openDatePicker(1);
                break;
            case R.id.img_profile:
                showActionMenu();
                break;
            case R.id.serach_btn:
                add_card.setVisibility(View.GONE);
                search_card.setVisibility(View.VISIBLE);
                break;
            case R.id.add_btn:
                search_card.setVisibility(View.GONE);
                add_card.setVisibility(View.VISIBLE);
                clearUi();
                break;
            case R.id.spin_company:
                ItemDialog itemDialog = new ItemDialog(getActivity(), "Select", "p_comp", companies);
                itemDialog.setSelectionListener(this);
                itemDialog.show();
                break;
            case R.id.designation:
                ItemDialog itemDialog1 = new ItemDialog(getActivity(), "Select", "dsg", designations);
                itemDialog1.setSelectionListener(this);
                itemDialog1.show();
                break;
            case R.id.blood_grp:
                ItemDialog itemDialog2 = new ItemDialog(getActivity(), "Select", "bg", bloodGroups);
                itemDialog2.setSelectionListener(this);
                itemDialog2.show();
                break;
            case R.id.marital_status:
                ItemDialog itemDialog3 = new ItemDialog(getActivity(), "Select", "ms", maritalList);
                itemDialog3.setSelectionListener(this);
                itemDialog3.show();
                break;
            case R.id.state:
                ItemDialog itemDialog4 = new ItemDialog(getActivity(), "Select", "state", stateString);
                itemDialog4.setSelectionListener(this);
                itemDialog4.show();
                break;
            case R.id.city:
                ItemDialog itemDialog5 = new ItemDialog(getActivity(), "Select", "city", cityString);
                itemDialog5.setSelectionListener(this);
                itemDialog5.show();
                break;
            case R.id.save_btn:
                if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("save")) {
                    //call insert employee
                    channelStaffController.insertStaff(st_parent, st_name, st_designation, st_joindate, st_city,
                            st_contact, st_marrital, st_dob, st_blood, st_pan, "", st_aadhar, SPUtils.getString(getActivity(), SPUtils.LOGIN_ID)
                            , st_email, isActive);
                } else if (isValidated() && save_btn.getText().toString().equalsIgnoreCase("update")) {
                    channelStaffController.updateStaff(st_parent, st_name, st_designation, st_joindate, st_city,
                            st_contact, st_marrital, st_dob, st_blood, st_pan, "", st_aadhar, SPUtils.getString(getActivity(), SPUtils.LOGIN_ID)
                            , st_email, isActive, String.valueOf(save_btn.getTag()));
                }
                break;
            case R.id.search_pcomp:
                ItemDialog itemDialog6 = new ItemDialog(getActivity(), "Select", "search_pcomp", companies);
                itemDialog6.setSelectionListener(this);
                itemDialog6.show();
                break;
            case R.id.search_button:
                //call search list api here
                channelStaffController.getStaffList(search_name.getText().toString(),
                        st_parent, search_contact.getText().toString(), search_email.getText().toString());
                lin_filters.setVisibility(View.GONE);
                break;
            case R.id.show_filter:
                if (lin_filters.getVisibility() == View.VISIBLE) {
                    lin_filters.setVisibility(View.GONE);

                } else {
                    lin_filters.setVisibility(View.VISIBLE);
                }
                break;
        }
        ((MainActivity) getActivity()).didTapButton(view);

    }

    public void showActionMenu() {
        PickerDialog pickerDialog = new PickerDialog(getActivity());
        pickerDialog.setImagePickerListener(this);
        pickerDialog.show();
    }

    @Override
    public void onGallerySelect() {
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onCameraSelect() {
        Intent cameraIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "mobileapp.leadgraph.com.leadgraph.fileprovider",
                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == CAMERA_REQUEST) {
                    Picasso.with(getActivity()).load(new File(mCurrentPhotoPath)).transform(new CircleTransform()).into(img_profile);
                    Log.e("FILEPATH", mCurrentPhotoPath);
                    File file = new File(mCurrentPhotoPath);
                    String name = file.getName();
                    Log.e("FILENAME", name);
                    String base = null;
                    try {
                        base = encodeFileToBase64Binary(mCurrentPhotoPath);
                        // callUploadImage(name, base);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (requestCode == PICK_IMAGE_REQUEST) {

                    Uri uri = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                        img_profile.setImageBitmap(new CircleTransform().transform(bitmap));
                        String picturePath = getPath(getActivity(), uri);
                        Log.e("FILEPATH", picturePath);
                        File file = new File(picturePath);
                        String name = file.getName();
                        Log.e("FILENAME", name);
                        String base = null;
                        try {
                            base = encodeFileToBase64Binary(picturePath);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    }

    @Override
    public void dropDownList(List<CompanyDropDown.ValueBean> valueBeans) {
        this.valueBeans.clear();
        this.valueBeans.addAll(valueBeans);
        this.companies.clear();
        for (CompanyDropDown.ValueBean valueBean : valueBeans
                ) {
            companies.add(valueBean.getCompany_Name());
        }
        if (this.valueBeans.size() == 1) {
            //set parent company diffault
            onItemSelect(valueBeans.get(0).getCompany_Name(), "p_comp", 0);

        }
    }

    @Override
    public void onItemSelect(String item, String tag, int position) {
        if (tag.equalsIgnoreCase("p_comp")) {
            spin_company.setText(item);
            if (position==-1){
                st_parent = "";

            }else {
                st_parent = valueBeans.get(position).getCompanyUser_Idno();

            }
            companyDesignationController.getDesignationList(st_parent, "");
        } else if (tag.equalsIgnoreCase("dsg")) {
            designation.setText(item);
            if (position==-1){
                st_designation = "";

            }else {
                st_designation = valueBeansDesignation.get(position).getDesignation_Idno();
            }
        } else if (tag.equalsIgnoreCase("bg")) {
            blood_grp.setText(item);
            if (position==-1){
                st_blood = "";
            }else {
                st_blood = String.valueOf(position + 1);
            }
        } else if (tag.equalsIgnoreCase("ms")) {
            marital_status.setText(item);
            if (position==-1){
                st_marrital = "";

            }else {
                st_marrital = String.valueOf(position + 1);

            }
        } else if (tag.equalsIgnoreCase("state")) {
            state.setText(item);
            if (position==-1){
                st_state = "";
            }else {
                st_state = stateList.get(position).getState_Idno();
            }
            st_city="";
            city.setText("");
            countryApiCall.callCities(this, st_state);
        } else if (tag.equalsIgnoreCase("city")) {
            city.setText(item);
            if (position==-1){
                st_city = "";

            }else {
                st_city = cityList.get(position).getCity_Idno();

            }
        } else if (tag.equalsIgnoreCase("search_pcomp")) {
            search_pcomp.setText(item);
            if (position==-1){
                st_parent = "";

            }else {
                st_parent = valueBeans.get(position).getCompanyUser_Idno();

            }
        }
    }

    List<DesignationListModel.ValueBean> valueBeansDesignation = new ArrayList<>();
    List<String> designations = new ArrayList<>();

    @Override
    public void designatioList(List<DesignationListModel.ValueBean> valueBeans) {
        valueBeansDesignation.clear();
        valueBeansDesignation.addAll(valueBeans);
        designations.clear();
        for (DesignationListModel.ValueBean valueBean : valueBeans
                ) {
            designations.add(valueBean.getDesignation_Name());
        }

    }

    @Override
    public void allStates(StateModel stateModel) {
        stateList.clear();
        stateString.clear();
        stateList.addAll(stateModel.getValue());
        for (StateModel.ValueBean valueBean : stateModel.getValue()
                ) {
            stateString.add(valueBean.getState_Name());
        }
        companyMasterController.getComapnyDropDown(SPUtils.getString(getActivity(), SPUtils.COMPANY_USER_ID));
    }

    @Override
    public void allCities(CityModel cityModel) {
        cityList.clear();
        cityString.clear();
        cityList.addAll(cityModel.getValue());
        for (CityModel.ValueBean valueBean : cityModel.getValue()
                ) {
            cityString.add(valueBean.getCity_Name());
        }
    }

    @Override
    public void staffList(List<StaffListModel.ValueBean> valueBeans) {
        this.staffList.clear();
        this.staffList.addAll(valueBeans);
        stafffListAdapter.notifyDataSetChanged();
    }

    @Override
    public void edit(int position) {
        //set ui here
        add_card.setVisibility(View.VISIBLE);
        search_card.setVisibility(View.GONE);
        setUi(staffList.get(position));
    }

    @Override
    public void delete(int position) {
        //call here delete api
        channelStaffController.deleteStaff(staffList.get(position).getUser_Idno());
        staffList.remove(position);
        stafffListAdapter.notifyDataSetChanged();

    }

    public class CircleTransform implements com.squareup.picasso.Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

    private String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);
        String encodedString = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return encodedString;

    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private boolean isValidated() {
        st_contact = contact_staff.getText().toString();
        st_email = email_staff.getText().toString();
        st_name = name_staff.getText().toString();
        st_joindate = join_date.getText().toString();
        st_dob = birth_date.getText().toString();
        st_aadhar = aadhar_staff.getText().toString();
        st_pan = pan_staff.getText().toString();
        if (st_name.isEmpty()) {
            name_staff.setError("name required!");
            return false;
        } else if (st_email.isEmpty()) {
            email_staff.setError("email required!");
            return false;
        } else if (!st_email.isEmpty() && !SPUtils.isValidateEmail(st_email)) {
            email_staff.setError("Invalid Email!");

            return false;
        } else if (st_contact.isEmpty()) {
            contact_staff.setError("contact required!");
            return false;
        } else if (!st_contact.isEmpty() && st_contact.length() < 10) {
            contact_staff.setError("Contact number should be 10 digit!");

            return false;
        } else if (st_marrital.isEmpty()) {
            marital_status.setError("please select!");
            return false;
        } else if (st_blood.isEmpty()) {
            blood_grp.setError("please Select");
            return false;
        } else if (st_designation.isEmpty()) {
            designation.setError("please select!");
            return false;
        } else if (st_state.isEmpty()) {
            state.setError("please select state!");
            return false;
        } else if (st_city.isEmpty()) {
            city.setError("please select city!");
            return false;
        } else if (st_dob.isEmpty()) {
            birth_date.setError("Select date dob!");
            return false;
        } else if (st_joindate.isEmpty()) {
            join_date.setError("select join date!");
            return false;
        } else if (st_pan.isEmpty()) {
            pan_staff.setError("pan number required!");
            return false;
        } else if (st_aadhar.isEmpty()) {
            aadhar_staff.setError("aadhar number required!");
            return false;
        }
        return true;
    }

    private void clearUi() {
        //clear ui on click add button
        spin_company.setText("Select");
        contact_staff.setText("");
        name_staff.setText("");
        email_staff.setText("dd-mm-yyyy");
        join_date.setText("dd-mm-yyyy");
        designation.setText("Select");
        birth_date.setText("dd-mm-yyyy");
        blood_grp.setText("Select");
        state.setText("Select");
        city.setText("Select");
        marital_status.setText("Select");
        pan_staff.setText("");
        aadhar_staff.setText("");
        save_btn.setText("Save");

    }

    private void setUi(StaffListModel.ValueBean ui) {
        spin_company.setText(ui.getComp_Name());
        st_parent = ui.getCompanyUser_Idno();
        maritalList.add("Married");
        maritalList.add("UnMarried");
        maritalList.add("Widowed");
        maritalList.add("Divorced");
        if (ui.getUser_MaritalStatus().equalsIgnoreCase("married")) {
            st_marrital = "1";
        } else if (ui.getUser_MaritalStatus().equalsIgnoreCase("UnMarried")) {
            st_marrital = "2";
        } else if (ui.getUser_MaritalStatus().equalsIgnoreCase("Widowed")) {
            st_marrital = "3";
        } else if (ui.getUser_MaritalStatus().equalsIgnoreCase("Divorced")) {
            st_marrital = "4";
        }
        save_btn.setTag(ui.getUser_Idno());
        companyDesignationController.getDesignationList(st_parent, "");
        contact_staff.setText(ui.getUser_MobileNo());
        name_staff.setText(ui.getUser_Name());
        email_staff.setText(ui.getUser_Email());
        join_date.setText(ui.getUser_DOJ());
        designation.setText(ui.getDesignation_Name());
        birth_date.setText(ui.getUser_DOB());
        blood_grp.setText(ui.getUser_BloodGroup());
        state.setText(ui.getState_Name());
        city.setText(ui.getCity_Name());
        marital_status.setText(ui.getUser_MaritalStatus());
        pan_staff.setText(ui.getUser_Pan());
        aadhar_staff.setText(ui.getUser_AadhaarNo());
        save_btn.setText("UPDATE");

    }
}
