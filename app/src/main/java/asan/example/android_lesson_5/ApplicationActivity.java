package asan.example.android_lesson_5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ApplicationActivity extends AppCompatActivity {

    private ImageView image;
    private EditText etName;
    private EditText etPhone;
    private Button btnSave;
    private static final int REQUEST_CODE = 200;
    public static final String KAY = "kay";
    Uri imageData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        init();
    }

    private void init() {
    image = (ImageView)findViewById(R.id.iconAdd);
    etName = (EditText)findViewById(R.id.etName);
    etPhone = (EditText)findViewById(R.id.etPhon);
    btnSave = (Button)findViewById(R.id.btnSave);

    image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Choose image"), REQUEST_CODE);
        }
    });
    btnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String titleName = etName.getText().toString();
            String titlePhone = etPhone.getText().toString();
            String image = imageData.toString();
            Intent intent = new Intent();
            TitleModel model = new TitleModel();
            model.setTitle(titleName);
            model.setNumber(titlePhone);
            model.setImageView(image);
            intent.putExtra(KAY, model);
            setResult(RESULT_OK, intent);
            finish();
        }
    });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
            imageData = data.getData();
            image.setImageURI(imageData);
            }
        }
    }
}