package com.example.homerental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.homerental.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    Context context;
    ArrayList<DocumentSnapshot> userList;

    public UsersAdapter(Context context, ArrayList<DocumentSnapshot> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        DocumentSnapshot userSnapshot = userList.get(position);
        String username = userSnapshot.getString("Username");
        String email = userSnapshot.getString("Email");
        boolean isBlocked = Boolean.TRUE.equals(userSnapshot.getBoolean("isBlocked"));

        // Afficher les données de l'utilisateur dans les vues correspondantes
        holder.usernameTextView.setText(username);
        holder.emailTextView.setText(email);
        holder.statusTextView.setText(isBlocked? "Blocked" : "Active");

        // Gérer les clics sur les boutons
        holder.blockButton.setOnClickListener(v -> {

            // Bloquer l'utilisateur
            userSnapshot.getReference().update("isBlocked", true);
            holder.statusTextView.setText("Blocked");

        });

        holder.unblockButton.setOnClickListener(v -> {
            // Débloquer l'utilisateur
            userSnapshot.getReference().update("isBlocked", false);
            holder.statusTextView.setText("Active");

        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, emailTextView, statusTextView;
        Button blockButton, unblockButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            blockButton = itemView.findViewById(R.id.blockButton);
            unblockButton = itemView.findViewById(R.id.unblockButton);
        }


    }
}