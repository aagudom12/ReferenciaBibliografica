package com.example.referenciabibliografica.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.referenciabibliografica.Adaptador;
import com.example.referenciabibliografica.Elemento;
import com.example.referenciabibliografica.MainActivity;
import com.example.referenciabibliografica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BibliotecaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BibliotecaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;

    public BibliotecaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubscriptionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BibliotecaFragment newInstance(String param1, String param2) {
        BibliotecaFragment fragment = new BibliotecaFragment();
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
        view = inflater.inflate(R.layout.fragment_biblioteca, container, false);

        // Recuperar información de favoritos de SharedPreferences
        List<Elemento> favoritos = obtenerFavoritos();

        // Mostrar los favoritos en el RecyclerView
        initFavoritos(view, favoritos);

        return view;
    }

    private List<Elemento> obtenerFavoritos() {
        List<Elemento> favoritos = new ArrayList<>();

        // Recuperar información de la lista centralizada en MainActivity
        List<Elemento> contactos = MainActivity.getLibros();

        // Verificar si la lista de contactos no es nula antes de iterar
        if (contactos != null) {
            // Iterar a través de los contactos y agregar a la lista de favoritos si es favorito
            for (Elemento contacto : contactos) {
                // Verificar si el contacto es favorito
                if (contacto.isFavorito()) {
                    favoritos.add(contacto);
                }
            }
        }

        return favoritos;
    }

    private void initFavoritos(View view, List<Elemento> favoritos) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFavoritos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Puedes usar el mismo adaptador que usaste en el primer fragmento
        Adaptador listaAdaptador = new Adaptador(favoritos, requireContext());
        recyclerView.setAdapter(listaAdaptador);
    }
}