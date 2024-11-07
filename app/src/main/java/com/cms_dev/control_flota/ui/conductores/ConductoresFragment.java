package com.cms_dev.control_flota.ui.conductores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.cms_dev.control_flota.Database;
import com.cms_dev.control_flota.R;

import java.util.ArrayList;

public class ConductoresFragment extends Fragment {
    private Database database;
    private EditText ingresarConductor, ingresarVehiculo, editarConductor, editarVehiculo;
    private Button btnAgregar, btnEditar, btnEliminar;
    private ListView listarConductor;
    private ArrayList<String> conductores;
    private ArrayAdapter<String> adapter;
    private String seleccionarConductor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento de Conductores
        View root = inflater.inflate(R.layout.fragment_conductores, container, false);

        // Configuración los elementos de la interfaz
        database = new Database(getActivity());
        ingresarVehiculo = root.findViewById(R.id.vehiculo);
        ingresarConductor = root.findViewById(R.id.editTextTask);
        editarConductor = root.findViewById(R.id.editTextEditTask);
        editarVehiculo = root.findViewById(R.id.edit_vehiculo);
        btnAgregar = root.findViewById(R.id.buttonAdd1);
        btnEditar = root.findViewById(R.id.buttonEdit);
        btnEliminar = root.findViewById(R.id.buttonDelete);
        listarConductor = root.findViewById(R.id.listViewTasks);

        // Configuración del ListView y adaptador
        conductores = database.getAllConductor();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, conductores);
        listarConductor.setAdapter(adapter);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conductor = ingresarConductor.getText().toString();
                String vehiculo = ingresarVehiculo.getText().toString();

                // Verificar que los campos no estén vacíos
                if (!conductor.isEmpty() && !vehiculo.isEmpty()) {
                    boolean insertado = database.insertConductor(conductor, vehiculo);
                    if (insertado) {
                        actualizarListadoConductores();
                        ingresarConductor.setText("");
                        ingresarVehiculo.setText("");
                        Toast.makeText(getActivity(), "Conductor agregado exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Error al agregar conductor", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Por favor, ingrese ambos valores", Toast.LENGTH_SHORT).show();
                }
            }
        });


        listarConductor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String seleccionado = conductores.get(position);
                String[] partes = seleccionado.split(" - ");
                String conductorSeleccionado = partes[0];
                String vehiculoSeleccionado = partes[1];

                seleccionarConductor = conductorSeleccionado;
                editarConductor.setText(conductorSeleccionado);
                editarVehiculo.setText(vehiculoSeleccionado);


                editarConductor.setVisibility(View.VISIBLE);
                editarVehiculo.setVisibility(View.VISIBLE);
                btnEditar.setVisibility(View.VISIBLE);
                btnEliminar.setVisibility(View.VISIBLE);
            }
        });



        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoConductor = editarConductor.getText().toString();
                String nuevoVehiculo = editarVehiculo.getText().toString();

                // Verificar que tanto el conductor como el vehículo no estén vacíos
                if (!nuevoConductor.isEmpty() && !nuevoVehiculo.isEmpty()) {
                    boolean actualizado = database.updateConductor(seleccionarConductor, nuevoConductor, nuevoVehiculo);
                    if (actualizado) {
                        actualizarListadoConductores();
                        LimpiarCampos();
                    } else {
                        Toast.makeText(getActivity(), "Error al actualizar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Por favor, ingrese ambos valores", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteConductor(seleccionarConductor);
                actualizarListadoConductores();
                LimpiarCampos();
            }
        });

        return root;
    }

    public void actualizarListadoConductores() {
        conductores.clear();  // Limpiar la lista actual
        conductores.addAll(database.getAllConductor());
        adapter.notifyDataSetChanged();
    }

    public void LimpiarCampos() {
        editarConductor.setText("");
        editarVehiculo.setText("");
        editarConductor.setVisibility(View.GONE);
        editarVehiculo.setVisibility(View.GONE);
        btnEditar.setVisibility(View.GONE);
        btnEliminar.setVisibility(View.GONE);
    }
}
