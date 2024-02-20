package com.example.referenciabibliografica.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.referenciabibliografica.AdaptadorDiccionario;
import com.example.referenciabibliografica.Palabra;
import com.example.referenciabibliografica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VocabularioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VocabularioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private AdaptadorDiccionario adapter;
    private List<Palabra> wordList;
    public VocabularioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VocabularioFragment newInstance(String param1, String param2) {
        VocabularioFragment fragment = new VocabularioFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocabulario, container, false);

        // Inicializar la lista de palabras
        wordList = new ArrayList<>();
        wordList.add(new Palabra("Anticonstitucionalmente", "De manera contraria a lo establecido en la constitución."));
        wordList.add(new Palabra("Electroencefalografía", "Registro gráfico de la actividad eléctrica del cerebro."));
        wordList.add(new Palabra("Desoxirribonucleico", "Ácido nucleico que contiene la información genética de los organismos."));
        wordList.add(new Palabra("Otorrinolaringólogo", "Especialista en el estudio y tratamiento de las enfermedades del oído, nariz y garganta."));

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaptadorDiccionario(wordList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}