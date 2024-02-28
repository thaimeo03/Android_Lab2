package com.example.lab2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    private EditText edtId, edtName, edtPhone;
    private CheckBox cbAdd;
    private Button btnAdd, btnCancel;
    private RecyclerView rcvAvatar;
    private ImgAdapter imgAdapter;

    private ImageView imageView;
    private ArrayList<Avatar> listImg = new ArrayList<>();

    private String _uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Init();
        UpdateData();

        Intent i = getIntent();
        ArrayList<Integer> listId = i.getIntegerArrayListExtra("listId");
//        System.out.println(listId.size());
        btnAdd.setOnClickListener(v->{
            int id = 0;
            try {
                id = Integer.parseInt(edtId.getText().toString());
                
                } catch (Exception e){
                    Toast.makeText(this, "Cần điền id", Toast.LENGTH_SHORT).show();
                }
            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            boolean status = cbAdd.isChecked();
            String img = _uri;

            if(Validate(id, name, phone, listId)) {
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putInt("id", id);
                b.putString("name", name);
                b.putString("phone", phone);
                b.putString("img", img);
                b.putBoolean("status", status);

                intent.putExtras(b);
                setResult(RESULT_OK, intent);
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(v->{
            finish();
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
            }
        });
    }

    private void UpdateData() {
        listImg.add(new Avatar(R.drawable.img1, false));
        listImg.add(new Avatar(R.drawable.img2, false));
        listImg.add(new Avatar(R.drawable.img3, false));
        listImg.add(new Avatar(R.drawable.img4, false));

        imgAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            _uri = selectedImageUri.toString();
            imageView.setImageURI(selectedImageUri);
        }
    }

    private boolean Validate(int id, String name, String phone, ArrayList<Integer> listId) {
        if(name.equals("") || phone.equals("")){
            Toast.makeText(this, "Cần điền đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i=0;i<listId.size();i++){
            if(listId.get(i) == id) {
                Toast.makeText(this, "id đã tồn tại", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        
        return true;
    }

    private void Init() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtFindName);
        edtPhone = findViewById(R.id.edtPhone);
        cbAdd = findViewById(R.id.cbAdd);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
//        rcvAvatar = findViewById(R.id.rcvAvatar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
//        rcvAvatar.setLayoutManager(linearLayoutManager);
        imgAdapter = new ImgAdapter(this, listImg);
//        rcvAvatar.setAdapter(imgAdapter);
        imageView = findViewById(R.id.imgViewSelect);
    }
}