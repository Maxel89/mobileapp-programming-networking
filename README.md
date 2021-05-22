
First I created the list view.
```
    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </ListView>
```

Then I created the class Mountain with the getters so that I could call on these in the activity to display the value of the different member values.
I also overwrote the toString function to display the name of the mountain instead of the address.
```
    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

```

In the MainActivity I defined a array list and a array adapter before creating them in the onCreate function.
```
    ArrayList<Mountain> mountains;
    ArrayAdapter<Mountain> adapter;
```

```
        mountains = new ArrayList<Mountain>();
        adapter = new ArrayAdapter<Mountain>(this, android.R.layout.simple_list_item_1, mountains);
```

Added the JsonTask class to get the string from the webservice, this class is then created in the "top" class MainActivity and executed.
This class returns a JSON object (I was not aware GSON was an option, this information has passed me by).
```
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

```

The JSON object is then past to the onPostExecute function in the JsonTask class to parse this to the java class Mountain
by dividing the JSONArray into strings and ints.

```
                JSONArray jsonArray = (JSONArray) new JSONTokener(json).nextValue();
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject element = jsonArray.optJSONObject(i);
                    String ID = element.getString("ID");
                    String name = element.getString("name");
                    String type = element.getString("type");
                    String company = element.getString("company");
                    String location = element.getString("location");
                    String category = element.getString("category");
                    int size = element.getInt("size");
                    int cost = element.getInt("cost");
                    String wiki = element.getJSONObject("auxdata").getString("wiki");
                    String img = element.getJSONObject("auxdata").getString("img");
                    mountains.add(new Mountain(ID,name,type,company,location,category,size,cost,wiki,img));
```

Lastly the list view is created and the on click listener is defined.
```
ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mountain mt = mountains.get(position);
                final Snackbar snackbar = Snackbar.make(findViewById(R.id.listView),
                        mt.getName() + " (" + mt.getLocation() + ")" + "\n" + "Height: " + mt.getSize() + " Price: " + mt.getCost(),
                        10000);
                snackbar.setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
```