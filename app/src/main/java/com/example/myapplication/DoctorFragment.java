
package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DoctorFragment extends Fragment {
    RecyclerView recyclerView;
   // String url = "https://api.jsonserve.com/YZ2tHr";
       // String url= "https://codebeautify.org/jsonviewer/y2343b97e";

   //String url = "https://api.jsonserve.com/tUrrp9";

    //String url ="https://api.jsonserve.com/5Ld-QL";

   // String url ="https://api.jsonserve.com/s45yCc";
//    String url = "http://192.168.0.111:8080/doctor/all"
   //String url = "http://192.168.0.105:8080/doctor/all";

    String url = StaticVariable.araf+"/doctor/all";


   // private  static final String API_URL = "http://192.168.0.105:8080/add/appointment";

    Doctor_MyAdapter myAdapter;
    List<Doctor_MyItem> listItems;
    Context context;
    ProgressDialog progressDialog;

    SearchView searchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);
        context = getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        searchView = view.findViewById(R.id.searchViewDoctor);
        searchView.clearFocus();


        listItems = new ArrayList<>();
        loadData();
        searchDoctor();
        return view;
    }

    public void searchDoctor(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the search query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the data based on the search query
                filterlist2(newText);
                return true;
            }
        });

    }

    private void filterlist2(String text){
        List<Doctor_MyItem>filerList=new ArrayList<>();

        for(Doctor_MyItem item:listItems){
            if(item.getHead().toLowerCase().contains(text.toLowerCase())){
                filerList.add(item);
            }

        }

        if(filerList.isEmpty()){
            Toast.makeText(context, "No data is found!", Toast.LENGTH_SHORT).show();
        }else{
            myAdapter.setFilterList2(filerList);
        }

    }

    public void loadData() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        Toast.makeText(context, "Server is loading!", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server is Okay!", Toast.LENGTH_SHORT).show();

                String header,desc,img;
                Long idForDoctor;
                String HospitalName, phoneNumber;
                String HospitalLocation;

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("doctors");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject receive = array.getJSONObject(i);
                        header=  receive.getString("firstName");
                        desc=  receive.getString("degrees");
                        img=  receive.getString("dp");
                        idForDoctor=receive.getLong("id");
                        HospitalName=receive.getString("currentHospital");
                        phoneNumber=receive.getString("contactNo");
                        Double ob1=0.0;
                        Double ob2=0.0;
                        HospitalLocation=receive.getString("place");
                        Doctor_MyItem item = new Doctor_MyItem(
                               header,desc,img,idForDoctor,HospitalName,ob1,ob2,phoneNumber,HospitalLocation
                        );
                        listItems.add(item);
                    }
                    myAdapter = new Doctor_MyAdapter(listItems, context);
                    recyclerView.setAdapter(myAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error : ", error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }

        });
        queue.add(stringRequest);
    }



//    public void makePostRequest() {
//        JSONObject requestBody = new JSONObject();
//
//        try {
//
//            JSONArray doctorsArray = requestBody.getJSONArray("doctors");
//
//            for (int i = 0; i < doctorsArray.length(); i++) {
//                JSONObject doctorObject = doctorsArray.getJSONObject(i);
//
//                Long idForDoctor = doctorObject.getLong("id");
//                String currentHospital = doctorObject.getString("currentHospital");
//
//                // Create a JSON object for each doctor
//                JSONObject doctorData = new JSONObject();
//                doctorData.put("doctorId", idForDoctor);
//                doctorData.put("hospitalName", currentHospital);
//
//                // Add the doctor's data to the main requestBody
//                requestBody.put("doctor_" + i, doctorData);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        ApiRequestHelper.sendPostRequest(getContext(), API_URL, requestBody, new ApiRequestHelper.VolleyCallback() {
//            @Override
//            public void onSuccess(String result) {
//                // Handle the successful response here
//                // 'result' contains the response from the server as a JSON string
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//                Toast.makeText(context, "Server Error for apiRequest!", Toast.LENGTH_SHORT).show();
//                // Handle errors here
//                // 'error' contains information about the error
//            }
//        });
//    }



//    public void makePostRequest() {
//        JSONObject requestBody = new JSONObject();
//
//        Long idForDoctor;
//        String HospitalName,phoneNumber;
//        String HospitalLocation;
//
//        try {
//            JSONArray array = requestBody.getJSONArray("doctors");
//
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject receive = array.getJSONObject(i);
//
//
//                idForDoctor=receive.getLong("id");
//
//                HospitalName=receive.getString("currentHospital");
//
//
//
//                requestBody.put("doctorId",idForDoctor);
//                requestBody.put("email", HospitalName);
//
//
//
//
//            }
//
//
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        ApiRequestHelper.sendPostRequest(getContext(), API_URL, requestBody, new ApiRequestHelper.VolleyCallback() {
//            @Override
//            public void onSuccess(String result) {
//                // Handle the successful response here
//                // 'result' contains the response from the server as a JSON string
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//
//                Toast.makeText(context, "Server Error for apiRequest!", Toast.LENGTH_SHORT).show();
//                // Handle errors here
//                // 'error' contains information about the error
//            }
//        });
//    }
}

