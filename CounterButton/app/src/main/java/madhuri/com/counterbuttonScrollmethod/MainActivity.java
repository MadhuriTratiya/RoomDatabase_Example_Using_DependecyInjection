package madhuri.com.counterbuttonScrollmethod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import madhuri.com.counterbutton.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private  int numtimeclicked =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView.setMovementMethod(new ScrollingMovementMethod());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String result = "\n The button got appended" + numtimeclicked + "time";
               // binding.textView2.setText(binding.textView.getText().toString());
                binding.textView.append(result);
            }
        });
    }
}