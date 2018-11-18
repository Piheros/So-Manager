/**
 * @file JuryAdapter.java
 * @version 0.2
 * @author Mehdi Bouafia & Pierre Pavlovic
 * @date 18/11/2018
 *
 * @section License
 *
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 *
 * Copyright (C) 2018  Mehdi Bouafia & Pierre Pavlovic
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */
package fr.eseo.dis.pavlovpi.somanager.data.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pavlovpi.somanager.MyJuryActivity;
import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.JuryItem;

public class JuryAdapter extends RecyclerView.Adapter<JuryAdapter.JuryViewHolder> {

    private MyJuryActivity activity;
    private List<Integer> expandedPositions;
    private ArrayList<JuryItem> mJuryList;

    public JuryAdapter(MyJuryActivity myJuryActivity, ArrayList<JuryItem> juryList){
        this.expandedPositions = new ArrayList<>();
        this.activity = myJuryActivity;
        this.mJuryList = juryList;
    }

    @NonNull
    @Override
    public JuryAdapter.JuryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.jury_card_view, parent, false);
        return new JuryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JuryViewHolder holder, final int position) {
        final JuryItem currentItem = mJuryList.get(position);

        String idJury = currentItem.getIdJury();
        String dateJury = currentItem.getDate();
        final String infoJury = currentItem.getInfo();
        final String supervisorName = currentItem.getSupervisorName();
        final String students = currentItem.getStudents();
        final String description = currentItem.getJuryProjectDescription();
        final String projectId = currentItem.getJuryProjectId();

        holder.mTextViewIdJury.setText("Jury " + idJury);
        holder.mTextViewDateJury.setText(dateJury);
        holder.mTextViewDInfoJury.setText(infoJury);
        holder.mTextViewSupervisorNameJury.setText("superviseur : " + supervisorName);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO renvoi vers l'activity detail
                activity.clickJuryCard(infoJury,supervisorName,students, description, projectId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mJuryList.size();
    }

    class JuryViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        public TextView mTextViewIdJury;
        public TextView mTextViewDateJury;
        public TextView mTextViewDInfoJury;
        public TextView mTextViewSupervisorNameJury;

        public JuryViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mTextViewIdJury = itemView.findViewById(R.id.jury_id_view);
            mTextViewDateJury = itemView.findViewById(R.id.jury_date);
            mTextViewDInfoJury = itemView.findViewById(R.id.jury_info);
            mTextViewSupervisorNameJury = itemView.findViewById(R.id.jury_supervisor_name);
        }

    }

}
