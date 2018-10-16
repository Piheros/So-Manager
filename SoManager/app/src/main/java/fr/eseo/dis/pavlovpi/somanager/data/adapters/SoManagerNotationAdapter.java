package fr.eseo.dis.pavlovpi.somanager.data.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pavlovpi.somanager.SoManagerNotationActivity;
import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.NotationAvecInternaute;

public class SoManagerNotationAdapter extends RecyclerView.Adapter<SoManagerNotationAdapter.NotationViewHolder> {

    private SoManagerNotationActivity activity;

    private List<NotationAvecInternaute> notations;

    private int minNote;

    private int maxNote;

    private float avgNote;

    public SoManagerNotationAdapter(SoManagerNotationActivity activity){
        this.activity = activity;
        setNotations(new ArrayList<NotationAvecInternaute>());
    }

    public void setNotations(List<NotationAvecInternaute> notations){
        this.notations = notations;
        if((notations != null) && (notations.size() > 0)){
            minNote = notations.get(0).notation.getNote();
            maxNote = minNote;
            avgNote = 0;
            int nbNotes = notations.size();
            for(NotationAvecInternaute notation : notations){
                if(notation.notation.getNote() < minNote){
                    minNote = notation.notation.getNote();
                }
                if(notation.notation.getNote() > maxNote){
                    maxNote = notation.notation.getNote();
                }
                avgNote += notation.notation.getNote();

            }
            avgNote = avgNote / (float)nbNotes;
        }
    }


    @NonNull
    @Override
    public SoManagerNotationAdapter.NotationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View notationView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notation_card_layout, parent, false);
        return new NotationViewHolder(notationView);

    }

    @Override
    public void onBindViewHolder(@NonNull SoManagerNotationAdapter.NotationViewHolder holder, int position) {
        final NotationAvecInternaute notation = notations.get(position);
        holder.prenom.setText(notation.internautes.get(0).getPrenom());
        holder.nom.setText(notation.internautes.get(0).getNom());
        holder.note.setText(String.valueOf(notation.notation.getNote()));
        if (notation.notation.getNote() == minNote) {
            holder.note
                    .setTextColor(ResourcesCompat.getColor(activity.getResources(), R.color.min_note, null));
        } else if (notation.notation.getNote() == maxNote) {
            holder.note
                    .setTextColor(ResourcesCompat.getColor(activity.getResources(), R.color.max_note, null));
        } else {
            holder.note.setTextColor(
                    ResourcesCompat.getColor(activity.getResources(), R.color.colorPrimaryDark, null));
        }
        if (notation.notation.getNote() < avgNote) {
            holder.note.setBackgroundColor(
                    ResourcesCompat.getColor(activity.getResources(), R.color.low_note, null));
        }
        if (notation.notation.getNote() > avgNote) {
            holder.note.setBackgroundColor(
                    ResourcesCompat.getColor(activity.getResources(), R.color.high_note, null));
        }

    }

    @Override
    public int getItemCount() {
        return (notations!=null)?notations.size():0;
    }


    class NotationViewHolder extends RecyclerView.ViewHolder {

        private final TextView prenom;

        private final TextView nom;

        private final TextView note;

        public NotationViewHolder(View view){
            super(view);
            prenom = view.findViewById(R.id.tv_notation_prenom);
            nom = view.findViewById(R.id.tv_notation_nom);
            note = view.findViewById(R.id.tv_notation_note);

        }
    }
}
