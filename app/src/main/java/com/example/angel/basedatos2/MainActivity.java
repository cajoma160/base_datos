package com.example.angel.basedatos2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et_dni);
        et2 = (EditText) findViewById(R.id.et_nombre);
        et3 = (EditText) findViewById(R.id.et_numero);

    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String numero = et3.getText().toString();

        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("numero", numero);

        bd.insert("contactos", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");

        Toast.makeText(this, "Contacto Guardado",
                Toast.LENGTH_SHORT).show();

    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        String dni = et1.getText().toString();

        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre,numero  from contactos where dni=" + dni, null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));

        } else
            Toast.makeText(this, "No existe el contacto" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        int cant = bd.delete("contactos", "dni=" + dni, null); // (votantes es la nombre de la tabla, condición)
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");

        if (cant == 1)
            Toast.makeText(this, "Se borró el contacto",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe el contacto",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String numero = et3.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("numero", numero);

        int cant = bd.update("contactos", registro, "dni=" + dni, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe el contacto",
                    Toast.LENGTH_SHORT).show();




    }
}
