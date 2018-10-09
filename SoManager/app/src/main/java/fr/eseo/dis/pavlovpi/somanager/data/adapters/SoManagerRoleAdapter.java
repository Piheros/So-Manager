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

import fr.eseo.dis.pavlovpi.somanager.R;
import fr.eseo.dis.pavlovpi.somanager.data.RoleAvecArtiste;

public class SoManagerRoleAdapter extends RecyclerView.Adapter<SoManagerRoleAdapter.ActeurViewHolder>{

    private final Context context;

    private List<RoleAvecArtiste> roles;

    public SoManagerRoleAdapter(Context context, int idFilm){
        this.context = context;
        roles = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    @NonNull
    @Override
    public ActeurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View acteurView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roles_card_layout, parent, false);
        return new ActeurViewHolder(acteurView);
    }

    @Override
    public void onBindViewHolder(@NonNull SoManagerRoleAdapter.ActeurViewHolder holder, int position) {
        RoleAvecArtiste roleArtiste = roles.get(position);
        holder.acteurNom.setText(roleArtiste.artistes.get(0).getPrenom()+" "+roleArtiste.artistes.get(0).getNom());
        holder.acteurRole.setText(roleArtiste.role.getNomRole());
    }

    public void setRoles(List<RoleAvecArtiste> roles){
        this.roles = roles;
    }

    class ActeurViewHolder extends RecyclerView.ViewHolder {

        private final TextView acteurNom;
        private final TextView acteurRole;

        public ActeurViewHolder(View view){
            super(view);
            acteurNom = view.findViewById(R.id.tv_role_acteur);
            acteurRole = view.findViewById(R.id.tv_role_titre);
        }
    }
}
