package com.example.ivonne.ropadeportiva;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ivonne.ropadeportiva.models.Producto;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mAcademicoReference;
    //private DatabaseReference mProductoReference;
    private String coleccionProductos="productos";

    Button  eliminar, siguiente, foto;
    ImageView imagen;

    private StorageReference storage;
    private ProgressDialog progress;

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode,data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){

            progress.setMessage("Subiendo Imagen");
            progress.show();

            Uri uri = data.getData();

            StorageReference filepath = storage.child("Fotos de Ropas").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    progress.dismiss();

                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Picasso.with(MainActivity.this).load(downloadUri).fit().centerCrop().into (imagen);

                    Toast.makeText(MainActivity.this, "Foto Subida...", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAcademicoReference = FirebaseDatabase.getInstance().getReference();
        //mProductoReference = mAcademicoReference.child(coleccionProductos);

        progress = new ProgressDialog(this);
        storage = FirebaseStorage.getInstance().getReference();

        imagen =  findViewById(R.id.ivimagen);
        foto = findViewById(R.id.btnfoto);

       /*eliminar =  findViewById(R.id.btneliminar) ;
        eliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });*/

        foto  = findViewById(R.id.btnfoto);
        foto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }
        });

        //mAcademicoReference = FirebaseDatabase.getInstance().getReference();
        siguiente = (Button)findViewById(R.id.buttonSiguiente);
        siguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(siguiente);
            }
        });
    }

        public  void buttonGuardarClick(View view){
        EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        EditText editTextColor = (EditText) findViewById(R.id.editTextColor);
        EditText editTextCantidad = (EditText) findViewById(R.id.editTextCantidad);
        EditText editTextPrecio = (EditText) findViewById(R.id.editTextPrecio);
        EditText editTextSexo = (EditText) findViewById(R.id.editTextSexo);
        EditText editTextTalla = (EditText) findViewById(R.id.editTextTalla);
        EditText editTextTela = (EditText) findViewById(R.id.editTextTela);

        String cadena = "Prdt" + editTextNombre.getText().toString().substring(0, editTextNombre.getText().toString().indexOf(' ', 0)-1);


        Producto prod = new Producto(editTextNombre.getText().toString(),editTextColor.getText().toString(),
                editTextCantidad.getText().toString(),editTextPrecio.getText().toString(),
                editTextSexo.getText().toString(),editTextTalla.getText().toString(),
                editTextTela.getText().toString());
        mAcademicoReference.child(coleccionProductos).child(cadena).setValue(prod);
        //Grabar(prod);
    }
/*
    private String Grabar(Producto prod) {
        //verificar si es un codigo existente
        String codigo =  prod.getCodigo();
        mProductoReference.child(codigo).setValue(prod);
          return codigo;
   }

   //eliminar
    public void eliminar() {
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference ref = database.getReference();
        EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        mProductoReference.child(editTextNombre.getText().toString()).removeValue();
        //String codigo = "Producto" +editTextNombre.getText().toString().substring(0, editTextNombre.getText().toString().indexOf(' ', 0)-1);

        //ref.child("Ropa_Deportiva").child(codigo).removeValue();

    }
*/
    //private String GrabarNuevo(Producto prod){
       // String codigo = mProductoReference.push().getKey();
       // mAcademicoReference.child(coleccionProductos).child(codigo).setValue(prod);
        //return codigo;
   // }

    public void remove(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        EditText editTextColor = (EditText) findViewById(R.id.editTextColor);
        EditText editTextCantidad = (EditText) findViewById(R.id.editTextCantidad);
        EditText editTextPrecio = (EditText) findViewById(R.id.editTextPrecio);
        EditText editTextSexo = (EditText) findViewById(R.id.editTextSexo);
        EditText editTextTalla = (EditText) findViewById(R.id.editTextTalla);
        EditText editTextTela = (EditText) findViewById(R.id.editTextTela);

        String cadena = "Prdt" + editTextNombre.getText().toString().substring(0, editTextNombre.getText().toString().indexOf(' ', 0)-1);
        mAcademicoReference.child(coleccionProductos).child(cadena).removeValue();

      }
    }
