/**
 * @file DetailAdapter.java
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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pavlovpi.somanager.DetailsActivity;
import fr.eseo.dis.pavlovpi.somanager.MyJuryActivity;
import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.DetailItem;
import fr.eseo.dis.pavlovpi.somanager.data.JuryItem;
import fr.eseo.dis.pavlovpi.somanager.data.ProjectItem;

public class DetailAdapter {

    private Context mContext;
    private DetailsActivity activity;
    private List<Integer> expandedPositions;
    private ArrayList<DetailItem> mDetailList;

    public DetailAdapter(DetailsActivity detailActivity, ArrayList<DetailItem> detailList){
        this.expandedPositions = new ArrayList<>();
        this.activity = detailActivity;
        this.mDetailList = detailList;
    }

    @NonNull
    public DetailAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_details, parent, false);
        return new DetailAdapter.DetailViewHolder(v);
    }

    public int getItemCount() {
        return this.mDetailList.size();
    }

    public void onBindViewHolder(@NonNull DetailAdapter.DetailViewHolder holder, final int position) {
        final DetailItem currentItem = mDetailList.get(position);

        String idProject = currentItem.getIdProject();
        String titleProject = currentItem.getTitleProject();
        String descriptionProject = currentItem.getDescriptionProject();
        String supervisor = currentItem.getSupervisor();

        holder.mTextViewIdProject.setText("Project " + idProject);
        holder.mTextViewTitleProject.setText(titleProject);
        holder.mTextViewDescriptionProject.setText("Resume : " + descriptionProject);
        holder.mTextViewSupervisor.setText("Supervisor :" + supervisor);
    }

    class DetailViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        public TextView mTextViewIdProject;
        public TextView mTextViewTitleProject;
        public TextView mTextViewDescriptionProject;
        public TextView mTextViewSupervisor;

        public DetailViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mTextViewIdProject = itemView.findViewById(R.id.jury_id_view);
            mTextViewTitleProject = itemView.findViewById(R.id.jury_info);
            mTextViewDescriptionProject = itemView.findViewById(R.id.tv_details_resume);
        }



    }
}
