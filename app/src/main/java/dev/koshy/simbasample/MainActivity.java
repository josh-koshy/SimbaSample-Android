package dev.koshy.simbasample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dev.koshy.simbasample.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private AsyncInit asyncInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The Start of Execution of Custom Code
        asyncInit = new AsyncInit();
        SimbaJosh credentials = new SimbaJosh("e7OencHUVC3ivO4dpucF5BtZRKIEnBpMhfSaBjpC", "oKrTycNJOReLSQjfbeheY5n95e9U4EL3TSjngbEY4GVBXFV4PWqPD1LownECtb869qgZn9xtvv02baRpeNQiRrXJETI16WM046Ijcvy10hPlZMQeNS5uvkZMX8ZrrmNr");

        // Example usage
        credentials.sendApiRequest(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("SimbaJosh", "RESPONSE RECEIVED");
                if (response.isSuccessful()) {
                    System.out.println("API Response: " + response.body());
                        Log.d("SimbaJosh", response.body().toString());
                } else {
                    System.out.println("Failed API Call: " + response.message());
                    Log.d("SimbaJosh", response.message().toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("SimbaJosh", "ERROR");
                t.printStackTrace();
            }
        });

        // The End of Execution of Custom Code
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}