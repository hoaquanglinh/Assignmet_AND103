
package com.example.assignmet_ph43159;

import static android.content.ContentValues.TAG;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignmet_ph43159.Adapter.AdapterCay;
import com.example.assignmet_ph43159.Model.Cay;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterCay adapter;
    List<Cay> list;
    APIService apiService;
    EditText edten, edgia, edkichthuoc, edtlinkurl;
    ImageView anh;
    Uri selectedImage;
    Cay cay;
    private static final int REQUEST_MANAGE_EXTERNAL_STORAGE_PERMISSION = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);

        loadData();

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them(Home.this, 0, cay);
            }
        });

    }

    void loadData(){
        Call<List<Cay>> call = apiService.getCays();

        call.enqueue(new Callback<List<Cay>>() {
            @Override
            public void onResponse(Call<List<Cay>> call, Response<List<Cay>> response) {
                if (response.isSuccessful()) {
                    list = response.body();

                    adapter = new AdapterCay(list,  getApplicationContext(), Home.this);

                    recyclerView = findViewById(R.id.recyclerViewSinhVien);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cay>> call, Throwable t) {
                Log.e("Main", t.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImage = data.getData();
            anh.setImageURI(selectedImage);
        }
    }

    public void them(Context context, int type, Cay cay) {
        Dialog dialog = new Dialog(Home.this);
        dialog.setContentView(R.layout.dialog_save);

        edten = dialog.findViewById(R.id.edtten);
        edgia = dialog.findViewById(R.id.edtgia);
        edkichthuoc = dialog.findViewById(R.id.edtkichthuoc);
//        anh = dialog.findViewById(R.id.imgImage);
        edtlinkurl = dialog.findViewById(R.id.edturl);

//        anh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
//                    // Kiểm tra và yêu cầu quyền truy cập vào bộ nhớ ngoài
//                    if (!Environment.isExternalStorageManager()) {
//                        Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//                        startActivity(intent);
//                        return;
//                    }
//                } else {
//                    // Kiểm tra và yêu cầu quyền truy cập vào bộ nhớ ngoài
//                    if (ContextCompat.checkSelfPermission(Home.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                            != PackageManager.PERMISSION_GRANTED) {
//                        // Yêu cầu quyền truy cập vào bộ nhớ ngoài
//                        ActivityCompat.requestPermissions(Home.this,
//                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                                2);
//                        return;
//                    }
//                }
//
//                moThuVienAnh();
//            }
//        });

        if (type != 0){
            edten.setText(cay.getTen());
            edgia.setText(cay.getGia()+"");
            edkichthuoc.setText(cay.getKichthuoc());
            edtlinkurl.setText(cay.getAnh());
        }
        dialog.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edten.getText().toString();
                String giastr = edgia.getText().toString();
                String kichthuoc = edkichthuoc.getText().toString();
                String anh = edtlinkurl.getText().toString();

//                // Lấy đường dẫn của file ảnh
//                String imagePath = getPath(selectedImage);
//                Log.d(TAG, "anhvjp: "+ imagePath);
//
//                // Tạo instance của File từ đường dẫn
//                File imageFile = new File(imagePath);
//
//                // Tạo request body cho file ảnh
//                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
//
//                // Tạo multipart body part từ request file
//                MultipartBody.Part body = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);

                if (ten.length() == 0 || giastr.length() == 0 || kichthuoc.length() == 0 || anh.length() == 0){
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Double gia = Double.parseDouble(giastr);

                    if (gia > 0){
                        Cay cay1 = new Cay(anh, ten, gia, kichthuoc);

                        if (type == 0){
                            Call<Void> call = apiService.addCay(cay1);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        loadData();
                                        Toast.makeText(Home.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(Home.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Log.e("Home", "Call failed: " + t.toString());
                                    Toast.makeText(Home.this, "Đã xảy ra lỗi khi thêm dữ liệu", Toast.LENGTH_SHORT).show();
                                }

                            });
                        }else{
                            Call<Void> call = apiService.updateCay(cay.get_id(), cay1);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        loadData();
                                        Toast.makeText(Home.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(Home.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Log.e("Home", "Call failed: " + t.toString());
                                    Toast.makeText(Home.this, "Đã xảy ra lỗi khi sửa dữ liệu", Toast.LENGTH_SHORT).show();
                                }

                            });
                        }
                    }else{
                        Toast.makeText(context, "Giá phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                    }

                }catch (NumberFormatException e){
                    Toast.makeText(context, "Gía phải là số", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public  void xoa(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<Void> call = apiService.deleteCay(id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            loadData();
                            Toast.makeText(Home.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Home.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("Home", "Call failed: " + t.toString());
                        Toast.makeText(Home.this, "Đã xảy ra lỗi khi xóa dữ liệu", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();

    }

    private void moThuVienAnh() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        }
        return uri.getPath();
    }

}