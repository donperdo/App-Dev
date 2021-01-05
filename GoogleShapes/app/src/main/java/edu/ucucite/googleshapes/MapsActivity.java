package edu.ucucite.googleshapes;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setBuildingsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(15.9758, 120.5707), 11));

    }

    public void clickPolygon(View view){
        mMap.clear();
        mMap.addPolygon(new
                PolygonOptions()
                .add(new LatLng(15.9758, 120.5707),
                        new LatLng(15.9061, 120.5853),
                        new LatLng(16.0044, 120.6545),
                        new LatLng(15.9758, 120.5707))
                .strokeColor(Color.RED)
                .fillColor(Color.argb(128, 255, 0, 0)));



        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(15.9758, 120.5707))
                .title("1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.addMarker(new MarkerOptions()
                .position( new LatLng(15.9061, 120.5853))
                .title("2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        mMap.addMarker(new MarkerOptions()
                .position( new LatLng(16.0044, 120.6545))
                .title("3")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));




        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(15.9758, 120.5707), 11));
    }
    public void clickPolyline(View view){
        mMap.clear();


        mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(16.01611599,120.73974609),
                        new LatLng(15.97915298, 120.69957733),
                        new LatLng(16.00341073, 120.66987991),
                        new LatLng(15.97486218,120.56739807))
                .width(10)
                .color(Color.BLUE));

        // Position the map's camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(16.01739501,120.64726638), 11));

    }
    public void clickCircle(View view){
        mMap.clear();
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(15.9758, 120.5707))
                .radius(10000)
                .strokeWidth(10)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(128, 255, 0, 0)));
        mMap.addMarker(new MarkerOptions()
        .position(new LatLng(15.9758, 120.5707))
        .title("Center")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(15.9758, 120.5707), 11));


    }

    //Map Type
    public void clickNormal(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    public void clickHybrid(View view){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }



}