package com.example.meddispenserschedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>{

    private ArrayList<medicationinfo> mMedList;

    public static class MedicationViewHolder extends RecyclerView.ViewHolder {
        public TextView mMedname;
        public TextView mTime;
        public TextView mNumbpills;

        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            mMedname = itemView.findViewById(R.id.medname);
            mTime = itemView.findViewById(R.id.time);
            mNumbpills = itemView.findViewById(R.id.numberofpills);
        }
    }
    public MedicationAdapter(ArrayList<medicationinfo> medList) {
        mMedList = medList;

    }


    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        MedicationViewHolder mvh = new MedicationViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        medicationinfo currentItem = mMedList.get(position);
        String medName = "Medication: " + currentItem.getmMedName();
        holder.mMedname.setText(medName);
        String medTime = "Time: " + currentItem.getMedTime();
        holder.mTime.setText(medTime);
        String numbPills = "# of Pills: " +currentItem.getNumbPills();
        holder.mNumbpills.setText(numbPills);

    }

    @Override
    public int getItemCount() {
        return mMedList.size();
    }



}
