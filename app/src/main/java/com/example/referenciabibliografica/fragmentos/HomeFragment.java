package com.example.referenciabibliografica.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.referenciabibliografica.Adaptador;
import com.example.referenciabibliografica.Elemento;
import com.example.referenciabibliografica.MainActivity;
import com.example.referenciabibliografica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemLongClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    List<Elemento> elementos;

    public HomeFragment() {
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
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
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

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}