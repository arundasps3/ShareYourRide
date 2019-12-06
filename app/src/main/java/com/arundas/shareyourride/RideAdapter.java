package com.arundas.shareyourride;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.arundas.RideModel;

import java.util.List;

public class RideAdapter extends ArrayAdapter<RideModel> {

    private Activity context;
    List<RideModel> rides;

    public RideAdapter(Activity context, List<RideModel> rides  ) {
        super(context, R.layout.ride_list, rides);

        this.context = context;
        this.rides = rides;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.ride_list
                , null
                , true);

        TextView departure = listViewItem.findViewById(R.id.departure);
        TextView destination = listViewItem.findViewById(R.id.destination);
        TextView date = listViewItem.findViewById(R.id.date);
        TextView time = listViewItem.findViewById(R.id.time);
        TextView message = listViewItem.findViewById(R.id.message);
        TextView fare = listViewItem.findViewById(R.id.fare);
        Button Request = listViewItem.findViewById(R.id.RequestButton);

        RideModel rideModel = rides.get(position);
        departure.setText(rideModel.getDeparture());
        destination.setText(rideModel.getDestination());
        date.setText(rideModel.getDate());
        time.setText(rideModel.getTime());
        message.setText(rideModel.getMessage());
        fare.setText(rideModel.getFare());

        return listViewItem;
    }
}
