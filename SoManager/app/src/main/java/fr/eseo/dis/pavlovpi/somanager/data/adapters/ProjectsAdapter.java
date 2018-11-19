/**
 * @file ProjectAdapter.java
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
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.ConnectionActivity;
import fr.eseo.dis.pavlovpi.somanager.data.ProjectItem;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private Context mContext;
    private ConnectionActivity activity;
    private List<Integer> expandedPositions;
    private ArrayList<ProjectItem> mProjectList;

    public ProjectsAdapter(Context context, ArrayList<ProjectItem> projectList){
        this.expandedPositions = new ArrayList<>();
        this.mContext = context;
        this.mProjectList = projectList;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.projects_card_view, parent, false);
        //CardView filmCardView = (CardView)v;
        //filmCardView.setCardElevation(3 * ConnectionActivity.NEW_CARD_COUNTER++);
        return new ProjectsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, final int position) {
        final ProjectItem currentItem = mProjectList.get(position);

        String idProject = currentItem.getIdProject();
        String titleProject = currentItem.getTitleProject();
        String descriptionProject = currentItem.getDescriptionProject();

        holder.mTextViewIdProject.setText("Project " + idProject);
        holder.mTextViewTitleProject.setText(titleProject);
        holder.mTextViewDescriptionProject.setText("Resume : " + descriptionProject);

        /*
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView resume = (TextView) v.findViewById(R.id.tv_film_resume);
                TextView resumeLabel = (TextView) v.findViewById(R.id.tv_film_resume_label);
                if (expandedPositions.contains(position)) {
                    resume.setVisibility(View.GONE);
                    resumeLabel.setVisibility(View.GONE);
                    expandedPositions.remove(new Integer(position));
                } else {
                    resume.setVisibility(View.VISIBLE);
                    resumeLabel.setVisibility(View.VISIBLE);
                    expandedPositions.add(position);
                }
                return true;
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return this.mProjectList.size();
    }

    class ProjectsViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        public TextView mTextViewIdProject;
        public TextView mTextViewTitleProject;
        public TextView mTextViewDescriptionProject;

        public ProjectsViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mTextViewIdProject = itemView.findViewById(R.id.project_id_view);
            mTextViewTitleProject = itemView.findViewById(R.id.project_title_view);
            mTextViewDescriptionProject = itemView.findViewById(R.id.project_description_view);
        }

    }

}
