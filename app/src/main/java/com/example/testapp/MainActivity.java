package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testapp.api.API;
import com.example.testapp.api.response.ConfigurationRootApiResponse;
import com.example.testapp.api.response.DataApiResponse;
import com.example.testapp.api.response.ErrorApiResponse;
import com.example.testapp.api.response.UserApiResponse;
import com.example.testapp.api.response.UserRootApiResponse;
import com.example.testapp.entities.InterfaceConfiguration;
import com.example.testapp.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private API api;
    private Button addEmployeeButton;
    private RecyclerView recyclerView;
    private List<User> userList = new ArrayList<>();
    private UserAdapter adapter = new UserAdapter(userList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addEmployeeButton = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        api = createAPI();
        addEmployeeButton.setOnClickListener(v -> getConfiguration());
    }

    public void makeRequestForEmployees(String endpoint, HashMap<String,String> attributes){
        StringBuilder s = endpoint.length()>1 ? new StringBuilder(endpoint.substring(1) + "?") : new StringBuilder("?");
        for(Map.Entry<String,String> entry: attributes.entrySet()){
            s.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String url = s.substring(0,s.length()-1);//удалить последний &
        api.addEmployee(url).enqueue(new Callback<UserRootApiResponse>() {
            @Override
            public void onResponse(Call<UserRootApiResponse> call, Response<UserRootApiResponse> response) {
                ErrorApiResponse errorApiResponse = response.body().getError();
                if(errorApiResponse.isError()) {
                    Toast.makeText(MainActivity.this, errorApiResponse.getDescription(), Toast.LENGTH_LONG).show();
                    return;
                }
                userList.add(new UserMapper().toUser(response.body().getData().getUser()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UserRootApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getConfiguration(){
        api.getConfiguration().enqueue(new Callback<ConfigurationRootApiResponse>() {
            @Override
            public void onResponse(Call<ConfigurationRootApiResponse> call, Response<ConfigurationRootApiResponse> response) {
                createInputFragment(new ViewMapper().map(response.body().getActivities().get(0).getLayout()));
            }

            @Override
            public void onFailure(Call<ConfigurationRootApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void createInputFragment(InterfaceConfiguration configuration){
        InputFragment inputFragment = new InputFragment();
        Bundle b = new Bundle();
        b.putParcelable(InputFragment.CONFIGURATION_KEY,configuration);
        inputFragment.setArguments(b);
        inputFragment.show(getSupportFragmentManager(),InputFragment.TAG);
    }

    private API createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/vth2k/7f41b5d6d3b78838d35a9b504d21f2f0/raw/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(API.class);
    }
}