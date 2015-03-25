package willsdev.will.trainerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;


public class MainActivity extends ActionBarActivity {
    final static int REQUEST_IMAGE_CAPTURE = 1;
    EditText nameFeild;
    EditText ageFeild;
    Client[] clientList = new Client[100];
    int numberOfClients = 0;
    String CLIENTFILE = "clientlist.txt";
    boolean listPopulated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (savedInstanceState != null) {
//            if (savedInstanceState.getInt("comingBackFromClientForm") == 1) {
//                String name = savedInstanceState.getString("name");
//                int age = savedInstanceState.getInt("age");
//
//                addClientToArray(new Client(name, age));
//            }
//        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fillActivity_main();
    }

    protected void onResume() {
        super.onResume();
        fillActivity_main();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void fillActivity_main(){
        if(listPopulated == false) { // check to see if its aready been created
            populateClientList();//fill array with client objects
            listPopulated = true;
        }
        //setup asset manager
        AssetManager am = getApplicationContext().getAssets();

        //Create the "GridLayout Image Board"
        GridLayout buttonBoard = (GridLayout) findViewById(R.id.buttonboard);
        buttonBoard.removeAllViews();
        buttonBoard.setRowCount(7);
        buttonBoard.setColumnCount(5);
        int idealWidth = buttonBoard.getWidth() / 5;//get width of the board
        int idealHeight = buttonBoard.getHeight() / 7;//same



        //create the Listeners, this is a place holder for now but will eventually use SetCurrentClient() (or maybe just switch to Start screen, with the current client?)
        View.OnClickListener imageClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CLICK AT: " + v.getId());
                Client temp = clientList[v.getId()];

                Intent i = new Intent(getApplicationContext(), DisplayClient.class);
                System.out.println(temp.getName());
                i.putExtra("name", temp.getName());
                System.out.println(i.getStringExtra("name"));
                i.putExtra("age", Integer.toString(temp.getAge()));
                startActivity(i);

            }
        };
        int j = 0; //used the keep track of the id's we set for the buttons
        for (int i = 0; i < clientList.length; i++) {
            if (clientList[i] != null) {
                //creation and ID setting
                ImageButton imgbutton = (ImageButton) new ImageButton(this);
                imgbutton.setId(j);

                //Layout shit
                imgbutton.setImageResource(R.mipmap.ic_launcher);
                imgbutton.setMinimumWidth(idealWidth);
                imgbutton.setMinimumHeight(idealHeight);

                imgbutton.setOnClickListener(imageClickListener);

                //TODO UNECESSARY - Figure out ID system, ie how to name the client image rescources
                //check and set image
                if(clientList[i].getClientImage().equals(" ")) {
//                    try{
//                    imgbutton.set(am.openFd(clientList[i].getClientImage()));}
//                        catch(Exception ex){
//                             ex.toString();
//                    }
                    Log.d("ClientImageCheck", "No picture found for " + clientList[i].getName());
                }
                buttonBoard.addView(imgbutton);
                j++;
            }


        }
        //create the new Client Button at the end of all the rest.
        Button newClientButton = (Button) new Button(this);
        newClientButton.setText("+");  // obvious
        newClientButton.setLayoutParams(new LinearLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT));
        newClientButton.setWidth(idealWidth);
        newClientButton.setHeight(idealHeight);
        View.OnClickListener newClientListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateClientForm.class);
                startActivityForResult(i, 199);
                //System.out.println("Doing good so far, leaving the createclient form bnut still in main");

            }
        }; // create listener
        newClientButton.setOnClickListener(newClientListener); // assign listener
        buttonBoard.addView(newClientButton); //add the button the buttonBoard, after all the clients have been added
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check which request we're responding to
        if (requestCode == 199) {
            // Make sure request was successful
            if (resultCode == RESULT_OK) { // this would be RESULT_CANCELED if the user backed out or op failed. magic.
                // The user made a name and crap.
                Bundle extras = data.getExtras();
                String name = extras.getString("name");
                int age = extras.getInt("age");



                Client temp = new Client(name, age);
                addClientToArray(temp);
                System.out.println(name + "attempted add to array");

            }
        }
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

    public void populateClientList(){
        try{
            Log.d("ClientPopulation", "Starting to populate clientList[]");
            AssetManager am = getApplicationContext().getAssets();
            InputStream is = am.open(CLIENTFILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

             int i = 0;
            String param = reader.readLine();
            while(param != null){

                String params[] = param.split(",");
                Client newClient = new Client(params[0], Integer.parseInt(params[1]));
                if(!(params.length < 3)){ // if their is 3 params(csv values), it means their is a link to the image, otherwise they get a stock image.
                    newClient.setClientImage((getPackageResourcePath() + params[0])); // not sure how to get a csv value into an Image, maybe link to a resource file?
                                                                                      // or just use their name as the name of the image and append it to the end of the resource file Path
                }
                Log.d("ClientPopulation",newClient.toString());
                clientList[i] = newClient;
                param = reader.readLine();
                i++;
                numberOfClients++;
            }

    }
        catch(Exception ex){
            Log.d("ClientPopulation","Error in populating clientLIst");
            ex.printStackTrace();
        }
    }


    public void addClientToArray(Client a){
        clientList[numberOfClients] = a;
        numberOfClients++;
    }

    //TODO write writeToFile (save the current clientList to a csv file )
    public void writeToFile(){

    }
    //TODO write start function
    public void start(Client client ){

    }


}
