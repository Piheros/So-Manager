/**
 * @file NotationAdapter.java
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fr.eseo.dis.pavlovpi.somanager.NotationActivity;
import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.NotationItem;

public class NotationAdapter extends RecyclerView.Adapter<NotationAdapter.NotationViewHolder>{

    private NotationActivity activity;
    private ArrayList<NotationItem> mNotationList;

    public NotationAdapter(NotationActivity activity, ArrayList<NotationItem> notationList) {
        this.activity = activity;
        this.mNotationList = notationList;
    }

    @NonNull
    @Override
    public NotationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notation_card_layout, parent, false);
        return new NotationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotationViewHolder holder, int position) {
        final NotationItem currentItem = mNotationList.get(position);

        String notationForename = currentItem.getNotationForename();
        String notationSurname = currentItem.getNotationSurname();
        String notationNote = currentItem.getNotationNote();
        String notationNoteAverage = currentItem.getNoteAverage();

        holder.mTextViewSurname.setText(notationSurname);
        holder.mTextViewForename.setText(notationForename);
        holder.mTextViewNote.setText(notationNote);
        holder.mTextViewNoteAverage.setText(notationNoteAverage);
    }

    @Override
    public int getItemCount() {
        return this.mNotationList.size();
    }

    class NotationViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        public TextView mTextViewSurname;
        public TextView mTextViewForename;
        public TextView mTextViewNote;
        public TextView mTextViewNoteAverage;

        public NotationViewHolder (View itemView) {
            super(itemView);
            this.view = itemView;
            mTextViewSurname = itemView.findViewById(R.id.notation_surename);
            mTextViewForename = itemView.findViewById(R.id.notation_forename);
            mTextViewNote = itemView.findViewById(R.id.tv_notation_note);
            mTextViewNoteAverage = itemView.findViewById(R.id.notation_average_view);
        }
    }

}
