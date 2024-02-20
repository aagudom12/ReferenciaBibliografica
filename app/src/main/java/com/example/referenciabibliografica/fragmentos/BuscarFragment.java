package com.example.referenciabibliografica.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.referenciabibliografica.Adaptador;
import com.example.referenciabibliografica.Elemento;
import com.example.referenciabibliografica.MainActivity;
import com.example.referenciabibliografica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    private EditText editText;
    private ListView listView;
    private Adaptador adaptador;
    private List<Elemento> listaLibros;



    public BuscarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShortsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscarFragment newInstance(String param1, String param2) {
        BuscarFragment fragment = new BuscarFragment();
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
        view = inflater.inflate(R.layout.fragment_buscar, container, false);

        // Obtener la lista de libros de la MainActivity
        listaLibros = MainActivity.getLibros();

        editText = view.findViewById(R.id.search_edit_text);
        // Obtén una referencia al RecyclerView desde tu layout
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        // Crea una instancia de tu adaptador Adaptador
        Adaptador adaptador = new Adaptador(listaLibros, requireActivity());
        // Establece el adaptador en el RecyclerView
        recyclerView.setAdapter(adaptador);

        // Agregar un listener al EditText para detectar cambios en el texto y filtrar la lista
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = charSequence.toString().toLowerCase();
                ArrayList<Elemento> filteredList = new ArrayList<>();

                for (Elemento elemento : listaLibros) {
                    if (elemento.getTitulo().toLowerCase().contains(searchText)) {
                        filteredList.add(elemento);
                    }
                }

                adaptador.filterList(searchText);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return view;
    }
}