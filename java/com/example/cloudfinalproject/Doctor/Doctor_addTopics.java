package com.example.cloudfinalproject.Doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.cloudfinalproject.R;
import com.example.cloudfinalproject.module.Topics;
import com.example.cloudfinalproject.module.Topicsdoc;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Doctor_addTopics extends AppCompatActivity {
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//    private static final int PICK_IMG_REQUEST = 1;
//    Button choose_image_btn;
//    Button load_btn;
//    EditText textname;
//    EditText textdetail;
//    ProgressBar progressBar;
//
//    //image
//    ImageView imageView;
//    private StorageTask storageTask;
//    StorageReference storageReference;
//    DatabaseReference databaseReference;
//    Uri uri;
//
//    //video
//    VideoView videoView;
//    Button choose_video_btn;
//    Uri video_uri;
//    MediaController mediaController;
//    StorageReference storageReference2;

    ImageView imageView;
    VideoView videoView;
    Button Choosevideo;
    Uri videouri;
    MediaController mediaController;
    Button chooseimage;
    EditText address;
    EditText cotent;
    Button add_btn;
    Uri imageUri;
    StorageReference storageReference;
    StorageReference storageReference2;

    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add_topics);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

//        //video
//        videoView=findViewById(R.id.videoView);
//        choose_video_btn=findViewById(R.id.choose_video);
//        videoView.setMediaController(mediaController);
//        videoView.start();
//        //image
//        imageView = findViewById(R.id.image_add);
//        textdetail = findViewById(R.id.topic_details);
//        textname = findViewById(R.id.topic_address);
//        load_btn = findViewById(R.id.add_btn);
//        choose_image_btn = findViewById(R.id.choose_image);
//
//        storageReference = FirebaseStorage.getInstance().getReference("uploads");
//        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
//
//        choose_image_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                openFileChooser();
//            }
//        });
//        //video
//        choose_video_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        Intent intent=new Intent();
//                        intent.setType("video/*");
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(intent,101);
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        permissionToken.continuePermissionRequest();
//                    }
//
//                }).check();
//            }
//        });
//
//        load_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(storageTask != null && storageTask.isInProgress()){
//                    Toast.makeText(Doctor_addTopics.this, "Upload in progress ", Toast.LENGTH_LONG).show();
//
//                }else{
//                    uploadFile();
//                    //uploadVideo();
//
//                }
//            }
//        });
//        load_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Doctor_addTopics.this, HomeDoctor.class));
//
//                uploadVideo();
//
//            }
//        });
//    }
//    //this foe open gallery to choose image
//    private  void  openFileChooser(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,PICK_IMG_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 100 && data != null && data.getData()!= null){
//            uri=data.getData();
//            imageView.setImageURI(uri);
//
//        }else if (requestCode == 101 && data != null && data.getData()!= null){
//            video_uri=data.getData();
//            videoView.setVideoURI(video_uri);
//        }
//    }
//
//    private String getFileExtension(Uri uri) {
//        ContentResolver cR = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cR.getType(uri));
//    }
//    ///upload the image
//
//    private  void uploadFile(){
//
//        if (uri != null) {
//            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
//                    + "." + getFileExtension(uri));
//
//              storageTask=fileReference.putFile(uri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressBar.setProgress(0);
//                                }
//                            }, 2000);
//
//                            Toast.makeText(Doctor_addTopics.this, "Upload successful", Toast.LENGTH_LONG).show();
//                            upload upload = new upload(
//                                    textname.getText().toString().trim(),
//                                    textdetail.getText().toString().trim(),
//                                    taskSnapshot.getUploadSessionUri().toString());
//                            String uploadId = databaseReference.push().getKey();
//                            databaseReference.child(uploadId).setValue(upload);
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(Doctor_addTopics.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                            progressBar.setProgress((int) progress);
//                        }
//                    });
//        } else {
//            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
//        }
//    }
//    /// video
//    public void  uploadVideo(){
//        storageReference= FirebaseStorage.getInstance().getReference("videos/");
//        storageReference.putFile(video_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
//
//    }
//
//
//
//    /////////// add data to firebase
//    private void add_topics(View v){
//        String name = textname.getText().toString();
//        String detail = textdetail.getText().toString();
//        Map<String, Object> topic = new HashMap<>();
//        if(!name.isEmpty() && !detail.isEmpty()) {
//
//            topic.put("detail",detail);
//            topic.put("name",name);
//
//            db.collection("doctor_topics")
//                    .add(topic)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.e("dana", "Note added successfully ");
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.e("dana", "Failed to add note", e);
//                        }
//                    });
//
//        }else {
//            Toast.makeText(this,"please fill feild" ,Toast.LENGTH_SHORT).show();
//        }
//    }
//}

        imageView=findViewById(R.id.image_add);
        chooseimage=findViewById(R.id.choose_image);
        videoView=findViewById(R.id.videoView);
        Choosevideo=findViewById(R.id.choose_video);
        videoView.setMediaController(mediaController);
        videoView.start();
        add_btn=findViewById(R.id.add_btn);
        cotent=findViewById(R.id.topic_details);
        address=findViewById(R.id.topic_address);
        firebaseFirestore=FirebaseFirestore.getInstance();


        Choosevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         openVideo();
                btnEvent("ChooseVideo","Video","Button");
            }
        });


        chooseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
                btnEvent("ChooseImage","Image","Button");
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadimage();
                uploadVideo();
                btnEvent("addImage","Image","Button");

            }
        });
    }

    public void openVideo(){

        Intent intent=new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,101);
        btnEvent("OpenVideo","Video","Button");
    }
    public  void selectImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
        btnEvent("SelectImage","Image","Button");

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null && data.getData()!= null){
            imageUri=data.getData();
            imageView.setImageURI(imageUri);

        }else if (requestCode == 101 && data != null && data.getData()!= null){
            videouri=data.getData();
            videoView.setVideoURI(videouri);
        }
    }



    public void  uploadVideo(){
        storageReference= FirebaseStorage.getInstance().getReference("videos/");
        storageReference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String title=address.getText().toString();

                        Map<String, Object> user = new HashMap<>();
                        user.put("title", title.toString());
                        firebaseFirestore.collection("topicDOC").document().set(
                                new Topicsdoc(title,uri.toString())
                        );
                        btnEvent("UploadVideo","Video","Button");
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
    public void uploadimage() {
        storageReference = FirebaseStorage.getInstance().getReference("images/");
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageView.setImageURI(null);

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String title=address.getText().toString();
                        String content=cotent.getText().toString();

                        Map<String, Object> user = new HashMap<>();
                        user.put("title", title.toString());
                        user.put("content", content.toString());
                        firebaseFirestore.collection("Topics").document().set(
                                new Topics(title,content,uri.toString())
                        );
                    }
                });

                Toast.makeText(Doctor_addTopics.this, "uploaded", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Doctor_addTopics.this, HomeDoctor.class));
                btnEvent("UploadImage","Image","Button");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Doctor_addTopics.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
//        String title=address.getText().toString();
//                        String content=cotent.getText().toString();
//
//                        Map<String, Object> user = new HashMap<>();
//                        user.put("title", title.toString());
//                        user.put("content", content.toString());
//                        firebaseFirestore.collection("Topics").document().set(
//                                new Topics(title,content,uri.toString())
//                        );

    public  void btnEvent(String id,String name,String content){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, content);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
    }



