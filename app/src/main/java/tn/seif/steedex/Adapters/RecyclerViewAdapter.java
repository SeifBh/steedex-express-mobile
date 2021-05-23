package tn.seif.steedex.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import tn.seif.steedex.DemandeActivity;
import tn.seif.steedex.MainActivityDemandes;
import tn.seif.steedex.Models.Demande;
import tn.seif.steedex.Models.User;
import tn.seif.steedex.Models.Anime;
import tn.seif.steedex.R;

/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>  {

    private Context mContext ;
    private List<Demande> mData ;

    private List<Demande> mData2  =  new ArrayList<Demande>();



    ArrayList<Demande> demande1;
    ArrayList<Demande> listDemande1;
    private EditText search;
    private RecyclerView list;

    private ArrayList<Demande> mExampleList;


    RequestOptions option;



    public RecyclerViewAdapter(Context mContext, ArrayList<Demande> mData) {
        this.mContext = mContext;
        this.mData = mData;

        mExampleList  = mData;
        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }


    public RecyclerViewAdapter(ArrayList<Demande> exampleList) {
        mExampleList = exampleList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.demandes_list_row,
                parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);













        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("9bal trye","true");

                try
                {
                    Log.d("woset try","true");

                    Intent i = new Intent(mContext, DemandeActivity.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



                    i.putExtra("demande_id",mExampleList.get(viewHolder.getAdapterPosition()).getId()+"");
                    i.putExtra("demande_titre",mExampleList.get(viewHolder.getAdapterPosition()).getTitre());
                    i.putExtra("demande_quoi",mExampleList.get(viewHolder.getAdapterPosition()).getQuoi());
                    i.putExtra("demande_description",mExampleList.get(viewHolder.getAdapterPosition()).getDescription_produit());
                    i.putExtra("demande_etat",mExampleList.get(viewHolder.getAdapterPosition()).getEtat());
                    i.putExtra("demande_type",mExampleList.get(viewHolder.getAdapterPosition()).getType());
                    i.putExtra("demande_adr",mExampleList.get(viewHolder.getAdapterPosition()).getAddresse_recept());

                    i.putExtra("demandeNomLivreur",mExampleList.get(viewHolder.getAdapterPosition()).getNomLivreur());

                    i.putExtra("demandePreNomLivreur",mExampleList.get(viewHolder.getAdapterPosition()).getPrenomLivreur());

                    i.putExtra("demandeTelLivreur",mExampleList.get(viewHolder.getAdapterPosition()).getTelLivreur());


                    i.putExtra("demande_typeDC",mExampleList.get(viewHolder.getAdapterPosition()).getTypeDC());

                    mContext.startActivity(i);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });


        return viewHolder;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (mExampleList.get(position).getTitre() != null){
            holder.dm_titre.setText(mExampleList.get(position).getTitre()+"");
            holder.dm_etat.setText(mExampleList.get(position).getType());


        }
        else{
           // holder.view_container.setBackgroundColor(Color.rgb(246,246,246));

            holder.dm_titre.setText(mExampleList.get(position).getQuoi());
            holder.dm_etat.setText(mExampleList.get(position).getTypeDC());

        }
        holder.dm_nom_prenom_rcpt.setText("À: "+mExampleList.get(position).getNom_prenom_recept()+"");
        holder.dm_lieu.setText("Vers: " +mExampleList.get(position).getLieu());
        holder.dm_id.setText(mExampleList.get(position).getId()+"");



        if (mExampleList.get(position).getEtat().contains("Valide"))
        {
            // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,1));

            holder.img_thumbnail.setBackgroundColor(Color.rgb(75, 181, 67));

        }
        else if (mExampleList.get(position).getEtat().contains("EnTraitement"))
        {
            holder.img_thumbnail.setBackgroundColor(Color.rgb(0, 191, 255));
            // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(2,3));


        }
        else if (mExampleList.get(position).getEtat().contains("EnCours"))
        {
            holder.img_thumbnail.setBackgroundColor(Color.rgb(255, 165, 0));
            //holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(2,3));


        }
        else if (mExampleList.get(position).getEtat().contains("Cloture"))
        {
            // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,2).toUpperCase());

            holder.img_thumbnail.setBackgroundColor(Color.rgb(178, 34, 34));

        }
        else if (mExampleList.get(position).getEtat().contains("Retour"))
        {
            //holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,1));

            holder.img_thumbnail.setBackgroundColor(Color.rgb(127,255,212));

        }
        else{
            // holder.dm_myImageViewText.setText(mData.get(position).getEtat().substring(0,1));

        }

        holder.dm_myImageViewText.setText(mExampleList.get(position).getEtat());


        //holder.dm_dateCreation.setText( mExampleList.get(position).getDateCreation());

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd'T'");
        try {
            Date d = sdf.parse(mExampleList.get(position).getDateCreation());
            String input = ""+d.getYear();
            holder.dm_dateCreation.setText(d.getDate()+"/"+ d.getMonth()+"/20"+input.substring(input.length() - 2));

        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }









//        holder.dm_type.setText(mData.get(position).getType());

        // Load Image from the internet and set it into Imageview using Glide




    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public void filterList(ArrayList<Demande> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder  {

        TextView dm_id ;
        TextView dm_titre ;
        TextView dm_quoi ;
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
        TextView dm_dateCreation ;
        LinearLayout view_container;

        TextView country;




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
            dm_dateCreation= itemView.findViewById(R.id.dateCreation_demande);

            country = (TextView) itemView.findViewById(R.id.demande_titre);

        }
    }



}
