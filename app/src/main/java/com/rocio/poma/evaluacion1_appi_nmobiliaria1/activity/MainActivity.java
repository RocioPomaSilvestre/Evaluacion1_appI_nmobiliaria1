package com.rocio.poma.evaluacion1_appi_nmobiliaria1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rocio.poma.evaluacion1_appi_nmobiliaria1.FragmentActivity;
import com.rocio.poma.evaluacion1_appi_nmobiliaria1.FragmentAdicionar;
import com.rocio.poma.evaluacion1_appi_nmobiliaria1.R;
import com.rocio.poma.evaluacion1_appi_nmobiliaria1.adaptador.RecyclerAdapter;
import com.rocio.poma.evaluacion1_appi_nmobiliaria1.model.ItemList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();

        FragmentAdicionar fragmentAdd = new FragmentAdicionar();

        FloatingActionButton btnadd=findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorPrincipal,fragmentAdd);
                transaction.commit();
            }
        });

        //SALIR
        BottomNavigationView.OnNavigationItemSelectedListener listener= new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem itemSel) {

                if (itemSel.getItemId()==R.id.itemSalir){
                    //mostrar fragment inicio
                    System.exit (0);
                    return true;
                }
                return false;
            }
        };
        BottomNavigationView navigationView=findViewById(R.id.navBotton);
        navigationView.setOnNavigationItemSelectedListener(listener);
        //

 /*
        FloatingActionButton btnadd=findViewById(R.id.btnAdd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FragmentAdicionar.class);

                startActivity(i);
            }
        });
*/


    }



    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Inmobiliaria del Momento", "inmobiliario refiere a aquello perteneciente o relativo a las cosas inmuebles. Un inmueble, por su parte, es un bien que se encuentra unido a un terreno de modo inseparable", R.drawable.uno));
        itemLists.add(new ItemList("Inmobiliaria Bienes y Raises", "Una inmobiliaria es un negocio dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc).", R.drawable.dos));
        itemLists.add(new ItemList("Inmobiliaria Pentium", "Dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc)", R.drawable.tres));
        itemLists.add(new ItemList("Inmobiliaria Omega", "Dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc)", R.drawable.cuatro));
        itemLists.add(new ItemList("Inmobiliaria Dorada", "Dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc).", R.drawable.cinco));
        itemLists.add(new ItemList("Inmobiliaria Vivela", "Dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc)", R.drawable.seis));
        itemLists.add(new ItemList("Inmobiliaria Real", "Dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc)", R.drawable.siete));
        itemLists.add(new ItemList("Inmobiliaria House", "Dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc)", R.drawable.ocho));
        itemLists.add(new ItemList("Inmobiliaria Vision Urbana", "dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc)", R.drawable.cinco));
        itemLists.add(new ItemList("Inmobiliaria Morada", "Dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc)", R.drawable.uno));
        itemLists.add(new ItemList("Inmobiliaria Creativa", "dedicado a la compra y venta de inmuebles (casas, locales comerciales, mansiones, fincas, etc).", R.drawable.seis));


        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        rvLista.setLayoutManager(layoutManager);
        return itemLists;
    }

    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(this, FragmentActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
