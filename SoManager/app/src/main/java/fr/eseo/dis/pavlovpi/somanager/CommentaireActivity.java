/**
 * @file CommentaireActivity.java
 * @version 0.2
 * @author Mehdi Bouafia & Pierre Pavlovic
 * @date 18/11/2018
 *
 * @section License
 *
 * GNU GENERAL PUBLIC LICENSE
 * Version 3, 29 June 2007
 *
 * Copyright (C) 2018  Mehdi Bouafia & Pierre Pavlovic
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */
package fr.eseo.dis.pavlovpi.somanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

/**
 * @class Commentaire Activity
 * @brief Allows to put a note to a poster
 */
public class CommentaireActivity extends AppCompatActivity {

    EditText editText;
    TextView textData1;
    TextView textData2;

    static final int RED_BLOCK_SIZE = 100;

    /**
     * @fn onCreateView
     * @brief creates and returns the view hierarchy associated with the fragment
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentaire);
        editText = (EditText)findViewById(R.id.editText);
        textData1 = (TextView)findViewById(R.id.textData1);
        textData2 = (TextView)findViewById(R.id.textData2);

        try {
            FileInputStream fileInputStream = openFileInput("dataCommentaire.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            char[] iChars = new char[RED_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead=inputStreamReader.read(iChars))>0)
            {
                String readString = String.copyValueOf(iChars,0,charRead);
                s += readString;
                iChars = new char[RED_BLOCK_SIZE];
            }
            editText.setText(s);
            editText.setSelection(editText.getText().length());

            TextView total = (TextView)findViewById(R.id.textData2);
            total.setText(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @fn load
     * @brief allows to load the old comment
     * @param view
     */
    public void load(View view) {
        try {
            FileInputStream fileInputStream = openFileInput("dataCommentaire.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            char[] iChars = new char[RED_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = inputStreamReader.read(iChars))>0)
            {
                String readString = String.copyValueOf(iChars,0,charRead);
                s += readString;
                iChars = new char[RED_BLOCK_SIZE];
            }
            editText.setText(s);
            editText.setSelection(editText.getText().length());

            TextView total = (TextView)findViewById(R.id.textData2);
            total.setText(s);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @fn load
     * @brief allows to save the old comment
     * @param view
     */
    public void save(View view) {
        try {
            String data = editText.getText().toString();

            TextView total = (TextView )findViewById(R.id.textData2);
            total.setText(String.valueOf(editText.getText().toString()));

            FileOutputStream fileOutputStream = openFileOutput("dataCommentaire.txt", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            Toast.makeText(this, "File saved Successfully", Toast.LENGTH_SHORT).show();
            editText.setText("");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
