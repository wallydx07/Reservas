package com.example.reservas.view;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.reservas.MainActivity;
import com.example.reservas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Usuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Usuario extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View v;
    private static String APP_DIRECTORY = "MyPictureApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private ImageView mSetImage;
    private Button bottomGuardar, buttoncerrar;
    EditText nameUser, correo, pass;
    private RelativeLayout mRlView;
    private String mPath;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    DatabaseReference mDatabase;
    private static final String TAG = "Usuario";

    public Usuario() {
    }
    public static Usuario newInstance(String param1, String param2) {
        Usuario fragment = new Usuario();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    public void updateProfile() {
        // [START update_profile]
        String ID="";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
             ID = user.getUid();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        System.out.println("el id es:"+ID);
        String finalID = ID;
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("guia").child(finalID);
        Map<String, Object> userData = new HashMap<>();
        userData.put("nombre", nameUser.getText().toString()  );
        userRef.setValue(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity().getApplicationContext(), "Cambio Realizado con exito", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                });

        String newName=String.valueOf(nameUser.getText());
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(newName)
                    .build();
                user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "User profile updated.");
                    }
                }
            });
        }


                    // [END update_profile]

                @SuppressLint("SetTextI18n")
                public void getUserProfile (){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        nameUser.setText(user.getDisplayName());
                        correo.setText(user.getEmail());
                     //   Uri photoUrl = user.getPhotoUrl();
                      //  System.out.println(photoUrl + "sfdhd7897898");
                        //mSetImage.setImageURI(photoUrl);

// Check if user's email is verified
                        boolean emailVerified = user.isEmailVerified();
                        String uid = user.getUid();
                    } else {
                        System.out.println("fghjhgfdf0dsfhgnhdgfdsah");
                    }
                }

                @Override
                public View onCreateView (LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState){
                    // Inflate the layout for this fragment
                    v = inflater.inflate(R.layout.fragment_usuario, container, false);
                    mSetImage = v.findViewById(R.id.userViewUser);
                    bottomGuardar = v.findViewById(R.id.buttonGuardar);
                    bottomGuardar.setOnClickListener(this);
                    buttoncerrar = v.findViewById(R.id.buttonCerrarSecion);
                    buttoncerrar.setOnClickListener(this);
                    nameUser = v.findViewById(R.id.txtUser);
                    correo= v.findViewById(R.id.editTextTextEmailAddress);
                    pass= v.findViewById(R.id.editTextNumberPassword);
                   // mSetImage.setOnClickListener(this);
                    getUserProfile();
                    return v;//muy impoortante
                }
                @Override
                public void onClick (View view){
                    switch (view.getId()) {
                        case R.id.userViewUser:
                         //   showOptions();
                            break;
                        case R.id.buttonGuardar:
                            updateProfile();
                            updateEmail ();
                            String ps=pass.getText().toString();
                            if(ps.isEmpty()){

                            }else{
                                if (ps.length()<6) {
                                    Toast.makeText(getContext(), "Contraseña debe ser mayor a 6 numeros", Toast.LENGTH_SHORT).show();

                                }else {
                                    updatePassword (ps);
                                }






                            }


                            break;
                        case R.id.buttonCerrarSecion:
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            getActivity().finish();
                            break;
                    }
                }

                private void showOptions () {
                    //final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    final CharSequence[] option = {"Tomar foto","Elegir de galeria", "Cancelar"};

                    builder.setTitle("Eleige una opción");
                    builder.setItems(option, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (option[which] == "Tomar foto") {
                                openCamera();
                            } else if (option[which] == "Elegir de galeria") {
                                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                            } else {
                                dialog.dismiss();
                            }
                        }
                    });

                    builder.show();
                }

                private void openCamera () {
                    File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
                    boolean isDirectoryCreated = file.exists();

                    if (!isDirectoryCreated)
                        isDirectoryCreated = file.mkdirs();

                    if (isDirectoryCreated) {
                        Long timestamp = System.currentTimeMillis() / 1000;
                        String imageName = timestamp.toString() + ".jpg";

                        mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                                + File.separator + imageName;

                        File newFile = new File(mPath);

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
                        startActivityForResult(intent, PHOTO_CODE);
                    }
                }
                public void updateEmail () {
                    // [START update_email]
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    user.updateEmail(correo.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User email address updated.");
                                    }
                                }
                            });
                    // [END update_email]
                }
                public void updatePassword (String p) {
                    // [START update_password]
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String newPassword = p;
                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "User password updated.");
                                    }else {
                                        Log.w(TAG, "Error updating password.", task.getException());
                                    }
                                }
                            });
                    // [END update_password]
                }

                @SuppressLint("WrongConstant")
                @Override
                public void onActivityResult ( int requestCode, int resultCode,
                @Nullable Intent data){
                    super.onActivityResult(requestCode, resultCode, data);
                    if (resultCode == RESULT_OK) {
                        switch (requestCode) {
                            case PHOTO_CODE:
                                MediaScannerConnection.scanFile(getContext().getApplicationContext(),
                                        new String[]{mPath}, null,
                                        new MediaScannerConnection.OnScanCompletedListener() {
                                            @Override
                                            public void onScanCompleted(String path, Uri uri) {
                                                Log.i("ExternalStorage", "Scanned " + path + ":");
                                                Log.i("ExternalStorage", "-> Uri = " + uri);
                                            }
                                        });


                                Bitmap bitmap = BitmapFactory.decodeFile(mPath);
                                mSetImage.setImageBitmap(bitmap);
                                break;
                            case SELECT_PICTURE:
                                imageUri = data.getData();

                                getActivity().getApplication().getContentResolver().takePersistableUriPermission(imageUri
                                        , data.getFlags()&(Intent.FLAG_GRANT_READ_URI_PERMISSION
                                                + Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                                        )
                                );

                                mSetImage.setImageURI(imageUri);
                                break;

                        }
                    }
                }
            }




