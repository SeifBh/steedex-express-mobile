package tn.seif.stedeex.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.loginrestapi.R;

import java.util.List;

import tn.seif.stedeex.DemandeActivity;
import tn.seif.stedeex.Models.Demande;

/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Demande> mData ;
    RequestOptions option;


    public RecyclerViewAdapter(Context mContext, List<Demande> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.demandes_list_row,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DemandeActivity.class);
                i.putExtra("anime_id",mData.get(viewHolder.getAdapterPosition()).getId()+"");
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getTitre());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getDescription_produit());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getEtat());
                i.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getType());
                i.putExtra("anime_nb_episode",mData.get(viewHolder.getAdapterPosition()).getTitre());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getEtat());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getTitre());

                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.dm_titre.setText(mData.get(position).getTitre());
        holder.dm_etat.setText(mData.get(position).getType());
        holder.dm_nom_prenom_rcpt.setText(mData.get(position).getNom_prenom_recept()+"");
        holder.dm_lieu.setText(mData.get(position).getLieu());
        holder.dm_id.setText(mData.get(position).getId()+"");

        if (mData.get(position).getEtat().contains("Valide"))
        {
           // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,1));

            holder.img_thumbnail.setBackgroundColor(Color.rgb(75, 181, 67));

        }
        else if (mData.get(position).getEtat().contains("EnTraitement"))
        {
            holder.img_thumbnail.setBackgroundColor(Color.rgb(0, 191, 255));
           // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(2,3));


        }
        else if (mData.get(position).getEtat().contains("EnCours"))
        {
            holder.img_thumbnail.setBackgroundColor(Color.rgb(255, 165, 0));
            //holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(2,3));


        }
        else if (mData.get(position).getEtat().contains("Cloture"))
        {
           // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,2).toUpperCase());

            holder.img_thumbnail.setBackgroundColor(Color.rgb(178, 34, 34));

        }
        else if (mData.get(position).getEtat().contains("Retour"))
        {
            //holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,1));

            holder.img_thumbnail.setBackgroundColor(Color.rgb(127,255,212));

        }
        else{
           // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,1));

        }

        holder.dm_myImageViewText.setText(mData.get(position).getEtat());


//        holder.dm_type.setText(mData.get(position).getType());

        // Load Image from the internet and set it into Imageview using Glide




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dm_id ;
        TextView dm_titre ;
        TextView dm_etat ;
        TextView dm_type ;
        TextView dm_nom_prenom_rcpt ;
        TextView dm_lieu ;
        TextView dm_note ;
        TextView dm_montant ;
        TextView dm_telephone ;
        TextView dm_demanded ;
        TextView dm_myImageViewText ;
        ImageView img_thumbnail ;

        LinearLayout view_container;





        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            dm_titre = itemView.findViewById(R.id.demande_titre);
            dm_etat = itemView.findViewById(R.id.etat);
            dm_lieu = itemView.findViewById(R.id.lieu);
            dm_nom_prenom_rcpt = itemView.findViewById(R.id.nom_prenom_recpt);
            dm_id = itemView.findViewById(R.id.demande_id);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            dm_myImageViewText = itemView.findViewById(R.id.myImageViewText);


        }
    }

}
