package fr.eseo.dis.pavlovpi.somanager.data.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import fr.eseo.dis.pavlovpi.somanager.EvaluationActivity;
import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.EvaluationItem;
import fr.eseo.dis.pavlovpi.somanager.data.NotationItem;

public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.EvaluationViewHolder>{

    private static final String TAG = EvaluationAdapter.class.getSimpleName();

    private EvaluationActivity activity;
    private ArrayList<EvaluationItem> mEvaluationItem;

    public EvaluationAdapter(EvaluationActivity activity, ArrayList<EvaluationItem> evaluationItems) {
        this.activity = activity;
        this.mEvaluationItem = evaluationItems;
    }

    @NonNull
    @Override
    public EvaluationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluation_card_layout, parent, false);
        return new EvaluationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final EvaluationViewHolder holder, int position) {
        final EvaluationItem currentItem = mEvaluationItem.get(position);

        String notationForename = currentItem.getForename();
        String notationSurname = currentItem.getSurname();

        holder.mTextViewSurname.setText(notationSurname);
        holder.mTextViewForename.setText(notationForename);

    }

    @Override
    public int getItemCount() {
        return this.mEvaluationItem.size();
    }

    class EvaluationViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        public TextView mTextViewSurname;
        public TextView mTextViewForename;
        public EditText mTextViewNote;

        public EvaluationViewHolder (View itemView) {
            super(itemView);
            this.view = itemView;
            mTextViewSurname = itemView.findViewById(R.id.evaluation_surname);
            mTextViewForename = itemView.findViewById(R.id.evaluation_forename);
            mTextViewNote = itemView.findViewById(R.id.evaluation_note);
        }
    }

}
