/**
 * @file ProjectMemberAdapter.java
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

import fr.eseo.dis.pavlovpi.somanager.DetailsActivity;
import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.ProjectMemberItem;

public class ProjectMemberAdapter extends RecyclerView.Adapter<ProjectMemberAdapter.ProjectMemberViewHolder> {

    private DetailsActivity activity;
    private List<Integer> expandedPositions;
    private ArrayList<ProjectMemberItem> mProjectMemberList;

    public ProjectMemberAdapter(DetailsActivity myDetailsActivity, ArrayList<ProjectMemberItem> projectMemberList){
        this.expandedPositions = new ArrayList<>();
        this.activity = myDetailsActivity;
        this.mProjectMemberList = projectMemberList;
    }


    @NonNull
    @Override
    public ProjectMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_member_card, parent, false);
        return new ProjectMemberAdapter.ProjectMemberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectMemberViewHolder holder, int position) {
        final ProjectMemberItem currentItem = mProjectMemberList.get(position);

        String projectMemberName = currentItem.getProjectMemberName();

        holder.mTextViewProjectMemberName.setText(projectMemberName);
    }

    @Override
    public int getItemCount() {
        return this.mProjectMemberList.size();
    }

    class ProjectMemberViewHolder extends RecyclerView.ViewHolder{

        private final View view;

        public TextView mTextViewProjectMemberName;

        public ProjectMemberViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mTextViewProjectMemberName = itemView.findViewById(R.id.member_name_card_view);
        }

    }

}
