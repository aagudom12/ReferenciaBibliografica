package com.example.referenciabibliografica.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.referenciabibliografica.Adaptador;
import com.example.referenciabibliografica.Elemento;
import com.example.referenciabibliografica.MainActivity;
import com.example.referenciabibliografica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment implements AdapterView.OnItemLongClickListener, AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    List<Elemento> elementos;

    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);

        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.opciones_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        iniciarLista();
        return view;
    }

    public void iniciarLista(){
        elementos = new ArrayList<>();
        elementos.add(new Elemento("#775447", "El alquimista", false));
        elementos.add(new Elemento("#687d8b", "Cien años de soledad", false));
        elementos.add(new Elemento("#83a9f4", "1984", false));
        elementos.add(new Elemento("#f44336", "Harry Potter", false));
        elementos.add(new Elemento("#009688", "Orgullo y prejuicio", false));
        elementos.add(new Elemento("#ff9800", "El nombre del viento", false));
        elementos.add(new Elemento("#4caf50", "Crónica de una muerte anunciada", false));
        elementos.add(new Elemento("#673ab7", "Don Quijote de la Mancha", false));
        elementos.add(new Elemento("#e91e63", "El señor de los anillos", false));
        elementos.add(new Elemento("#ffcc00", "Cien años de soledad", false));

        MainActivity.setLibros(elementos);

        Adaptador listaAdaptador = new Adaptador(elementos,requireContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listaAdaptador);
    }

    public void mostrarNoticias(){
        elementos = new ArrayList<>();
        elementos.add(new Elemento("#775447", "Nuevo tratado comercial entra en vigor", false));
        elementos.add(new Elemento("#687d8b", "Científicos descubren posible cura para enfermedad", false));
        elementos.add(new Elemento("#83a9f4", "Protestas estudiantiles exigen reforma educativa", false));
        elementos.add(new Elemento("#f44336", "Empresa lanza producto revolucionario", false));
        elementos.add(new Elemento("#009688", "Terremoto sacude región costera", false));
        elementos.add(new Elemento("#ff9800", "Acuerdo climático alcanza un hito importante", false));
        elementos.add(new Elemento("#4caf50", "Artista famoso dona a obras benéficas", false));
        elementos.add(new Elemento("#673ab7", "La tensión aumenta en la disputa territorial", false));
        elementos.add(new Elemento("#e91e63", "Estudio revela efectos negativos de redes sociales", false));
        elementos.add(new Elemento("#ffcc00", "Inflación causa preocupación en la economía global", false));

        MainActivity.setLibros(elementos);

        Adaptador listaAdaptador = new Adaptador(elementos,requireContext());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(listaAdaptador);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String opcionSeleccionada = parent.getItemAtPosition(position).toString();
        if (opcionSeleccionada.equals("Libros")) {
            // Mostrar lista de libros
            iniciarLista();
        } else if (opcionSeleccionada.equals("Noticias")) {
            // Mostrar lista de noticias
            mostrarNoticias(); // Método que debes implementar para mostrar noticias en el RecyclerView
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}