package fr.eisti.inem.pingpong.ui.game;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.game.Game;
import fr.eisti.inem.pingpong.engine.user.User;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PingPongTable.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PingPongTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PingPongTable extends Fragment {

    public static Map<Game.PlayerPosition, User> getTablePlayers() {
        return tablePlayers;
    }

    public static void setTablePlayers(Map<Game.PlayerPosition, User> tablePlayers) {
        PingPongTable.tablePlayers = tablePlayers;
    }

    static Map<Game.PlayerPosition, User> tablePlayers;

    private OnFragmentInteractionListener mListener;

    public PingPongTable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param players HashMap of players around the table
     * @return A new instance of fragment PingPongTable.
     */
    // TODO: Rename and change types and number of parameters
    public static PingPongTable newInstance(Map<Game.PlayerPosition,User> players) {
        PingPongTable fragment = new PingPongTable();
        Bundle args = new Bundle();
        tablePlayers = players;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ping_pong_table, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
